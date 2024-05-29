package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookStateRepository;
import com.example.demo.repository.BookUserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book-borrow-record")
public class BookBorrowRecordController {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookUserRepository bookUserRepository;
	
	@Autowired
	BookStateRepository bookStateRepository;
	
	@RequestMapping("/") 
	public String index(Model model, HttpSession session) {
		User user =(User) session.getAttribute("USER_SESSION");
		model.addAttribute("records", bookUserRepository.findByUserID(user.getId().toString()));
		return "book-borrow-record";
	}
	
}
