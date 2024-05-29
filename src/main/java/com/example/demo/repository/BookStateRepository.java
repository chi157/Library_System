package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookState;

@Repository
public interface BookStateRepository extends JpaRepository<BookState, Long> {
		
	
}