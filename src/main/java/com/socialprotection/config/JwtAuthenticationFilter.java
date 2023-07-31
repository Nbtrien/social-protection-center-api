package com.socialprotection.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialprotection.entity.Token;
import com.socialprotection.repository.TokenRepository;
import com.socialprotection.service.JwtService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			String token = authHeader.substring(7);
			String userName = jwtService.extractUsername(token);
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
				if (jwtService.isTokenValid(token, userDetails)) {
					Token storedToken = tokenRepository.findByToken(token).orElse(null);
					if (storedToken.isExpired()) {
						response.setStatus(HttpStatus.FORBIDDEN_403);
						Map<String, String> error = new HashMap<String, String>();
						error.put("error message", "token is expired");
						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
						new ObjectMapper().writeValue(response.getOutputStream(), error);
					}
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				} else {
					response.setStatus(HttpStatus.FORBIDDEN_403);
					Map<String, String> error = new HashMap<String, String>();
					error.put("error message", "token is not valid");
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				}
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.setHeader("error", e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN_403);
			Map<String, String> error = new HashMap<String, String>();
			error.put("error message", e.getMessage());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), error);
		}
	}

}
