package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("test", "嗨1");
		return "test";
	}
	

	
}
