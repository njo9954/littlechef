package com.project.service;


import java.util.ArrayList;

import com.project.domain.Board;
import com.project.util.PageNavigator;

public interface BoardService {
	//글 저장
	public int writeBoard(Board board);

	public Board selectOne(int boardnum);

	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);

	public int selectTotal();

	public ArrayList<Board> selectAll(PageNavigator navi, String type, String searchWord);

	public int updateHits(int b_num);

}
