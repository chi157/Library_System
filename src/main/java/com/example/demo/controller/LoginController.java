package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

 
@Controller
@RequestMapping("/login")
public class LoginController {
 
	@Autowired
	private UserRepository userRepository;

	 
	@RequestMapping("/") 
    public String index(Model model, HttpSession session) {
		User user =(User) session.getAttribute("USER_SESSION");
		if (user == null) {
			user = new User();
		}
		model.addAttribute("user", user);
		//model.addAttribute("user", new User());
        return "login";
    }
	
	@PostMapping("/check")
	public String create(Model model, User user,  HttpServletRequest request) {

		System.out.println(user.getAccount());
		System.out.println(user.getPassword());
		try {
			User loggined_user = userRepository.findByAccountAndPassword(user.getAccount(), user.getPassword());
			System.out.println(loggined_user.getAccount());
			System.out.println(loggined_user.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("USER_SESSION", loggined_user);
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			model.addAttribute("login_state", "登入失敗");
			System.out.println("login error");
			return "login";
		}
		model.addAttribute("login_state", "登入成功");
		return "index";
	}
	
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        //clears session
        request.getSession().invalidate();
        return "redirect:/index/";
    }
}
