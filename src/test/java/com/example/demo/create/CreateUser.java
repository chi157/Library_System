package com.example.demo.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class CreateUser {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	

	
	@Test
	public void test() {
//		Role role1 = new Role();
//		role1.setName("管理員");
//		Role role2 = new Role();
//		role2.setName("圖書館職員");
//		Role role3 = new Role();
//		role3.setName("讀者");
//		
//		
//		User user1 = new User();
//		user1.setAccount("A11223021");
//		user1.setPassword("1026");
//		user1.setName("張謦麒");
//		user1.setRole(role1);
//		
//		User user2 = new User();
//		user2.setAccount("A11223003");
//		user2.setPassword("A11223003");
//		user2.setName("吳霜");
//		user2.setRole(role1);
//	
//		User user3 = new User();
//		user3.setAccount("A11223030");
//		user3.setPassword("A11223030");
//		user3.setName("吳易陞");
//		user3.setRole(role1);
	
//		
//		
//		roleRepository.save(role1);
//		roleRepository.save(role2);
//		roleRepository.save(role3);
//		userRepository.save(user1);
//		userRepository.save(user2);
//		userRepository.save(user3);
		
	}
}
