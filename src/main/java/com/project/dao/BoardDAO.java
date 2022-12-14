package com.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import com.project.domain.Board;
import com.project.domain.Reply;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.domain.Board;
@Mapper
public interface BoardDAO { //  후기 게시판 
		//글 저장
		public int writeBoard(Board board);
		//글 읽기
		public Board selectOne(int boardnum);
		
		
		
		// 후기 게시판
		public int count(HashMap<String, String> map);
		
		public ArrayList<Board> selectAll(HashMap<String, String> map, RowBounds rb);
		
		public int selectTotal();
	
		public int updateHits(int boardnum);
	
		public int delete(Board board);
		
		public int updateboard(Board board);
		
		//후기 게시판  댓글 
		public int insertReply(Reply reply);
		
		public ArrayList<Reply> selectReply(int boardnum);
		
		public Reply selectOneReply(int replynum);
		
		public int deleteReply(Reply reply);
}
