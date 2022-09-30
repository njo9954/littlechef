package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.project.domain.Food;

public interface FoodService {
	
	//레시피 이름 불러오기
	ArrayList<Food> foodList();
	//레시피 상세 내용 불러오기
	Food getDetail(int food_id);
	int writeFood(Food food);
	//레시피 찾기
	String search(String searchWord);
	

}
