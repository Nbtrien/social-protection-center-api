package com.socialprotection.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialprotection.entity.Token;
import com.socialprotection.repository.TokenRepository;
import com.socialprotection.utils.StatusResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		response.setHeader("Access-Control-Allow-Origin", "/**");
		response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		if (request.getMethod() == "OPTIONS") {
	         response.setStatus(HttpStatus.OK_200);
	    }
		final String authHeader = request.getHeader("Authorization");
		final String token;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		token = authHeader.substring(7);
		String userName = jwtService.extractUsername(token);
		Token storedToken = tokenRepository.findByToken(token).orElse(null);
		if (storedToken != null) {
			storedToken.setExpired(true);
			storedToken.setRevoked(true);
			tokenRepository.save(storedToken);
			SecurityContextHolder.clearContext();
			
			response.setStatus(HttpStatus.OK_200);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			try {
				new ObjectMapper().writeValue(response.getOutputStream(), new StatusResponse(true, "Logout Successful!"));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
