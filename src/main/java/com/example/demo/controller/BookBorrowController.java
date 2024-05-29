package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookState;
import com.example.demo.entity.BookUser;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookStateRepository;
import com.example.demo.repository.BookUserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book-borrow")
public class BookBorrowController {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookUserRepository bookUserRepository;
	
	@Autowired
	BookStateRepository bookStateRepository;
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("books", bookRepository.findByActivateBook());
		return "book-borrow";
	}
	
	@GetMapping("/borrow/{id}") // 借這本書
	public String borrow_book(@PathVariable("id") Long id,  Model model, HttpSession session) {
		// 點擊借閱按鈕直接借閱
		Book book = bookRepository.findById(id).get();
		BookState bookState = bookStateRepository.findById(2L).get();
		book.setBook_state(bookState);
		bookRepository.save(book);
		User user =(User) session.getAttribute("USER_SESSION");
		BookUser bookUser = new BookUser();
		
		bookUser.setBook(book);
		bookUser.setUser(user);
		bookUser.setBorrowDate(new Date());
		bookUser.setReturnDate(new Date(new Date().getTime() + (long)15 * 24 * 60 * 60 * 1000));
		bookUser.setIsLost(false);
		bookUser.setActionTime(new Date());
		bookUserRepository.save(bookUser);
		//model.addAttribute("book", new Book());
		return "redirect:/book-borrow-record/";
	}
	
	@GetMapping("/reserve/{id}") // 預約這本書
	public String reserve_book(Model model) {
		// 點擊借閱按鈕直接預約
		model.addAttribute("book", new Book());
		return "book-borrow-record";
	}
	

	
	@GetMapping("/return/{id}") // 還這本書
	public String return_book(@PathVariable("id") Long id,  Model model, HttpSession session, HttpServletRequest request) {
		// 點擊借閱按鈕直接歸還
		BookUser bookUser = bookUserRepository.findById(id).get();
		Book book = bookUser.getBook();
		BookState bookState = bookStateRepository.findById(1L).get();
		book.setBook_state(bookState);
		bookRepository.save(book);
		User user =(User) session.getAttribute("USER_SESSION");
		
		bookUser.setActualReturnDate(new Date());
		bookUser.setActionTime(new Date());
		bookUserRepository.save(bookUser);
		//model.addAttribute("book", new Book());

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	

	@GetMapping("/lost/{id}") // 設定書籍遺失
	public String book_lost(@PathVariable("id") Long id,  Model model, HttpSession session, HttpServletRequest request) {
		
		BookUser bookUser = bookUserRepository.findById(id).get();
		Book book = bookUser.getBook();
		BookState bookState = bookStateRepository.findById(4L).get();
		book.setBook_state(bookState);
		bookRepository.save(book);
		User user =(User) session.getAttribute("USER_SESSION");
		
		bookUser.setIsLost(true);
		bookUser.setActionTime(new Date());
		bookUserRepository.save(bookUser);
		//model.addAttribute("book", new Book());

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
