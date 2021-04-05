package com.kang.FloApiServer.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.config.auth.PrincipalDetails;
import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.service.UserService;
import com.kang.FloApiServer.web.dto.CMRespDto;
import com.kang.FloApiServer.web.dto.auth.AuthJoinReqDto;
import com.kang.FloApiServer.web.dto.auth.AuthLoginRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user/{id}") 
	public CMRespDto<?> findById(@PathVariable int id) {
		
		User user = userService.유저정보(id);
		
		AuthLoginRespDto authLoginRespDto = new AuthLoginRespDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole().toString());
		
		return new CMRespDto<>(1, "유저 정보 불러오기 성공", authLoginRespDto);
	}

	
	
	@PutMapping("/user/{id}")
	public CMRespDto<?> profileUpdate(@PathVariable int id, @RequestBody AuthJoinReqDto authJoinReqDto,@AuthenticationPrincipal PrincipalDetails details ) {
		
		System.out.println(authJoinReqDto);
		
		User userEntity = userService.회원수정(id, authJoinReqDto);
		details.setUser(userEntity);
		
		AuthLoginRespDto authLoginRespDto = new AuthLoginRespDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getRole().toString());
		
		return new CMRespDto<>(1, "유저정보 수정 성공", authLoginRespDto);
	}
	
	

}
