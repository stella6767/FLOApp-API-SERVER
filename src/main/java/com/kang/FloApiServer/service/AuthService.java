package com.kang.FloApiServer.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.FloApiServer.domain.user.RoleType;
import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Transactional
	public void 회원가입(User user) { //안드로이드에게 던질 때는 리턴 필요
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		System.out.println("user: "+user);
		userRepository.save(user);
		
	}
	
}
