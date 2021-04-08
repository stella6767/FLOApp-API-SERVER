package com.kang.FloApiServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kang.FloApiServer.config.auth.AuthFailureHandler;
import com.kang.FloApiServer.config.auth.MyLoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.addFilterBefore(loginFailFiler, 
//                UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests()
		.antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //일단은 user 정보수정과 불러오기만 인증요구하도록
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //관리자만 들어올 수 있도록	    
		.anyRequest().permitAll()
		.and()
		.formLogin()//x-www-form-urlencoded, formlogin()은 json 던지면 못 받음
		.loginProcessingUrl("/login")//x-www-form-urlencoded, 시큐리티가 post로 온 /login 이라는 주소가 들어오면 낚아챔
		.failureHandler(new AuthFailureHandler()) 
		.successHandler(new MyLoginSuccessHandler());
		
	}
	
	
	
}
