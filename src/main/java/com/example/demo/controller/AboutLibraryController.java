package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Library;
import com.example.demo.repository.LibraryRepository;

@Controller
@RequestMapping("/about-library")
public class AboutLibraryController {
	@Autowired
	LibraryRepository libraryRepository;
	
	@GetMapping("/") 
	public String index(Model model) {
		Library library = libraryRepository.findById(1L).get();
		model.addAttribute("library", library);
		return "about-library";
	}
}
