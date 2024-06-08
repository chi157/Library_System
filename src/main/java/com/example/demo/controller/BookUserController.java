package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookStateRepository;
import com.example.demo.repository.BookUserRepository;


@Controller
@RequestMapping("/record-manage")
public class BookUserController {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookUserRepository bookUserRepository;
	
	@Autowired
	BookStateRepository bookStateRepository;
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("records", bookUserRepository.findAll());
		return "record-manage";
	}
	
}
