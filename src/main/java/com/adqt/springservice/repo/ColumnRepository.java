package com.adqt.springservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adqt.springservice.entity.Column;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Integer> {

    List<Column> findByTableName(String tableName);
}
