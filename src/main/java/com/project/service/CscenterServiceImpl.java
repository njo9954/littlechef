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
		int result = csDAO.cswrite(cssenter);
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
		@Override
		public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
				String searchWord) {
			HashMap<String, String> map = new HashMap<>();
			map.put("type", type);
			map.put("searchWord", searchWord);
			
			int total = csDAO.count(map);
			PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
			return navi;
		}
		@Override
		public ArrayList<Cscenter> selectAll(PageNavigator navi, String type, String searchWord) {
			HashMap<String, String> map = new HashMap<>();
			map.put("type", type);
			map.put("searchWord", searchWord); // Map에 담을 때 이름으로 꺼내서 써야 함. "type", "searchWord"
			
			RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
			
			ArrayList<Cscenter> cslist = csDAO.selectAll(map, rb); //list
			return cslist;
		}
		@Override
		public int delete(Cscenter cscenter) {
			int result = csDAO.delete(cscenter);
			return result;
		}
		@Override
		public int csupdate(Cscenter cscenter) {
			int result = csDAO.updateboard(cscenter);
			return result;
		}
		@Override
		public int insertReply(Csreply csreply) {
			int result = csDAO.insertReply(csreply);
			return result;
		}
		@Override
		public ArrayList<Csreply> selectReply(int c_num) {
			ArrayList<Csreply> list = csDAO.selectReply(c_num);
			return list;
		}
		@Override
		public Csreply selectOneReply(int cr_num) {
			Csreply csreply = csDAO.selectOneReply(cr_num);
			return csreply;
		}
		@Override
		public int deleteReply(Csreply csreply) {
			int result = csDAO.deleteReply(csreply);
			return result;
		}
		@Override
		public int findcenter(Cscenter cscenter) {
			int result = csDAO.findcenter(cscenter);
			return result;
		}

}
