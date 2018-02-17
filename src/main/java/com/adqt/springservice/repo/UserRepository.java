package com.adqt.springservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adqt.springservice.entity.User;
/**
 * @author prateek.mishra
 * Repository Interface for CRUD operations on User Table
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmailIgnoreCase(String email); 
}
