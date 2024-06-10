package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/reader-manage")
public class ReaderController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository repository;
	
	@GetMapping("/") 
	public String index(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userRepository.findAllByReader());
		return "reader-manage";
	}
	@PostMapping("/search")
	public String search(@ModelAttribute User user, Model model) {
		model.addAttribute("users", null);
		Role role = repository.getById(3l);
		
		List<User> users = userRepository.findBySearchCriteria(
				user.getAccount().isEmpty() ? null : "%" + user.getAccount() + "%", 
				user.getName().isEmpty() ? null : "%" + user.getName() + "%",
				role
			);
		model.addAttribute("users", users);
		//return "redirect:/book-manage/";
		return "reader-manage";
	}
	
	@GetMapping("/create-page")
	public String create_page(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userRepository.findAllByReader());
		return "reader-create-page";
	}

	@PostMapping("/create-reader")
	public String create_reader(User user) {
		userRepository.saveReader(user.getAccount(), user.getName(), user.getAccount(), user.getAddress(), user.getBirth(), user.getGender(), user.getPhone(), user.getEmail());
		return "redirect:/reader-manage/";
	}


	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit_reader(@PathVariable("id") Long id,  Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		model.addAttribute("roles", repository.findAll());
		return "reader-edit-page";
	}
	
	@PostMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, User user) {
		user.setId(id);
		userRepository.save(user);
		return "redirect:/reader-manage/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
		return "redirect:/reader-manage/";
	}
	
}