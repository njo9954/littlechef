package com.project.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.Ingredient;

@Mapper
public interface IngredientDAO {

	ArrayList<Ingredient> selectAllIngredient();


}
