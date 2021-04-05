package com.kang.FloApiServer.config.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.handler.MyAuthenticationException;

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
//				HttpSession session = request.getSession();
//				User principal = (User) session.getAttribute("principal");
//				
//				String method = request.getMethod();
//				System.out.println("메서드:  " + method);
//
//			if(principal == null && (method.equals("PUT") ||  method.equals("POST") || method.equals("DELETE") )) {
//					
//					throw new MyAuthenticationException();
//				}else { 
//					return true;
//				}						
//
//			}
//
//		}).addPathPatterns("/user/*");
//
//	}
//
//}
