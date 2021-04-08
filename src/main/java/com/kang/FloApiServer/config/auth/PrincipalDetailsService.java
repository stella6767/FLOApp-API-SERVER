package com.kang.FloApiServer.config.auth;

import java.io.PrintWriter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.domain.user.UserRepository;
import com.kang.FloApiServer.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@Service // 메모리에 안 떠서 문제!, 꼭 붙여넣어라.
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override // Authentication Maneger 가 보내줌
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("/login 이 호출되면 자동 실행되어 username 이 DB에 있는지 확인한다.");
		User principal = userRepository.findByUsername(username);

		if (principal == null) {

//			CMRespDto<?> cmRespDto = new CMRespDto<>(1, "login fail", null);
//			ObjectMapper mapper = new ObjectMapper();
//			String jsonDto = mapper.writeValueAsString(cmRespDto);
//
//			PrintWriter out = response.getWriter();
//
//			out.print(jsonDto);
//			out.flush();

//			throw new IllegalArgumentException("아이디 또는 패스워드가 틀립니다");
			return null;

		} else {
			// session.setAttribute("principal",principal); - jsp 아니라면 세션에 저장하고 사용해야된다.
			return new PrincipalDetails(principal);
		}
	}
}