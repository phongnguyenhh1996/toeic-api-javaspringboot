package com.bezkoder.spring.jwt.mongodb.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.bezkoder.spring.jwt.mongodb.models.ERole;
import com.bezkoder.spring.jwt.mongodb.models.Role;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.payload.request.EditCurrentUserRequest;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.UserDetailResponse;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/user")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository roleRepository;
	
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
	@PutMapping("/edit-info")
	public ResponseEntity<?> editUserInfo(Principal principal, @Valid @RequestBody EditCurrentUserRequest editCurrentUserRequest){
		try {
			Optional<User> userCurrent = userRepository.findByUsername(principal.getName());
			User user = userCurrent.get();
			user.setEmail(editCurrentUserRequest.getEmail());
			user.setUsername(editCurrentUserRequest.getUsername());
			user.setPassword(encoder.encode(editCurrentUserRequest.getPassword()));
			Set<String> strRoles = editCurrentUserRequest.getRoles();
			Set<Role> roles = new HashSet<>();
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
	
						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
	
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}
	
			user.setRoles(roles);
			userRepository.save(user);
			return ResponseEntity.ok(new UserDetailResponse(user));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(e.toString()));
		}
		//return ResponseEntity.ok(new MessageResponse("message"));
	}
}
