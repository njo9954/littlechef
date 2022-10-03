package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.FoodDetail;

@Mapper
public interface FoodDetailDAO {
	List<FoodDetail> selectIngredientListByFoodId(int foodId);

	int insertFoodDetailList(List<FoodDetail> foodDetailList);
}
