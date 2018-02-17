package com.adqt.springservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adqt.springservice.entity.Table;

public interface TableRepository extends JpaRepository<Table, Integer> {

	@Query("select t from table t")
	List<Table> findAll();

	
}
