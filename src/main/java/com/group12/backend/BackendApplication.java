package com.group12.backend;

import com.group12.backend.domain.AppUser;
import com.group12.backend.domain.Role;
import com.group12.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
//CREEER DE GEBRUIKERS (ALLEEN EEN KEER DRAAIEN DAARNA UITCOMMENTEN)
//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.saveUser(new AppUser(null, "Johhny the abbreviation guy", "John", "1245", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "William jackson", "William", "1245", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "Leonard Bell", "Leonard", "1245", new ArrayList<>()));
//
//			userService.addRoleToUser("Leonard", "ROLE_ADMIN");
//			userService.addRoleToUser("William", "ROLE_ADMIN");
//			userService.addRoleToUser("John", "ROLE_ADMIN");
//		};
//	}
}
