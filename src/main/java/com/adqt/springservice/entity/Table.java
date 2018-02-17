package com.adqt.springservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@javax.persistence.Table(name="table")
public class Table {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@javax.persistence.Column(name="table_name")
	private String tableName;

	@OneToMany(mappedBy="table",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Column> columns;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", tableName=" + tableName + ", columns=" + columns + "]";
	}
	
}
