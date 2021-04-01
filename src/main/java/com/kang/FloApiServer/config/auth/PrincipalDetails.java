package com.kang.FloApiServer.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kang.FloApiServer.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	@Override// 계정의 비밀번호를 리턴한다.
	public String getPassword() {
		return user.getPassword();
	}

	@Override// 계정의 이름을 리턴한다.
	public String getUsername() {
		return user.getUsername();
	}

	public String getEmail() {
		return user.getEmail();
	}
	
	public String getRole() {
		return user.getRole().toString();
	}
	
	public int getId() {
		return user.getId();
	}
	
	
	@Override// 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	public boolean isAccountNonExpired() { 
		return true;   //안 쓸거면 그냥 true 
	}

	@Override// 계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
	public boolean isAccountNonLocked() { //예를 들어 아이디 3번 시도했는데 실패했으면 락인한다든가.. 그런 로직
		return true;
	}

	@Override// 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {// 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
		return true;
	}
	
	@Override// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("Role 검증 하는 중");
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->"ROLE_"+user.getRole().toString());
		
		return collectors;
	}

}
