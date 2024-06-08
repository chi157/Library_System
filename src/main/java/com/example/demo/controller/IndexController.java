package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@GetMapping("/") 
	public String index(Model model, HttpServletRequest request) {
		return "index";
	}
	

	
}
