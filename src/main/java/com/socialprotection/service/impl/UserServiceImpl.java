package com.socialprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialprotection.auth.RegisterRequest;
import com.socialprotection.entity.Role;
import com.socialprotection.entity.User;
import com.socialprotection.repository.RoleRepository;
import com.socialprotection.repository.UserRepository;
import com.socialprotection.service.UserService;
import com.socialprotection.utils.StatusResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		user.setPassWord(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public StatusResponse saveUser(RegisterRequest request) {
		User user = new User();
		user.setUserName(request.getUserName());
		user.setPassWord(request.getPassWord());
		user.setRole(request.getRole());
		user.setPassWord(passwordEncoder.encode(user.getPassword()));
		if (userRepository.save(user) != null)
			return new StatusResponse(true, "Add new user successful!");
		else
			return new StatusResponse(false, "Add new user fail!");
	}

	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public List<User> findAllUser() {
		List<User> users = userRepository.findAll();
		return users;
	}

}
