package com.adqt.springservice.dto;

import java.io.Serializable;

public class DataQualityStatDTO implements Serializable {

	private static final long serialVersionUID = 1207833351585694128L;

	private String tableName;
	private long totalRowCount;
	private long qualifiedRowCount;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public long getQualifiedRowCount() {
		return qualifiedRowCount;
	}

	public void setQualifiedRowCount(long qualifiedRowCount) {
		this.qualifiedRowCount = qualifiedRowCount;
	}

	@Override
	public String toString() {
		return "DataQualityStatDTO [tableName=" + tableName + ", totalRowCount=" + totalRowCount
				+ ", qualifiedRowCount=" + qualifiedRowCount + "]";
	}

}
