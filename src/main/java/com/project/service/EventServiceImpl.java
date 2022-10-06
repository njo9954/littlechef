package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.MemberDAO;
import com.project.domain.Member;


@Service
public class EventServiceImpl implements EventService
{

	@Autowired
	private MemberDAO memberdao;
	



	@Override
	public int insertcoupon(Member member) {
		int  result = memberdao.insertcoupon(member);
		return result;
		
	}
}