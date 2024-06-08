package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

 
@Controller
@RequestMapping("/login")
public class LoginController {
 
	@Autowired
	private UserRepository userRepository;

	 
	@GetMapping("/") 
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
		if(model.getAttribute("login_state") != null){
			model.addAttribute("login_state", "");
		}
		//System.out.println(user.getAccount());
		//System.out.println(user.getPassword());
		try {
			User loggined_user = userRepository.findByAccountAndPassword(user.getAccount(), user.getPassword());
			//System.out.println(loggined_user.getAccount());
			//System.out.println(loggined_user.getPassword());
			if (loggined_user == null) {
				model.addAttribute("login_state", "登入失敗");
				System.out.println("帳號密碼輸入錯誤");
				return "login";
			}
			HttpSession session = request.getSession();
			session.setAttribute("USER_SESSION", loggined_user);
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			model.addAttribute("login_state", "登入失敗");
			System.out.println("帳號密碼輸入錯誤");
			return "login";
		}
		System.out.println("帳密輸入成功");
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
