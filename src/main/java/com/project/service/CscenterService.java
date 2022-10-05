package com.project.service;

import java.util.ArrayList;

import com.project.domain.Csreply;
import com.project.domain.Cscenter;
import com.project.util.PageNavigator;

public interface CscenterService {

	
	//후기 게시판
		public int cswrite(Cscenter cssenter); // Board board

		public Cscenter selectOne(int c_num);  // boardnum 
		
		public int selectTotal();


		
		


}
