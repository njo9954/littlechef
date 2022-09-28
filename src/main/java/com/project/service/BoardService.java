package com.project.service;


import java.util.ArrayList;

import com.project.domain.Board;
import com.project.domain.Reply;
import com.project.util.PageNavigator;

public interface BoardService {
	//후기 게시판
	public int writeBoard(Board board);

	public Board selectOne(int boardnum);
	

	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);

	public int selectTotal();

	public ArrayList<Board> selectAll(PageNavigator navi, String type, String searchWord);

	public int updateHits(int b_num);
	
	public int delete(Board board);
	
	int updateboard(Board board);
	
	

	//후기 게시판 댓글 
	
	public int insertReply(Reply reply);
	
	public ArrayList<Reply> selectReply(int boardnum);

	public Reply selectOneReply(int replynum);

	public int deleteReply(Reply reply);

}
