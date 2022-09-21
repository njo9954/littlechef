package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.Food;
import com.project.domain.Member;

@Mapper
public interface FoodDAO {
	
	//레시피 목록 불러오기
	ArrayList<Food> getFoodList();
	
	//레시피 상세내역 불러오기
	Food getDetail(int food_id);
	
	
	
}
