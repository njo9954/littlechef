package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

	
	
	@GetMapping("/mypage")
	public String mypage() {
		
		return "/memberView/mypage";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(Model model, @AuthenticationPrincipal UserDetails user) {
		
		String id = user.getUsername();
		Member member = service.getMemberInfo(id);
		model.addAttribute("member", member);
		
		return "/memberView/updateForm";
	}
	
	@PostMapping("/deleteMember")
	public String deleteMember(Member member, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("수정할 정보 : {}", member); 
		
		String id = user.getUsername();
		member.setM_id(id);
		
		int result=service.deleteMember(member);
		if(result==0) {
			log.debug("해당 이름이 없습니다.");
		}
		else {
			log.debug("{} 수정 성공", member);
		}
	
		return "redirect:/logout";
	}
	
	
	@PostMapping("/update")
	public String update(Member member, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("수정할 정보 : {}", member); 
		
		String id = user.getUsername();
		member.setM_id(id);
		
		int result=service.update(member);
		if(result==0) {
			log.debug("해당 이름이 없습니다.");
		}
		else {
			log.debug("{} 수정 성공", member);
		}
	
		return "redirect:/";
	}
	
	@GetMapping("coupon")
	public String coupon(Model model, @AuthenticationPrincipal UserDetails user) {
		String id = user.getUsername();
		Member member = service.getMemberInfo(id);
		
		int coupon = member.getM_coupon();
		model.addAttribute("coupon",coupon);
			
		return "/memberView/coupon";
	}
}
	