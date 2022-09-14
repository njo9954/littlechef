package com.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.Member;

@Mapper
public interface MemberDAO {
	
	//회원가입
	int insertMember(Member member);
	
	//회원정보 조회
	Member selectOne(String id);
	
	//회원정보 수정
	int update(Member member);
	
	//회원정보 삭제
	int deleteMember(Member member);
	
	
}
