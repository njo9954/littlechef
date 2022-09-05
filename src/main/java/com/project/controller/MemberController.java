package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.domain.Member;
import com.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("memberView")
public class MemberController {
	
	//@Autowired
	//MemberService service;
	
	@GetMapping("join")
	public String join() {
		return "/memberView/join";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		log.debug("{}",member);
		
		//service.insertMember(member);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login() {
		return "/memberView/login";
	}



}
	