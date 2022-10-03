package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.IngredientDAO;
import com.project.domain.Food;
import com.project.domain.Ingredient;

@Service
public class IngredientServiceImpl implements IngredientService  {
	
	@Autowired
	private IngredientDAO ingdao;
	
	//재료 전부 선택(가격도)
	@Override
	public List<Ingredient> selectAllIngredient() {
		ArrayList<Ingredient> inglist=ingdao.selectAllIngredient();
		return inglist;
	}
}
