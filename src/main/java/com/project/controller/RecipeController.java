package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("recipeView")
public class RecipeController {
	@GetMapping("list")
	public String list() {
		return "/recipeView/list";
	}
	
}
