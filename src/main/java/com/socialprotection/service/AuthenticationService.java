package com.socialprotection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.socialprotection.auth.AuthenticationRequest;
import com.socialprotection.auth.AuthenticationResponse;
import com.socialprotection.entity.Token;
import com.socialprotection.entity.User;
import com.socialprotection.repository.TokenRepository;
import com.socialprotection.repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private TokenRepository tokenRepository;

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
		User user = userRepository.findByUserName(request.getUserName()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole().getRoleName());
	}
	
	public HttpStatus checkToken(String token) {
		return null;

	}

	private void revokeAllUserTokens(User user) {
		List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
			tokenRepository.save(token);
		});
	}

	private void saveUserToken(User user, String jwtToken) {
		Token token = new Token(jwtToken, false, false, user);
		tokenRepository.save(token);
	}

}
