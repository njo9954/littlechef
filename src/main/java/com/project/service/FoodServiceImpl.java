package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.FoodDAO;
import com.project.domain.Food;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDAO fooddao;


	@Override
	public Food getDetail(int food_id) {
		log.debug("getDetail");
		Food food = fooddao.getDetail(food_id);
		
		return food;
	}

	@Override
	public int writeFood(Food food) {
		int result = fooddao.writeFood(food);
		return result;
		
	}


	@Override
	public ArrayList<Food> foodList() {
		ArrayList<Food> foodlist=fooddao.getFoodList();
		return foodlist;
	}

	@Override
	public ArrayList<Food> foodSearch(String type, String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
