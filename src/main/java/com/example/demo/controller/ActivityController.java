package com.example.demo.controller;


import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Activity;
import com.example.demo.entity.Book;
import com.example.demo.repository.ActivityRepository;


@Controller
@RequestMapping("/activity-manage")
public class ActivityController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	ActivityRepository activityRepository;
	
	@GetMapping({"/"}) 
	public String index(Model model) {
		//model.addAttribute("activities", activityRepository.findAll());
		model.addAttribute("activities", activityRepository.findAll());
		return "activity-manage";
	}
	// 新增活動頁面
	@GetMapping("/create-page")
	public String create_page(Model model) {
		model.addAttribute("activity", new Activity());
		return "activity-create-page";
	}
	// 新增活動按下去
	@PostMapping("/create-activity")
    public String createActivity(@ModelAttribute Activity activity, @RequestParam("picture") MultipartFile file, Model model, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime birthday) {
		try {
            if (!file.isEmpty()) {
                byte[] pictureBytes = file.getBytes();
                activity.setPicture(pictureBytes);
            }

        } catch (IOException e) {
            //logger.error("Error saving activity", e);
        	model.addAttribute("error", e);
            System.out.println(e);
            return "error";
        }

        activityRepository.save(activity);
        return "redirect:/activity-manage/";
    }
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit_activity(@PathVariable("id") Long id,  Model model) {
		Activity activity = activityRepository.findById(id).get();
		model.addAttribute("activity", activity);
		return "activity-edit-page";
	}
	
	@PostMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Activity activity, @RequestParam("picture") MultipartFile file) {
		Activity activity_o = activityRepository.findById(id).get();
		activity.setId(id);
		try {
            if (!file.isEmpty()) {
                byte[] pictureBytes = file.getBytes();
                activity.setPicture(pictureBytes);
            }else {
            	activity.setPicture(activity_o.getPicture());
            }

        } catch (IOException e) {
            return "error";
        }
		activityRepository.save(activity);
		return "redirect:/activity-manage/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		activityRepository.deleteById(id);
		return "redirect:/activity-manage/";
	}
	
}