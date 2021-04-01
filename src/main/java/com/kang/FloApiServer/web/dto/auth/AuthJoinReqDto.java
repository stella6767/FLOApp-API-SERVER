package com.kang.FloApiServer.web.dto.auth;

import com.kang.FloApiServer.domain.user.User;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class AuthJoinReqDto {

	private String username;
	private String password;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
	}
}