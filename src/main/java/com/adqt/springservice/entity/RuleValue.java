package com.adqt.springservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="rule_value")
public class RuleValue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@javax.persistence.Column(name="table_name")
	private String tableName;
	
	@javax.persistence.Column(name="column_name",nullable=false)
	private String columnName;
	
	@javax.persistence.Column(name="data_type")
	private String dataType;
	
	@javax.persistence.Column(name="column_index")
	private String columnIndex;
	
	@javax.persistence.Column(name="rule_type")
	private String ruleTypes;
	
	@javax.persistence.Column(name="rule_key")
	private String ruleKey;
	
	@javax.persistence.Column(name="rule_value")
	private String ruleValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuleKey() {
		return ruleKey;
	}

	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

}
