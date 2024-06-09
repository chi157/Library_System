package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.ActivityRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	ActivityRepository activityRepository;
	
	@GetMapping("/") 
	public String index(Model model, HttpServletRequest request) {
		model.addAttribute("activities", activityRepository.findAll());
		return "index";
	}
	

	
}
