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
public class Member implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3222388532456457383L;
	
	 // 접근불가 __ 한번대입한 값 붙일 수 없음 자료형x // 값 저장하고 읽기만 하려고 static final
	String m_id;			// 사용자 식별ID
	int auth_id;		// 관리자 식별ID
	String m_pw;		// 사용자 비밀번회
	String m_name;		// 사용자 이름 
	String m_gender;	// 사용사 성별
	String m_address;	// 사용자 주소 		
	String m_phone;		// 사용자 핸드폰번호
	String m_email;		// 사용자 이메일 
	String m_grade;		// 사용자 맴버등급
	String m_allergy ;	// 사용자 알러지 정보
	boolean enabled;    // 계정상태 1: 사용가능 , 0: 사용불가능 
	int m_birth; 		// 사용자 생년월일	
	int m_cupon; 		// 사용자 쿠폰 갯수
	int m_mileage; 		// 사용자 마일리지 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		
		return this.m_pw;
	}
	@Override
	public String getUsername() {
		return this.m_id;
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
	@Override
	public boolean isEnabled() {
		return this.enabled; // 1이면 true 리턴, 0이면 false 리턴
	} // getter setter와 상관없이 security에서는 얘네로 정보를 읽어감
}


