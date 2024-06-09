package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Library;
import com.example.demo.repository.LibraryRepository;

@Controller
@RequestMapping("/library-info")
public class LibraryController {
	
	@Autowired
	LibraryRepository libraryRepository;
	
	@GetMapping("/") 
	public String index(Model model) {
		Library library = libraryRepository.findById(1L).get();
		model.addAttribute("library", library);
		return "library-info";
	}
	
	@PostMapping("/edit") // 對資料庫進行修改
	public String update(Library library, Model model) {
		library.setId(1L);
		try {
			libraryRepository.save(library);
			model.addAttribute("edit_state", "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("edit_state", "修改失敗");
		}
		
		
		return "library-info";
	}
}
