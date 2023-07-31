package com.socialprotection.auth;

import com.socialprotection.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String userName;
	private String passWord;
	private Role role;

	public RegisterRequest() {
		super();
	}

	public RegisterRequest(String userName, String passWord, Role role) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public Role getRole() {
		return role;
	}

}
