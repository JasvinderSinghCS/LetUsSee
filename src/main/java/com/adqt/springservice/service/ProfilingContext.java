package com.adqt.springservice.service;

import com.adqt.springservice.entity.RuleValue;

import java.util.Arrays;
import java.util.List;

public class ProfilingContext {
    String tableName;
    Schema schema;
    List<RuleValue> rules;

    public ProfilingContext(String tableName, Schema schema, List<RuleValue> rules) {
        this.tableName = tableName;
        this.schema = schema;
        this.rules = rules;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<RuleValue> getRules() {
        return rules;
    }

    public void setRules(List<RuleValue> rules) {
        this.rules = rules;
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
