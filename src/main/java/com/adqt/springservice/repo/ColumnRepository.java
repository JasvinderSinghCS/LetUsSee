package com.adqt.springservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adqt.springservice.entity.Column;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Integer> {

	@Query("SELECT c FROM Column c WHERE c.table.tableName = :tableName")
    List<Column> findByTableName(@Param("tableName") String tableName);
}
