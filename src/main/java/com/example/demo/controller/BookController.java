package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookState;
import com.example.demo.entity.BookUser;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookStateRepository;
import com.example.demo.repository.BookUserRepository;
import com.example.demo.repository.PublisherRepository;

import org.springframework.mock.web.MockMultipartFile;

@Controller
@RequestMapping("/book-manage")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Autowired
	BookStateRepository bookStateRepository;
	
	@Autowired
	BookUserRepository bookUserRepository;
	
	@GetMapping({"/"}) 
	public String index(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("books", bookRepository.findAllAndOrderByActivate());
		model.addAttribute("publishers", publisherRepository.findAll());
		return "book-manage";
	}
	
	@PostMapping("/search")
	public String search(@ModelAttribute Book book, Model model) {
		model.addAttribute("books", null);
		model.addAttribute("publishers", publisherRepository.findAll());
		
		List<Book> books = bookRepository.findBySearchCriteria(
			    book.getISBN().isEmpty() ? null : "%" + book.getISBN() + "%",
			    book.getAuthor().isEmpty() ? null : "%" + book.getAuthor() + "%", 
			    book.getName().isEmpty() ? null : "%" + book.getName() + "%",
			    book.getPublisher().getId() == null ? null : book.getPublisher()
			);
		model.addAttribute("books", books);
		//return "redirect:/book-manage/";
		return "book-manage";
	}
	
	@GetMapping("/create-page")
	public String create_page(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("publishers", publisherRepository.findAll());
		return "book-create-page";
	}


	@PostMapping("/create-book")
    public String createBook(@ModelAttribute Book book, @RequestParam("picture") MultipartFile file) {
		BookState bookState = bookStateRepository.findById(1L).get();
		book.setBook_state(bookState);
        try {
            if (!file.isEmpty()) {
                byte[] pictureBytes = file.getBytes();
                book.setPicture(pictureBytes);
            }

            bookRepository.save(book);
        } catch (IOException e) {
            logger.error("Error saving book", e);
            return "error";
        }

        return "redirect:/book-manage/";
    }




	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit_book(@PathVariable("id") Long id,  Model model) {
		Book book = bookRepository.findById(id).get();
		//BookState bookState = bookStateRepository.findById(1L).get();
		model.addAttribute("book", book);
		//model.addAttribute("book_state_def", bookState);
		model.addAttribute("publishers", publisherRepository.findAll());
		model.addAttribute("book_states", bookStateRepository.findAll());
		return "book-edit-page";
	}
	
	@PostMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Book book, @RequestParam("picture") MultipartFile file) {
		// 原本的
		Book book_o = bookRepository.findById(id).get();
		BookState bookState1 = bookStateRepository.findById(1L).get(); // 書在館
		book.setId(id);
		try {
            if (!file.isEmpty()) {
                byte[] pictureBytes = file.getBytes();
                book.setPicture(pictureBytes);
            }else {
            	book.setPicture(book_o.getPicture());
            }

        } catch (IOException e) {
            logger.error("Error saving book", e);
            return "error";
        }
		
		// 這邊是系統後台修改，如果修改借閱狀態，要同時修改借閱資料表
		if(book.getBook_state().equals(bookState1)) {
			BookUser bookUser = bookUserRepository.findByBookAndActualReturnDate(book.getId().toString());
			bookUser.setActionTime(new Date());
			bookUser.setActualReturnDate(new Date());
			bookUserRepository.save(bookUser);
		}
		
        bookRepository.save(book);
		return "redirect:/book-manage/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
		return "redirect:/book-manage/";
	}
	
	
	
}