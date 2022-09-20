package com.project.service;

import com.project.domain.Board;

public interface BoardService {
	//글 저장
	public int writeBoard(Board board);

	public Board selectOne(int boardnum);

}
