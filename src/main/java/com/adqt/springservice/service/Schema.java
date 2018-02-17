package com.adqt.springservice.service;

import com.adqt.springservice.entity.Column;

import java.util.List;

public class Schema {

    List<Column> columns;

    public Schema(List<Column> columns) {
        this.columns= columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Column getColumn(Integer columnIndex) {
        for(Column column:columns){
            if(column.getColumnIndex() == columnIndex)
                return column;
        }
        return null;
    }
}
