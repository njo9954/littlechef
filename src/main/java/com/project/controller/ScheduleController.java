package com.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/eventView")
@Controller
public class ScheduleController {

	@GetMapping("schedule")
	public String schedule() {
		return "/eventView/schedule";
	}
}