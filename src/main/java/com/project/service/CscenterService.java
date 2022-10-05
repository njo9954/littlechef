package com.project.service;


import com.project.domain.Cscenter;

public interface CscenterService {

	
	//후기 게시판
		public int cswrite(Cscenter cssenter); // Board board

		public Cscenter selectOne(int c_num);  // boardnum 
		
		public int selectTotal();



}
