package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dao.MemberDAO;
import com.project.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public int insertMember(Member member) {
		return 0;
	}
	
}
