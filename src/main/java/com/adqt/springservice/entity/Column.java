package com.adqt.springservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="column")
public class Column {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@javax.persistence.Column(name="column_name",nullable=false)
	private String columnName;
	
	@javax.persistence.Column(name="data_type")
	private String dataType;
	
	@javax.persistence.Column(name="column_index")
	private int columnIndex;
	
	@javax.persistence.Column(name="table_id")
	private int tableId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
}
