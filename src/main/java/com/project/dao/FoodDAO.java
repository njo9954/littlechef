package com.project.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

import com.project.domain.Board;
import com.project.domain.Food;

@Mapper
public interface FoodDAO {
	
	//레시피 목록 불러오기
	ArrayList<Food> getFoodList();
	
	//레시피 상세내역 불러오기
	Food getDetail(int food_id);

	int writeFood(Food food);

	String search(String searchWord);

	ArrayList<Board> selectboard(int food_id);

	int updateReadCount(int food_id);

}
