package com.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.domain.Cscenter;
import com.project.domain.Csreply;


@Mapper
public interface CsDAO {
	//글 저장
			public int writeBoard(Cscenter cscenter);
			//글 읽기
			public Cscenter selectOne(int c_num); // boardnum
			

			// 후기 게시판
			public int count(HashMap<String, String> map);
			
			public ArrayList<Cscenter> selectAll(HashMap<String, String> map, RowBounds rb);
			
			public int selectTotal();
		
			public int delete(Cscenter cscenter); //board
			
			public int updateboard(Cscenter cscenter); //board
			
			//후기 게시판  댓글 
			public int insertReply(Csreply csreply); // csreply
			
			public ArrayList<Csreply> selectReply(int c_num);
			
			public Csreply selectOneReply(int cr_num); // int replynum
			 
			public int deleteReply(Csreply csreply);
	}
