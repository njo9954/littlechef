package com.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.Member;

@Mapper
public interface MemberDAO {

	int insertMember(Member member);
 
}
