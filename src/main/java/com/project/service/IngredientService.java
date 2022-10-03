package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.domain.Ingredient;

public interface IngredientService {
	
	// 모든 재료(가격) 가져오기
	List<Ingredient> selectAllIngredient();
}
