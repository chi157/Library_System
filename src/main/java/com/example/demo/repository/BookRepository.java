package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query(value = "SELECT * FROM BOOK WHERE book_state_id <= 3 ORDER BY book_state_id", nativeQuery = true)
	public List<Book> findByActivateBook();
	
	@Query(value = "SELECT * FROM BOOK ORDER BY book_state_id", nativeQuery = true)
	public List<Book> findAllAndOrderByActivate();
	
//	@Transactional
//	@Modifying
//	@Query(value = "INSERT INTO BOOK(isbn, author, name, publisher_id, book_state_id, pictureurl) VALUES (?, ?, ?, ?, 1, ?)", nativeQuery = true)
//	public void saveBook(String isbn, String author, String name, String publisher_id, String pictureURL);
	
}