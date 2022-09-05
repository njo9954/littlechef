package com.project.domain;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 정보
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3222388532456457383L; // 접근불가 __ 한번대입한 값 붙일 수 없음 자료형x // 값 저장하고 읽기만 하려고 static final
	String memberid;		// 사용자 식별ID
	String memberpw;		// 비밀번호
	String membername;		// 사용자 이름
	String email;			// 이메일주소
	String phone;			// 전화번호
	String address;			// 집주소
	boolean enabled;			// 계정상태. 1:사용가능, 0:사용불가능
	String rolename;		// 사용자 구분. 'ROLE_USER', 'ROLE_ADMIN'중 하나
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

}