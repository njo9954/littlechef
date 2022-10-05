package com.project.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.Board;
import com.project.domain.Cscenter;
import com.project.domain.Reply;
import com.project.service.CscenterService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("cscenterView")
public class CssenterController {
	
		@Autowired
		CscenterService service;
	

	//cs center page  문의사항 페이지 
		@GetMapping("cscenter")
		public String cscenter() {

			return "cscenterView/cscenter";
		}
		
		@PostMapping("cscenter")
		public String cscenter(Cscenter cscenter
				, @AuthenticationPrincipal UserDetails user) {

			String id = user.getUsername(); // id 읽어서
			cscenter.setM_id(id); // board에 추가
			
		
			
			log.debug("저장할 글정보 : {}", cscenter);
			log.debug("저장할 글정보 : {}", cscenter);
			log.debug("저장할 글정보 : {}", cscenter);
		
			
			service.cswrite(cscenter);
			
			return "redirect:/CscenterView/cscenter?c_num=" + cscenter.getC_num();
		}
		
		
		//문의 상세 읽기
				@GetMapping("csread")
				public String csread(Model model
						, @RequestParam(name="c_num", defaultValue="0") int c_num,
						@SessionAttribute(name = "user", required = false)UserDetails user,
		                HttpServletRequest request,
		                HttpServletResponse response
		               ) { // 안들어오면 기본값 0으로 처리하겠다는 @getParameter
				
					log.debug("전달된 번호 : {}", c_num);
					
					
					//전달된 번호로 글을 읽어서 Board객체를 리턴받음
					Cscenter cscenter = service.selectOne(c_num); //DB에서 글을 읽어서
					log.debug("전달된 값 : {}", cscenter);
					
					//현재 글의 리플 목록 읽기
				//	ArrayList<Reply> replylist = service.selectReply(c_num);
					
					//결과가 있으면 모델에 저장하고 html에서 출력
					model.addAttribute("Cscenter", cscenter);
				//	model.addAttribute("replylist", replylist);
					model.addAttribute("user", user);
					
					
					return "/cscenterView/csread";
				}

		//후기쓰기 폼으로 이동
		@GetMapping("cswrite")
		public String write() { // food_id를 받아주고 
			
			
			
			return "/cscenterView/cswrite";
		}

	}

