package com.adqt.springservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.adqt.springservice.dto.ColumnInfoDTO;
import com.adqt.springservice.dto.TableInfoDTO;
import com.adqt.springservice.entity.Column;
import com.adqt.springservice.entity.Table;
import com.adqt.springservice.repo.TableRepository;

@org.springframework.stereotype.Service
public class TableInfoService {

	@Autowired
	TableRepository tableRepository;

	public TableInfoDTO saveAndGetTable(TableInfoDTO tableInfoDTO) {
		Table table = new Table();
		table.setTableName(tableInfoDTO.getTableName());
		Set<Column> columns = new HashSet<Column>();
		int index = 0;
		for(ColumnInfoDTO columnDTO : tableInfoDTO.getColumnInfoList()) {
			Column column = new Column();
			column.setColumnName(columnDTO.getColumnName());
			column.setColumnIndex(index);
			column.setDataType(columnDTO.getDataType());
			column.setTable(table);
			columns.add(column);
		}
		table.setColumns(columns);
		tableRepository.save(table);
		TableInfoDTO tableInfoDTOToReturn = getTableInfoToSend(table);
		
		return tableInfoDTOToReturn;
	}

	private TableInfoDTO getTableInfoToSend(Table table) {
		TableInfoDTO tableInfoDTO = new TableInfoDTO();
		tableInfoDTO.setTableId(table.getId());
		tableInfoDTO.setTableName(table.getTableName());
		Set<Column> columns = table.getColumns();
		for(Column column : columns) {
			
		}
		return null;
	}
	
}
