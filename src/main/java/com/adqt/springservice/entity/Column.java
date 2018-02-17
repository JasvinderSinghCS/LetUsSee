package com.adqt.springservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne
	@JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
	private Table table;
	
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

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
