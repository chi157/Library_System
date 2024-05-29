package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value = "SELECT * FROM ROLE WHERE id != 3 ORDER BY id", nativeQuery = true)
	public List<Role> findStaff();
}