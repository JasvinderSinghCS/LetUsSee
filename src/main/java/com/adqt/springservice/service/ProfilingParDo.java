package com.adqt.springservice.service;

import com.adqt.springservice.entity.ColumnInformation;
import com.adqt.springservice.entity.RuleValue;
import com.adqt.springservice.enums.RuleTypeEnum;
import com.google.api.services.bigquery.model.TableRow;
import com.google.cloud.dataflow.sdk.values.TupleTag;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollectionView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ProfilingParDo extends DoFn<String, TableRow> {

    private final PCollectionView<ProfilingContext> profilingContext;
    final TupleTag<TableRow> accuracyTag = new TupleTag<TableRow>(){};
    final TupleTag<TableRow> completenessTag = new TupleTag<TableRow>(){};
    final TupleTag<TableRow> conformityTag = new TupleTag<TableRow>(){};
    final TupleTag<TableRow> consistencyTag = new TupleTag<TableRow>(){};

    public ProfilingParDo(PCollectionView<ProfilingContext> profilingContext) {
        this.profilingContext = profilingContext;
    }

    @ProcessElement
    public void processElement(ProcessContext c) {
        ProfilingContext localProfilingContext = c.sideInput(profilingContext);
        String row = c.element();
        List<RuleValue> rules = localProfilingContext.getRules();
        String[] columns = row.split(",");
        TableRow tableRow = new TableRow();
        for (RuleValue rule : rules) {
            tableRow.set("tablename", localProfilingContext.getTableName());
            tableRow.set("row", rule.getRuleTypes());
            ColumnInformation column = localProfilingContext.getSchema().getColumn(rule.getColumnIndex());
            Boolean status = true;
            String data = columns[rule.getColumnIndex()];
            if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.ACCURACY.toString())) {
                status = interpretAccuracyRule(data, rule.getRuleValue(), rule.getDataType(), rule.getRuleKey());
                tableRow.set("accuracy", status);
            } else if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.COMPLETNESS.toString())) {
                tableRow.set("completeness", status);
                status = interpretCompletenessRule(data, rule.getRuleKey());
            } else if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.CONFORMITY.toString())) {
                tableRow.set("conformity", status);
                status = interpretConformityRule(data, rule.getRuleValue(), rule.getRuleKey());
            } else if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.CONSISTENCY.toString())) {
                tableRow.set("consistency", status);
                status = interpretConsistencyRule(data, rule.getRuleValue(), rule.getRuleKey());
            }
        }

        c.output(tableRow);
//        c.output(KV.of(localProfilingContext.getTableName(), Rows));

    }

    private Comparable castObject(String value, String dataType) {
        try {
            switch (dataType.toLowerCase()) {
                case "string":
                    return value;
                case "integer":
                    return Integer.parseInt(value);
                case "float":
                    return Float.parseFloat(value);
                case "date":
                    SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
                    Date d = f.parse(value);
                    return d.getTime();
                case "boolean":
                    return Boolean.parseBoolean(value);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private boolean interpretConsistencyRule(String data, String ruleValue, String rule) {
        if (rule.toUpperCase().equals("IN")) {
            String[] in = ruleValue.split(",");
            return Arrays.binarySearch(in, data) >= 0;
        }
        return false;

    }

    private boolean interpretCompletenessRule(String data, String rule) {
        if (rule.toUpperCase().equals("NOTNULL")) {
            return data != null && !data.equals("") && !data.equals("-");
        }
        return false;
    }

    private boolean interpretConformityRule(String data, String ruleValue, String rule) {
        if (rule.toUpperCase().equals("NOTNULL")) {
            Comparable com = castObject(data, ruleValue);
            return com!=null;
        }
        return false;
    }

    private boolean interpretAccuracyRule(String data, String ruleValue, String dataType, String rule) {
        Comparable dataCom = castObject(data,dataType);
        if(rule.toUpperCase().trim().equals("GREATER")) {
            Comparable ruleValCom = castObject(data, dataType);
            return dataCom.compareTo(ruleValCom) > 0;
        }else if(rule.toUpperCase().trim().equals("SMALLER")) {
            Comparable ruleValCom = castObject(data, dataType);
            return dataCom.compareTo(ruleValCom) < 0;
        }else if(rule.toUpperCase().trim().equals("EQUAL")) {
            Comparable ruleValCom = castObject(data, dataType);
            return dataCom.compareTo(ruleValCom) == 0;
        }else if(rule.toUpperCase().trim().equals("BETWEEN")) {
            String[] range = ruleValue.split(",");
            Comparable ruleValCom1 = castObject(range[0], dataType);
            Comparable ruleValCom2 = castObject(range[1], dataType);
            return dataCom.compareTo(ruleValCom1) > 0 && dataCom.compareTo(ruleValCom2) < 0;
        } else if(rule.toUpperCase().trim().equals("NOTEQUAL")) {
                return data.compareTo(ruleValue) != 0;
        }else{
                return false;
        }
    }
}
