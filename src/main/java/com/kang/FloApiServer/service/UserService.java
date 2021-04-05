package com.kang.FloApiServer.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.domain.user.UserRepository;
import com.kang.FloApiServer.web.dto.auth.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, AuthJoinReqDto authJoinReqDto) {
		
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		}); //1차 캐시
		String encPassword = bCryptPasswordEncoder.encode(authJoinReqDto.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setEmail(authJoinReqDto.getEmail());
		userEntity.setUsername(authJoinReqDto.getUsername());
		
		return userEntity;
	}//더티체킹
	
	
	@Transactional(readOnly = true)
	public User 유저정보(int id) {
		
		return userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
	}
	

}
