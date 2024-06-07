package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//	@Query(value = "SELECT * FROM BOOK WHERE ?", nativeQuery = true)
//	public List<Book> findBooks(String condition);
	
	@Query(value = "SELECT * FROM BOOK WHERE book_state_id <= 3 ORDER BY book_state_id", nativeQuery = true)
	public List<Book> findByActivateBook();
	
	@Query(value = "SELECT * FROM BOOK ORDER BY book_state_id", nativeQuery = true)
	public List<Book> findAllAndOrderByActivate();
	
//	@Query("SELECT b FROM Book b WHERE (:condition IS NULL OR :condition = '' OR b.id IN (SELECT bb.id FROM Book bb WHERE :condition))")
//	public List<Book> findBooks(@Param("condition") String condition);
	
	@Query("SELECT b FROM Book b WHERE (:ISBN IS NULL OR b.ISBN LIKE %:ISBN%) AND (:author IS NULL OR b.author LIKE %:author%) AND (:name IS NULL OR b.name LIKE %:name%) AND (:publisher IS NULL OR b.publisher = :publisher)")
	List<Book> findBySearchCriteria(@Param("ISBN") String ISBN, @Param("author") String author, @Param("name") String name, @Param("publisher") Publisher publisher);
	
//	@Transactional
//	@Modifying
//	@Query(value = "INSERT INTO BOOK(isbn, author, name, publisher_id, book_state_id, pictureurl) VALUES (?, ?, ?, ?, 1, ?)", nativeQuery = true)
//	public void saveBook(String isbn, String author, String name, String publisher_id, String pictureURL);
	public static String buildQueryCondition(String isbn, String author, String name, Long publisherId) {
	    StringBuilder condition = new StringBuilder();
	    boolean isFirst = true;
	    
	    if (!isbn.isEmpty()) {
	        condition.append(isFirst ? " isbn LIKE '%" + isbn + "%'" : " AND isbn LIKE '%" + isbn + "%'");
	        isFirst = false;
	    }
	    
	    if (!author.isEmpty()) {
	        condition.append(isFirst ? " author LIKE '%" + author + "%'" : " AND author LIKE '%" + author + "%'");
	        isFirst = false;
	    }
	    
	    if (!name.isEmpty()) {
	        condition.append(isFirst ? " name LIKE '%" + name + "%'" : " AND name LIKE '%" + name + "%'");
	        isFirst = false;
	    }
	    
	    if (publisherId != null) {
	        condition.append(isFirst ? " publisher_id = " + publisherId : " AND publisher_id = " + publisherId);
	    }
	    
	    return condition.toString();
	}
}