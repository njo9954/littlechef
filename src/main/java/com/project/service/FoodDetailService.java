package com.project.service;

import java.util.List;

import com.project.domain.FoodDetail;

public interface FoodDetailService {
	List<FoodDetail> selectIngredientListByFoodId(int foodId); 
}
