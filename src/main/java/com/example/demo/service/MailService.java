package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LibraryRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailService {

	private final JavaMailSender mailSender;
	
	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendPlainText(Collection<String> receivers, String subject, String content) {
    	String nameString = libraryRepository.findById(1L).get().getName();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receivers.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(nameString + "<bbcynthia1026@gmail.com>");

        mailSender.send(message);
    }
}