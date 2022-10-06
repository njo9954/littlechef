package com.project.service;

import java.util.ArrayList;

import com.project.domain.Board;
import com.project.domain.Food;

public interface FoodService {
	
	//레시피 이름 불러오기
	ArrayList<Food> foodList();
	//레시피 상세 내용 불러오기
	Food getDetail(int food_id);
	
	int writeFood(Food food, String[] arrIname, String[] arrQty) throws Exception ;
	//레시피 찾기
	ArrayList<Food> search(String searchWord);

	ArrayList<Board> selectboard(int food_id);
	
	//레시피 글 클릭시 조회수 증가
	int updateReadCount(int food_id);
	

}
