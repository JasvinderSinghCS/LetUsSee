package com.adqt.springservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adqt.springservice.entity.ColumnInformation;

import java.util.List;

public interface ColumnRepository extends JpaRepository<ColumnInformation, Integer> {

	@Query("SELECT c FROM ColumnInformation c WHERE c.tableInformation.tableName = :tableName")
    List<ColumnInformation> findByTableName(@Param("tableName") String tableName);
}
