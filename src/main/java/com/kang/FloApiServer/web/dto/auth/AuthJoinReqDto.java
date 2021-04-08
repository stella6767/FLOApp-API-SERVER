package com.kang.FloApiServer.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kang.FloApiServer.domain.user.User;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class AuthJoinReqDto {

	@NotBlank(message = "유저네임을 입력해주세요.")
	@Size(max = 20,message = "유저네임 길이를 초과하였습니다.")
	private String username;
	
	@NotBlank(message = "유저네임을 입력해주세요.")
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