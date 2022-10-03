package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.FoodDetailDAO;
import com.project.domain.FoodDetail;
import com.project.service.FoodDetailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodDetailServiceImpl implements FoodDetailService{
	@Autowired
	private FoodDetailDAO foodDetailDAO;

	@Override
	public List<FoodDetail> selectIngredientListByFoodId(int foodId) {
		log.debug("selectIngredientListByFoodId() called");
		return foodDetailDAO.selectIngredientListByFoodId(foodId);
	}

}


