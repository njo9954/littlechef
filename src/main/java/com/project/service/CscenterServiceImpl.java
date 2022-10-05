package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CsDAO;
import com.project.domain.Cscenter;
import com.project.domain.Csreply;
import com.project.util.PageNavigator;
@Service
public class CscenterServiceImpl implements CscenterService {

	@Autowired
	private CsDAO csDAO;
	
	//후기 저장
		@Override	
		public int cswrite(Cscenter cssenter) {
		int result = csDAO.writeBoard(cssenter);
		return result;
		}
		// 후기 검색
		@Override
		public Cscenter selectOne(int c_num) {
			Cscenter cssenter = csDAO.selectOne(c_num);
			return cssenter;
		}
	

		@Override
		public int selectTotal() {
			int result = csDAO.selectTotal();
			return result;
		}

}
