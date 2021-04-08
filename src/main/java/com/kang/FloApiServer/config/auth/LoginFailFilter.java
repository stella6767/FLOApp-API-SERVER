package com.kang.FloApiServer.config.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.FloApiServer.web.dto.CMRespDto;

//@Component
//public class LoginFailFilter extends OncePerRequestFilter {
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		CMRespDto<?> cmRespDto = new CMRespDto<>(1, "login fail", null);
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonDto = mapper.writeValueAsString(cmRespDto);
//
//		PrintWriter out = response.getWriter();
//
//		out.print(jsonDto);
//		out.flush();
//
//		 filterChain.doFilter(request,response);
//		
//		
//	}
//
//}
