package com.kang.FloApiServer.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service  //메모리에 안 떠서 문제!, 꼭 붙여넣어라.
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override //Authentication Maneger 가 보내줌
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("/login 이 호출되면 자동 실행되어 username 이 DB에 있는지 확인한다.");
		User principal = userRepository.findByUsername(username);
		
		if(principal == null) {
			return null;
		}else {
			//session.setAttribute("principal",principal); - jsp 아니라면 세션에 저장하고 사용해야된다.
			return new PrincipalDetails(principal);
		}
	}
}