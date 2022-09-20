package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Board;
import com.project.dao.BoardDAO;
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	//후기 저장
	@Override	
	public int writeBoard(Board board) {
	int result = boardDAO.writeBoard(board);
	return result;
	}
	// 후기 검색
	@Override
	public Board selectOne(int boardnum) {
		Board board = boardDAO.selectOne(boardnum);
		return board;
	}

	
}
