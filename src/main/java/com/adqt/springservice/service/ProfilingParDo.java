package com.adqt.springservice.service;

import com.adqt.springservice.entity.Column;
import com.adqt.springservice.entity.RuleValue;
import com.adqt.springservice.enums.RuleTypeEnum;
import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollectionView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProfilingParDo extends DoFn<String, KV<String, List<TableRow>>> {

    private final PCollectionView<ProfilingContext> profilingContext;

    public ProfilingParDo(PCollectionView<ProfilingContext> profilingContext) {
        this.profilingContext = profilingContext;
    }

    @ProcessElement
    public void processElement(ProcessContext c) {
        ProfilingContext localProfilingContext = c.sideInput(profilingContext);
        String row = c.element();
        List<RuleValue> rules = localProfilingContext.getRules();
        String[] columns = row.split(",");
        List<TableRow> Rows = new ArrayList<>();
        for (RuleValue rule : rules) {
            TableRow tableRow = new TableRow();
            tableRow.set("tablename", localProfilingContext.getTableName());
            tableRow.set("ruleType", rule.getRuleTypes());
            tableRow.set("row", rule.getRuleTypes());
            Column column = localProfilingContext.getSchema().getColumn(rule.getColumnIndex());
            List<Comparable> dataObject = castObject(columns[rule.getColumnIndex()], column.getDataType());
            List<Comparable> ValueObject = castObject(rule.getRuleValue(), column.getDataType());
            Boolean status = true;
            String data = columns[rule.getColumnIndex()];
            if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.ACCURACY.toString())) {
                status = interpretAccuracyRule(data, castObject(data, rule.getRuleValue()), rule.getRuleKey());
            } else if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.COMPLETNESS.toString())) {
                status = interpretCompletenessRule(data, rule.getRuleKey());
            } else if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.CONFORMITY.toString())) {
                status = interpretConformityRule(data, rule.getRuleValue(), rule.getRuleKey());
            } else if (rule.getRuleTypes().equalsIgnoreCase(RuleTypeEnum.CONSISTENCY.toString())) {
                status = interpretConsistencyRule(data, rule.getRuleValue(), rule.getRuleKey());
            }
            Rows.add(tableRow);
        }
        c.output(KV.of(localProfilingContext.getTableName(), Rows));
    }

    private List<Comparable> castObject(String value, String dataType) {
        List<Comparable> output = new ArrayList<>();
        try {
            switch (dataType.toLowerCase()) {
                case "string":
                    output.add(value);
                    break;
                case "integer":
                    output.add(Integer.parseInt(value));
                    break;
                case "float":
                    output.add(Float.parseFloat(value));
                    break;
                case "date":
                    SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
                    Date d = f.parse(value);
                    Long milliseconds = d.getTime();
                    output.add(milliseconds);
                    break;
                case "boolean":
                    output.add(Boolean.parseBoolean(value));
            }
        } catch (Exception e) {
        }
        return output;

    }

    public boolean interpretConsistencyRule(String data, String ruleValue, String rule) {
        if (rule.toUpperCase().equals("IN")) {
            String[] in = ruleValue.split(",");
            if (Arrays.binarySearch(in, data) < 0)
                return false;
            else return true;
        }
        return false;

    }

    public boolean interpretCompletenessRule(String data, String rule) {
        if (rule.toUpperCase().equals("NOTNULL")) {
            if (data == null || data.equals("") || data.equals("-")) {
                return true;
            } else return false;
        }
        return false;
    }

    public boolean interpretConformityRule(String data, String ruleValue, String rule) {
        if (rule.toUpperCase().equals("NOTNULL")) {
            List<Comparable> list = castObject(data, ruleValue);
            if (list == null || list.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean interpretAccuracyRule(Comparable data, List<Comparable> ruleValue, String rule) {
        switch (rule.toUpperCase().trim()) {
            case "GREATER":
                return data.compareTo(ruleValue.get(0)) > 0;
            case "SMALLER":
                return data.compareTo(ruleValue.get(0)) < 0;
            case "EQUAL":
                return data.compareTo(ruleValue.get(0)) == 0;
            case "BETWEEN":
                return data.compareTo(ruleValue.get(0)) > 0 && data.compareTo(ruleValue.get(1)) < 0;
            case "NOTEQUAL":
                return data.compareTo(ruleValue.get(0)) != 0;
            default:
                return false;
        }
    }
}
