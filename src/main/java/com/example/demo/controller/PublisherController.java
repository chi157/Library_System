package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Publisher;
import com.example.demo.repository.PublisherRepository;

@Controller
@RequestMapping("/publisher-manage")
public class PublisherController {
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("publisher", new Publisher());
		List<Publisher> publishers = publisherRepository.findAll();
		if (publishers != null) {
			model.addAttribute("publishers", publishers);
		}
		return "publisher-manage";
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Publisher publisher) {
		publisherRepository.save(publisher);
		return "redirect:/publisher-manage/";
	}

	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		Publisher publisher = publisherRepository.findById(id).get();
		model.addAttribute("publisher", publisher);
		return "publisher-edit";
	}
	
	@PostMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Publisher publisher) {
		publisher.setId(id);
		publisherRepository.save(publisher);
		return "redirect:/publisher-manage/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		publisherRepository.deleteById(id);
		return "redirect:/publisher-manage/";
	}
	
}
