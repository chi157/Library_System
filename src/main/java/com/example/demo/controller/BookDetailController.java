package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.BookRepository;

@Controller
@RequestMapping("/book-detail")
public class BookDetailController {
	
	@Autowired
	BookRepository bookRepository;
	
	
	
	@RequestMapping("/{id}") 
	public String index(@PathVariable("id") Long id,  Model model) {
		model.addAttribute("book", bookRepository.findById(id).get());
		return "book-detail";
	}
	
}
