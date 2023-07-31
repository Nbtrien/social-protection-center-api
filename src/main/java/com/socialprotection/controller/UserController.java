package com.socialprotection.controller;

import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.socialprotection.auth.RegisterRequest;
import com.socialprotection.entity.Role;
import com.socialprotection.entity.User;
import com.socialprotection.service.UserService;
import com.socialprotection.utils.StatusResponse;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/users" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUser(){
		return  ResponseEntity.ok(userService.findAllUser());
	}
	
	@PostMapping(value = "/users" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResponse> saveUser(@RequestBody RegisterRequest registerRequest){
		return  ResponseEntity.ok(userService.saveUser(registerRequest));
	}
	
	@GetMapping(value = "/roles" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> getAllRole() {
		return ResponseEntity.ok(userService.findAllRole());
	}
}
