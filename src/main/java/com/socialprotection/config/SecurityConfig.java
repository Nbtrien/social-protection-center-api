package com.socialprotection.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/home").permitAll();
		http.authorizeRequests().antMatchers("/api/login").permitAll();
		http.authorizeRequests().antMatchers("/api/test").authenticated();
		http.authorizeRequests().anyRequest().permitAll();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authenticationProvider(authenticationProvider);
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		http.logout().logoutUrl("/api/logout").addLogoutHandler(logoutHandler)
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
		System.out.println("filter");
		return http.build();
	}
}
