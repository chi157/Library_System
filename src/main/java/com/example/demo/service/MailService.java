package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailService {

	private final JavaMailSender mailSender;

	@Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendPlainText(Collection<String> receivers, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receivers.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(content);
        message.setFrom("圖書館管理系統<bbcynthia1026@gmail.com>");

        mailSender.send(message);
    }
}