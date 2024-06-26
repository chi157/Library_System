package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@ComponentScan("com.example.demo.repository")
@Configuration
public class SpringBootJpaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ByteArrayMultipartFileConverter());
    }
	
	@Bean
    public ImageUtils imageUtils() {
        return new ImageUtils();
    }
}
