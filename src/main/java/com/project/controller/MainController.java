package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  
public class MainController {

	//메인홈페이지 
	@GetMapping({"","/"})
	public String main() {
		return "main";
	}
	
	
	
	@GetMapping("review")
	public String review() {
		return "/review";
	}
}
