package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

@Repository
@Transactional()
public interface UserRepository extends JpaRepository<User, Long> {
		
	@Query(value = "SELECT * FROM USER WHERE account=? AND password=?", nativeQuery = true)
	public User findByAccountAndPassword(String account, String password);
	
	@Query(value = "SELECT * FROM USER WHERE role_id = 3", nativeQuery = true)
	public List<User> findAllByReader();
	
	@Query(value = "SELECT * FROM USER WHERE role_id != 3 ORDER BY role_id", nativeQuery = true)
	public List<User> findAllByStaff();
	
	@Modifying
	@Query(value = "INSERT INTO USER(account, name, password, role_id, address, birth, gender, phone) VALUES (?, ?, ?, 3, ?, ?, ?, ?)", nativeQuery = true)
	public void saveReader(String account, String name, String password, String address, Date birth, String gender, String phone);
	

	@Modifying
	@Query(value = "INSERT INTO USER(account, name, password, role_id, address, birth, gender, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
	public void saveStaff(String account, String name, String password, Long role, String address, Date birth, String gender, String phone);
	
	// jpql
	@Query("SELECT u FROM User u WHERE (:account IS NULL OR u.account LIKE %:account%) AND (:name IS NULL OR u.name LIKE %:name%) AND (:role IS NULL OR u.role = :role)")
	List<User> findBySearchCriteria(@Param("account") String account, @Param("name") String name, @Param("role") Role role);
		
}