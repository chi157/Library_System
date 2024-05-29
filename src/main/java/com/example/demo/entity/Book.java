package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String author;
	
	@Column
	private String ISBN;
	
	@Column
	private String introduce;
	
	@JoinColumn(name = "publisher_id") 
	@ManyToOne()
	private Publisher publisher;
	
	@JoinColumn(name = "book_state_id") 
	@ManyToOne()
	private BookState book_state;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	public List<BookUser> records;
	
	@Column(columnDefinition="LONGBLOB")
	private byte[] picture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public BookState getBook_state() {
		return book_state;
	}

	public void setBook_state(BookState book_state) {
		this.book_state = book_state;
	}

	public List<BookUser> getRecords() {
		return records;
	}

	public void setRecords(List<BookUser> records) {
		this.records = records;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	

	
	
}