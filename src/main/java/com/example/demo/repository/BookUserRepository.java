package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookUser;

@Repository
public interface BookUserRepository extends JpaRepository<BookUser, Long> {
		
	@Query(value = "SELECT * FROM libms.BOOK_USER WHERE user_id = ? ORDER BY borrow_date DESC", nativeQuery = true)
	public List<BookUser> findByUserID(String id);
	
	@Query(value = "SELECT * FROM libms.BOOK_USER WHERE (book_id = ? AND  actual_return_date IS NULL)", nativeQuery = true)
	public BookUser findByBookAndActualReturnDate(String id);
}