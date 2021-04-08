package com.kang.FloApiServer.config.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.FloApiServer.web.dto.CMRespDto;

public class AuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub

		System.out.println("왜 안되는거지?");

		// 로그인 실패 시 처리할 내용을 작성하여 확장할 수 있다.

		// 응답으로
		CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "login fail", null);
		ObjectMapper mapper = new ObjectMapper();
		String jsonDto = mapper.writeValueAsString(cmRespDto);

		PrintWriter out = response.getWriter();

		out.print(jsonDto);
		out.flush();

		return;
		
		//provider manager가 여기서 리턴

	}

}