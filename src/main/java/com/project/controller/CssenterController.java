package com.project.controller;

import java.util.ArrayList;

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

import com.project.domain.Cscenter;
import com.project.domain.Csreply;
import com.project.service.CscenterService;
import com.project.util.PageNavigator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("cscenterView")
public class CssenterController {
	
	
	//게시판 페이지 당 출력할 글수
			@Value("${user.board.page}") // 바뀔 수도 있는 값들 한 곳에 몰아서 정의
			int countPerPage; // 변수 준비하고 변수 위에 어노테이션
			
			//게시판 목록의 페이지 이동 링크 수
			@Value("${user.board.group}")
			int pagePerGroup;
			
	
	
	
		@Autowired
		CscenterService service;
		

		@GetMapping("cslist")
		public String cslist(
				Model model
				, @RequestParam(name="page", defaultValue="1") int page // 안넘어오면 무조건 1페이지 -> requestParam
				, String type
				, String searchWord
				) {
			log.debug("페이지당 글수:{}, 페이지이동 링크수:{}, 페이지:{}, 검색대상:{}, 검색어:{}"
					, countPerPage, pagePerGroup, page, type, searchWord);
			
			PageNavigator navi = service.getPageNavigator(
					pagePerGroup, countPerPage, page, type, searchWord);
			
			//DB의 게시판 테이블의 모든 글을 읽어서
			ArrayList<Cscenter> cslist = service.selectAll(navi, type, searchWord);
			
			//ArrayList<Board> 타입으로 모델에 저장
			model.addAttribute("navi", navi);
			model.addAttribute("cslist", cslist);
			model.addAttribute("type", type); // 저장해서 html 페이지에서 사용
			model.addAttribute("searchWord", searchWord);
			
			int total = service.selectTotal();
			log.debug("전체 글 : {}", total);
			model.addAttribute("total", total);
			
			return "/cscenterView/cslist";
		}
		
		@PostMapping("cslist")
		public String cslist2(Cscenter cscenter, String op, @AuthenticationPrincipal UserDetails user) {
			log.debug("cscenter:{} // {}",cscenter, op);
			String userId = user.getUsername();
			log.debug("cscenter_userID: {}",userId);
			
			cscenter.setM_id(userId);
			service.findcenter(cscenter);
			
			return "redirect:/cscenterView/cslist";
		}
		
		
		
		//후기쓰기 폼으로 이동
		@GetMapping("cswrite")
		public String write(Model model ) { // food_id를 받아주고 
			
			return "/cscenterView/cswrite";
		}
		
		
		//후기저장
			@PostMapping("cswrite")
			public String write(
					Cscenter cscenter 
					, @AuthenticationPrincipal UserDetails user // 로그인한 사용자 정보 전달
					, MultipartFile upload) {
				
				String id = user.getUsername(); // id 읽어서
				cscenter.setM_id(id); // board에 추가
				
				log.debug("저장할 글정보 : {}", cscenter);
			
				
				service.cswrite(cscenter);
				
				return "redirect:/cscenterView/cslist?c_num=" + cscenter.getC_num();
			}
			
			@GetMapping("delete")
			public String delete(
					int c_num
					, @AuthenticationPrincipal UserDetails user) {
				
				//해당번호의 글 조회
				Cscenter cscenter = service.selectOne(c_num);
				log.debug("cscenter : {}", cscenter);
				//결과가 없으면 글목록으로 리다이렉트
				if (cscenter == null) {
					return "redirect:/";
				}
				
				String id = user.getUsername();
				cscenter.setM_id(id); // ??
				
				service.delete(cscenter);
				
				
				
				//글목록으로 이동
				return "redirect:/cscenterView/cslist"; // 글 목록으로 리다이렉트
			}
			
			@GetMapping("csupdate")
			public String update(int c_num, Model model) { // 제목, 내용 등을 보여주면서 수정하라고 해야 함 -> Model 필요
				//전달받은 번호의 글정보를 조회
				Cscenter cscenter = service.selectOne(c_num);
				log.debug("update: {}", cscenter);
				//모델에 저장하고 HTML로 이동
				model.addAttribute("cscenter", cscenter);
				return "/cscenterView/csupdate";
			}

			
			@PostMapping("csupdate")
			public String update(
					Cscenter cscenter
					, MultipartFile upload // 그 폼의 파일 선택 요소와 같아야
					, @AuthenticationPrincipal UserDetails user) { // 자기글을 수정하려고 하는지

				log.debug("저장할 글정보 : {}", cscenter);
				log.debug("파일 정보: {}", upload);
				
				//로그인한 아이디 확인 // 자기 글 수정하는지 알 수 있음
				log.debug("로그인한 아이디 : {}", user.getUsername());
				//작성자 아이디 추가
				cscenter.setM_id(user.getUsername());

				
				log.debug("수정할 글정보 : {}", cscenter);
			//	int result = 
						service.csupdate(cscenter);

						
				
						//읽던 글로 돌아감
				return "redirect:/cscenterView/csread?c_num=" + cscenter.getC_num(); // redirect:/board/read // 글읽기로 감. int형 boardnum 기다림 0 -> 글 없어서 목록으로 감 // 글 번호까지 줘야 갈 수 있음 // 읽기 화면으로 가되 아까 있던 페이지로 감
			}
			
			//후기 상세 읽기
			@GetMapping("csread")
			public String read(Model model
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
				ArrayList<Csreply> replylist = service.selectReply(c_num);
				
				//결과가 있으면 모델에 저장하고 html에서 출력
				model.addAttribute("Cscenter", cscenter);
				model.addAttribute("replylist", replylist);
				model.addAttribute("user", user);
				
				
				return "/cscenterView/csread";
			}
			
			@GetMapping("csreplywrite")
			public String replywrite() {
				return "/cscenterView/csreplywrite";
			}
			
			//리플 저장
			@PostMapping("csreplyWrite")
			public String replyWrite(
					Csreply csreply
					, @AuthenticationPrincipal UserDetails user) {
				//본문글번호, 리플내용 전달받아 로그인한 아이디 추가
				String id = user.getUsername();
				csreply.setM_id(id);
				//DB에 저장
				log.debug("reply : {}", csreply);
				service.insertReply(csreply);
				
				//아까 읽던 글로 돌아감
				return "redirect:/cscenterView/csread?c_num=" + csreply.getC_num();
			}
			
			@GetMapping("deleteReply")
			public String deleteReply(
					int cr_num
					, @AuthenticationPrincipal UserDetails user) {
				
				// 해당번호의 댓글 조회
				Csreply reply = service.selectOneReply(cr_num);
				log.debug("reply : {}", reply);
				
				//결과가 없으면 글목록으로 리다이렉트
				if (reply == null) {
					return "redirect:/cscenterView/cslist";
				}
				
				//로그인한 아이디 확인
				String m_id = user.getUsername();
				reply.setM_id(m_id);
				// ~로그인한 아이디 다시 집어넣어서 댓글 작성자 아이디를 로그인한 아이디로 고침 / 혹시 다른 사람의 글로 접근했을 때
				
				//전달된 번호의 글 삭제 // 0 : 글 못지움 / 1 : 글 삭제함
				service.deleteReply(reply);
					
				//글읽기로 이동
				return "redirect:/cscenterView/csread?c_num=" + reply.getC_num();
			}

	}

