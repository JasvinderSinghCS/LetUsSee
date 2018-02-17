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
	
	public List<TableInfoDTO> getAllTables(){
		List<Table> tables = tableRepository.findAll();
		List<TableInfoDTO> tableInfoDtOList = new ArrayList<TableInfoDTO>();
		for(Table table: tables) {
			TableInfoDTO tableDTO = new TableInfoDTO();
			tableDTO.setTableId(table.getId());
			tableDTO.setTableName(table.getTableName());
			List<ColumnInfoDTO> columnInfoDTOList = new ArrayList<ColumnInfoDTO>();
			for(Column column : table.getColumns()) {
				ColumnInfoDTO columnDTO = new ColumnInfoDTO();
				columnDTO.setColumnIndex(column.getColumnIndex());
				columnDTO.setColumnId(column.getId());
				columnDTO.setColumnName(column.getColumnName());
				columnDTO.setDataType(column.getDataType());
				columnInfoDTOList.add(columnDTO);
			}
			tableDTO.setColumnInfoList(columnInfoDTOList);
			tableInfoDtOList.add(tableDTO);
		}
		return tableInfoDtOList;
	}

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
		TableInfoDTO tableInfoDTOToReturn = new TableInfoDTO();
		return tableInfoDTOToReturn;
	}
	
}
