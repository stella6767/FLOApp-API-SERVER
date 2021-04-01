package com.kang.FloApiServer.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthLoginRespDto {
	
	private int id;
	private String username;
	private String email;
	private String role;

}
