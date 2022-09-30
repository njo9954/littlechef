package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	/**
	 * 카테고리와 검색어를 전달받아 검색결과를 리턴
	 * @param category 상품 카테고리
	 * @param searchWord 검색어
	 */
	
	@PostMapping("search")
	public String postSearch(Model model, String searchWord) {
		log.debug("postSearch() called");
		log.debug("searchWord : {}", searchWord);
		String foodlist =  service.search(searchWord);
		model.addAttribute("foodlist", foodlist);
		return "search";
	}
	
}

