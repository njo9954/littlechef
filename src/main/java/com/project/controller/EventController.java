package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import lombok.extern.slf4j.Slf4j;
//@Slf4j
@RequestMapping("eventView")
@Controller
public class EventController {
	@GetMapping("event")
	public String event() {
		
		return "eventView/event";
	}
	
	
}
