package com.project.service;


import java.util.ArrayList;

import com.project.domain.Cscenter;
import com.project.domain.Csreply;
import com.project.util.PageNavigator;

public interface CscenterService {

	//후기 게시판
		public int cswrite(Cscenter cscenter);

		public Cscenter selectOne(int c_num);
		

		public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);

		public int selectTotal();

		public ArrayList<Cscenter> selectAll(PageNavigator navi, String type, String searchWord);

		
		public int delete(Cscenter cscenter);
		
		int csupdate(Cscenter cscenter);
		
		

		//후기 게시판 댓글 
		
		public int insertReply(Csreply csreply);
		
		public ArrayList<Csreply> selectReply(int c_num);

		public Csreply selectOneReply(int cr_num);

		public int deleteReply(Csreply csreply);

		public int findcenter(Cscenter cscenter);



}
