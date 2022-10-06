package com.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.domain.Cscenter;
import com.project.domain.Csreply;


@Mapper
public interface CsDAO {
			
	public int cswrite(Cscenter cscenter);   // 문의 저장
			
			public Cscenter selectOne(int c_num); // boardnum  문의 읽기 
			

			public int count(HashMap<String, String> map); // 문의 게시판 
			
			public ArrayList<Cscenter> selectAll(HashMap<String, String> map, RowBounds rb);
			
			public int selectTotal();
		
			public int delete(Cscenter cscenter); //board
			
			public int updateboard(Cscenter cscenter); //board
			
			//후기 게시판  댓글 
			public int insertReply(Csreply csreply); // csreply
			
			public ArrayList<Csreply> selectReply(int c_num);
			
			public Csreply selectOneReply(int cr_num); // int replynum
			 
			public int deleteReply(Csreply csreply);

			public int findcenter(Cscenter cscenter);
	}
