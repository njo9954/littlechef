package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.FoodDAO;
import com.project.domain.Food;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDAO fooddao;

	@Override
	public ArrayList<Food> foodList() {
		
		ArrayList<Food> foodlist=fooddao.getFoodList();
		
		return foodlist;
	}

	@Override
	public Food getDetail(int food_id) {
		
		Food food = fooddao.getDetail(food_id);
		
		return food;
	}
	
}
