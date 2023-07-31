package com.socialprotection.service;

import java.util.List;

import com.socialprotection.auth.RegisterRequest;
import com.socialprotection.entity.Role;
import com.socialprotection.entity.User;
import com.socialprotection.utils.StatusResponse;

public interface UserService {
	public User saveUser(User user);
	public List<User> findAllUser();
	public StatusResponse saveUser(RegisterRequest registerRequest);
	public Role saveRole(Role role);
	public List<Role> findAllRole();
}
