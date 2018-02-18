package com.adqt.springservice.service;

import com.adqt.springservice.entity.ColumnInformation;

import java.util.List;

public class Schema {

    List<ColumnInformation> columns;

    public Schema(List<ColumnInformation> columns) {
        this.columns= columns;
    }

    public List<ColumnInformation> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInformation> columns) {
        this.columns = columns;
    }

    public ColumnInformation getColumn(Integer columnIndex) {
        for(ColumnInformation column:columns){
            if(column.getColumnIndex() == columnIndex)
                return column;
        }
        return null;
    }
}
