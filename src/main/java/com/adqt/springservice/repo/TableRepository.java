package com.adqt.springservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adqt.springservice.entity.Table;

public interface TableRepository extends JpaRepository<Table, Integer> {
	
}
