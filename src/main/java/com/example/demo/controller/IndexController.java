package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/") 
	public String index(Model model, HttpServletRequest request) {
		return "index";
	}
	

	
}
