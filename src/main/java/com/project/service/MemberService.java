package com.project.service;

import org.springframework.stereotype.Service;

import com.project.domain.Member;

public interface MemberService {
	/**
	 * 회원가입
	 * @param member 저장할 회원 정보
	 * @return 저장된 행 개수
	 */
	public int insertMember(Member member);

	public boolean idcheck(String searchId);
		
}
