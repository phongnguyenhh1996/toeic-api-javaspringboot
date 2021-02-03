package com.bezkoder.spring.jwt.mongodb.controllers;

import java.security.Principal;
import java.util.Optional;

import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.UserDetailResponse;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
    @GetMapping("/me")
	public ResponseEntity<?> infoUser(Principal principal){
		try {
			Optional<User> userCurrent = userRepository.findByUsername(principal.getName());
			User user = userCurrent.get();
			return ResponseEntity.ok(new UserDetailResponse(user));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(e.toString()));
		}
		
	}
}
