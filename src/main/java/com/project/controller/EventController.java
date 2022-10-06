package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.domain.Member;
import com.project.service.EventService;

//import lombok.extern.slf4j.Slf4j;
//@Slf4j
@RequestMapping("eventView")
@Controller
public class EventController {
	
	@Autowired
	EventService service;
	
	@GetMapping("event")
	public String event(
			@AuthenticationPrincipal UserDetails user, Model model
			) {
		
		//id 알아냄
		String id = user.getUsername(); 
		
		return "eventView/event";
	
	}
	
	@ResponseBody
	@PostMapping("insertEvent")
	public int insertEvent(int num, 
			@AuthenticationPrincipal UserDetails user ) {
		
		Member member = new Member();
		
		//id 알아냄
		String id = user.getUsername(); 
		//서비스로 num과 id를 보내
		member.setM_id(id);
		member.setM_coupon(num);
		
		int res = service.insertcoupon(member);
		//서비스 -> dao 거쳐서 다음을 실행
		return res;
		//update site_member set m_coupon = 1 where m_id = '1234' and m_coupon != 0;
		
	}

}