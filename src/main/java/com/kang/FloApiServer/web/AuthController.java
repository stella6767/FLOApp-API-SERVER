package com.kang.FloApiServer.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.service.AuthService;
import com.kang.FloApiServer.web.dto.CMRespDto;
import com.kang.FloApiServer.web.dto.auth.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController    
public class AuthController {
	
	private final AuthService authService;
	
	// 주소: 인증안되어있을 때 /user, /post,  인증이 되든 말든 무조건 온다. /loginForm
//	@GetMapping("/loginForm")
//	public String loginForm() {
//		return "auth/loginForm";
//	}
//	
//	@GetMapping("/joinForm")
//	public String joinForm() {
//		return "auth/joinForm";
//	}
//	
	
	@PostMapping("/join")
	public CMRespDto<?> join(@RequestBody AuthJoinReqDto authJoinReqDto) {	
		System.out.println(authJoinReqDto);
		
		authService.회원가입(authJoinReqDto.toEntity());
		return new CMRespDto<>(1, "회원가입에 성공 하였습니다.", null); 
	}
	
	
}
