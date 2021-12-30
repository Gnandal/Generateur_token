package com.gev.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gev.auth.repository.UserRepository;
import com.gev.auth.user.User;

@SpringBootApplication
public class ServerAuthApplication implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ServerAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepo.save(new User( "gev", passwordEncoder.encode("1234"), "USER", "USER"));
		
		System.out.println(userRepo.findByUsername("gev").toString());
	}

}
