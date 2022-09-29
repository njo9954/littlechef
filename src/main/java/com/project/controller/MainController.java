package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.domain.Food;
import com.project.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller  
public class MainController {

	@Autowired
	private FoodService service;
	
	//메인홈페이지 
	@GetMapping({"","/"})
	public String main() {
		return "main";
	}
	
	//cs center page
	@GetMapping("/cscenter")
	public String cscenter() {
		return "cscenter";
	}
	
	//search기능
	@PostMapping("/search")
	public String foodList(Model model, 
			String type, String searchWord) {
		log.debug("검색대상 : {}, 검색어 : {}", type, searchWord);
		log.debug("검색대상:{}, 검색어:{}"
				, type, searchWord);
		log.debug("foodlist() called");
		ArrayList<Food> list = service.foodSearch(type, searchWord);
		model.addAttribute("foodList", list);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
		log.debug("리스트 잘 적용되는지 확인");
		return "redirect:/";
	}
}
