package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dao.FoodDAO;
import com.project.dao.IngredientDAO;
import com.project.domain.Board;
import com.project.domain.Food;
import com.project.domain.FoodDetail;
import com.project.domain.Ingredient;
import com.project.service.BoardService;
import com.project.service.FoodDetailService;
import com.project.service.FoodService;
import com.project.service.IngredientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "recipeView")
public class FoodController {
	
	@Autowired
	FoodService foodservice;
	
	@Autowired
	FoodDAO fooddao;
	@Autowired
	BoardService service;
	
	@Autowired
	FoodDetailService foodDetailService;
	
	@Autowired
	IngredientService ingService;
	
	@GetMapping("/writeFood")
	public String writeFood(Model model) {
		List<Ingredient> inglist=ingService.selectAllIngredient();
		model.addAttribute("inglist", inglist);
		
		return "recipeView/writeFood";
	}
	
	@PostMapping("/writeFood")
	public String writeFood(Food food, String i_name, String od_quantity) throws Exception{
		log.debug("postWriteFood() called");
		log.debug("food : {}, i_name : {}, od_quantity : {}", food, i_name, od_quantity);
		String[] arrIname = i_name.split(",");
		String[] arrQty = od_quantity.split(",");
		
		int result = foodservice.writeFood(food, arrIname, arrQty);
		log.debug("저장한 레시피 정보 :{}", food);
		return "redirect:list";
	}
	
	//레시피 탭 클릭했을 시 레시피 이름 출력
	@GetMapping("/list")
	public String getFood(Model model) {
		log.debug("get recipeView/list called");
		
		ArrayList<Food> foodlist = foodservice.foodList();
		log.debug("foodlist : {}", foodlist);
		model.addAttribute("foodlist", foodlist);
		
		return "recipeView/list";
		
	}
	
	//레시피 상세 내용 출력
	@GetMapping("/detail")
	public String getDetail(Model model,  
			@RequestParam(name="food_id", defaultValue = "0") int food_id ) {
		log.debug("get recipeView/detail called");
		
		Food food = foodservice.getDetail(food_id);
		model.addAttribute("food", food);
		log.debug("food: {}", food);
		
		List<FoodDetail> ingredientList =foodDetailService.selectIngredientListByFoodId(food_id);
		model.addAttribute("ingredientList", ingredientList);
		log.debug("ingredientList : {}", ingredientList);
		
		ArrayList<Board> list = foodservice.selectboard(food_id);//food_id에 대한 후기글 목록 가져오기)
		log.debug("후기글 목록:", list);
		model.addAttribute("list", list);
		
	
		return "recipeView/detail";
		
	}
	
}
