package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dao.FoodDAO;
import com.project.domain.Board;
import com.project.domain.Food;
import com.project.service.BoardService;
import com.project.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FoodController {
	
	@Autowired
	FoodService foodservice;
	FoodDAO fooddao;
	BoardService service;
	
	@GetMapping("recipeView/writeFood")
	public String writeFood() {
		return "recipeView/writeFood";
	}
	
	@PostMapping("recipeView/writeFood")
	public String writeFood(Food food)
	{
		
		int result = foodservice.writeFood(food);
		log.debug("저장한 레시피 정보 :{}", food);
		return "redirect:list";
	}
	
	//레시피 탭 클릭했을 시 레시피 이름 출력
	@GetMapping("recipeView/list")
	public String getFood(Model model) {
		log.debug("get recipeView/list called");
		
		ArrayList<Food> foodlist = foodservice.foodList();
		log.debug("foodlist : {}", foodlist);
		model.addAttribute("foodlist", foodlist);
		
		return "recipeView/list";
		
	}
	
	//레시피 상세 내용 출력
	@GetMapping("recipeView/detail")
	public String getDetail(Model model,  
			@RequestParam(name="food_id", defaultValue = "0") int food_id 
			, String type 
			, String searchWord
			) {

		log.debug("get recipeView/detail called");
		Food food = foodservice.getDetail(food_id);
		log.debug("food: {}", food);
		model.addAttribute("food", food);
		
		ArrayList<Board> list = foodservice.selectboard(food_id);//food_id에 대한 후기글 목록 가져오기)
		log.debug("후기글 목록:", list);
				
		model.addAttribute("list", list);
		model.addAttribute("type", type); // 저장해서 html 페이지에서 사용
		model.addAttribute("searchWord", searchWord);
		
	
		return "recipeView/detail";
		
	}
	
}
