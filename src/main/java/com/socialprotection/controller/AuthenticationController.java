package com.socialprotection.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialprotection.auth.AuthenticationRequest;
import com.socialprotection.auth.AuthenticationResponse;
import com.socialprotection.service.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
		return  ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
	}

	@CrossOrigin
	@RequestMapping(value = "/auth/checktoken", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> checkToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		System.out.println("TEST");
//		return ResponseEntity.ok("Authenticate");
	}

}
