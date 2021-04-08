package com.kang.FloApiServer.config.auth.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.handler.MyAuthenticationException;
import com.kang.FloApiServer.web.dto.CMRespDto;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HandlerInterceptor() {
//
//			@Override
//			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//					throws Exception {
//				
//				System.out.println("인터셉터 테스트");
//				
//				String username = request.getParameter("username"); // request에서 getParameter를 사용하여 "username"에 대한 정보를 가져올 수 있다.
//				System.out.println(username);
//
//				// 로그인 실패 시 처리할 내용을 작성하여 확장할 수 있다.
//
//				// 응답으로
//				CMRespDto<?> cmRespDto = new CMRespDto<>(1, "login fail", null);
//				ObjectMapper mapper = new ObjectMapper();
//				String jsonDto = mapper.writeValueAsString(cmRespDto);
//
//				PrintWriter out = response.getWriter();
//
//				out.print(jsonDto);
//				out.flush();
//
//				return true;						
//
//			}
//
//		}).addPathPatterns("/test");
//
//	}
//
//}
