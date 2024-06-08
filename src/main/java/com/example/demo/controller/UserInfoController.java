package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Passwrod;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

 
@Controller
@RequestMapping("/user-info")
public class UserInfoController {
 
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
        return "user-info";
    }
	@PostMapping("/edit") // 對資料庫進行修改，修改個人資料
	public String update(User user, HttpSession session, Model model) {
		try {
			User u =(User) session.getAttribute("USER_SESSION");
			user.setId(u.getId());
			user.setPassword(u.getPassword());
			user.setRecords(u.getRecords());
			user.setRole(u.getRole());
			session.setAttribute("USER_SESSION", user);
			userRepository.save(user);
			model.addAttribute("edit_state", "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("edit_state", "修改失敗");
		}
		
		return "user-info";
	}
	@GetMapping("/pwd/") // 修改密碼畫面 
    public String edit_pwd_page(Model model, HttpSession session) {
		Passwrod password = new Passwrod();
		model.addAttribute("password", password);
		//model.addAttribute("user", new User());
        return "user-info-pwd";
    }
	@PostMapping("/pwd/edit") // 修改密碼
    public String edit_pwd(Passwrod passwrod,Model model, HttpSession session, HttpServletRequest request) {
		User user =(User) session.getAttribute("USER_SESSION");
		if(passwrod.getOriginal_pwd().equals(user.getPassword())) {
			if(passwrod.getNew_pwd().equals(passwrod.getCheck_pwd())) {
				user.setPassword(passwrod.getNew_pwd());
				userRepository.save(user);
				model.addAttribute("edit_state", "修改成功");
				request.getSession().invalidate();
				return "index";
			}else {
				model.addAttribute("edit_state", "修改失敗1");
				Passwrod password = new Passwrod();
				model.addAttribute("password", password);
				return "user-info-pwd";
			}
		}else {
			model.addAttribute("edit_state", "修改失敗2");
			Passwrod password = new Passwrod();
			model.addAttribute("password", password);
			return "user-info-pwd";
		}
		//model.addAttribute("user", new User());
    }
}
