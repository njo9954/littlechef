package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dao.MemberDAO;
import com.project.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired 
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int insertMember(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getM_pw()); // 암호화해주는 메소드 // 사용자가 입력한 비밀번호 암호화해서 전달
		member.setM_pw(encodedPassword);// 그 멤버에 도로 집어넣음. 암호화된 다른 비밀번호로 set
		
		int result = memberDAO.insertMember(member);
		return result;
	}
}
	

