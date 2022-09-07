package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.domain.Member;
import com.project.service.MemberService;
import com.project.service.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("memberView")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("join")
	public String join() {
		return "/memberView/join";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		log.debug("{}",member);
		
		service.insertMember(member);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login() {
		return "/memberView/login";
	}
	
	@GetMapping("idcheck")
	public String idcheck() {
		return "/memberView/idForm";
	}
	
	@PostMapping("idcheck")
	public String idcheck(String searchId, Model model) {
		log.debug("ID : {}", searchId);
		boolean result = service.idcheck(searchId);
		
		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);
		
		return "/memberView/idForm";
	}

	
	
	@GetMapping("mypage")
	public String mypage() {
		return "/memberView/mypage";
	}
	
	@GetMapping("cart")
	public String cart() {
		return "/memberView/cart";
	}
}
	