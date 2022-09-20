package com.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import com.project.domain.Board;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.project.domain.Board;
@Mapper
public interface BoardDAO { //  후기 게시판 
		//글 저장
		public int writeBoard(Board board);
		//글 개수
//		public int count(HashMap<String, String> map);
		//글 목록
//		public ArrayList<Board> selectAll(HashMap<String, String> map, RowBounds rb);
		//글 읽기
		public Board selectOne(int boardnum);
		//조회수 증가
/*		public int updateHits(int boardnum);
		
		public int selectTotal();
		
		public int delete(Board board);
		
		public int update(Board board);
*/
}
