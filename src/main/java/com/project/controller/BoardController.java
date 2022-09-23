package com.project.controller;


import java.util.ArrayList;

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
import org.springframework.web.multipart.MultipartFile;

import com.project.service.BoardService;
import com.project.util.PageNavigator;
import com.project.domain.Board;
import com.project.domain.Reply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("boardView")
public class BoardController { // 후기 컨트롤러 
	
	//게시판 페이지 당 출력할 글수
		@Value("${user.board.page}") // 바뀔 수도 있는 값들 한 곳에 몰아서 정의
		int countPerPage; // 변수 준비하고 변수 위에 어노테이션
		
		//게시판 목록의 페이지 이동 링크 수
		@Value("${user.board.group}")
		int pagePerGroup;
		
		//첨부파일이 저장된 경로
	//	@Value("${spring.servlet.multipart.location}")
	//	String uploadPath;
		
	@Autowired
	BoardService service;

	@GetMapping("listboard")
	public String list(
			Model model
			, @RequestParam(name="page", defaultValue="1") int page // 안넘어오면 무조건 1페이지 -> requestParam
			, String type
			, String searchWord) {
		log.debug("페이지당 글수:{}, 페이지이동 링크수:{}, 페이지:{}, 검색대상:{}, 검색어:{}"
				, countPerPage, pagePerGroup, page, type, searchWord);
		
		PageNavigator navi = service.getPageNavigator(
				pagePerGroup, countPerPage, page, type, searchWord);
		
		//DB의 게시판 테이블의 모든 글을 읽어서
		ArrayList<Board> boardlist = service.selectAll(navi, type, searchWord);
		
		//ArrayList<Board> 타입으로 모델에 저장
		model.addAttribute("navi", navi);
		model.addAttribute("listboard", boardlist);
		model.addAttribute("type", type); // 저장해서 html 페이지에서 사용
		model.addAttribute("searchWord", searchWord);
		
		int total = service.selectTotal();
		log.debug("전체 글 : {}", total);
		model.addAttribute("total", total);
		
		return "/boardView/listboard";
	}
	
	
	//후기쓰기 폼으로 이동
	@GetMapping("writeboard")
	public String write() {
		return "/boardView/writeboard";
	}
	
	
	//후기저장
		@PostMapping("writeboard")
		public String write(
				Board board
				, @AuthenticationPrincipal UserDetails user // 로그인한 사용자 정보 전달
				, MultipartFile upload) {
			
			String id = user.getUsername(); // id 읽어서
			board.setM_id(id); // board에 추가
			
		//	if(upload != null && !upload.isEmpty()) {
//				log.debug("name: {}", upload.getName());
//				log.debug("originalFilename: {}", upload.getOriginalFilename());
//				log.debug("contentType: {}", upload.getContentType()); // 어떤 종류의 파일인지
//				log.debug("size: {}", upload.getSize()); // 바이트 단위 용량 // 엄청 큰 파일이라면 여기 오기 전 에러
//				log.debug("isEmpty: {}", upload.isEmpty()); // 제대로 올라왔으면 false
			//	String savedfile = FileService.saveFile(upload, uploadPath); // 변경할 수 있는 값 xx. "c:/upload". 수정하더라도 application.properties만 수정 o
			//	board.setOriginalfile(upload.getOriginalFilename()); // 파일 원래 이름
			//	board.setSavedfile(savedfile); // 파일 저장된 이름
//			}
			
			log.debug("저장할 글정보 : {}", board);
			
			service.writeBoard(board);
			
			return "redirect:listboard";
		}
		//후기 상세 읽기
		@GetMapping("readboard")
		public String read(Model model
				, @RequestParam(name="b_num", defaultValue="0") int b_num) { // 안들어오면 기본값 0으로 처리하겠다는 @getParameter
			log.debug("전달된 번호 : {}", b_num);
			
			service.updateHits(b_num); 
			
			//전달된 번호로 글을 읽어서 Board객체를 리턴받음
			Board board = service.selectOne(b_num); //DB에서 글을 읽어서
			log.debug("전달된 값 : {}", board);
			
			//현재 글의 리플 목록 읽기
			ArrayList<Reply> replylist = service.selectReply(b_num);
			
			//결과가 있으면 모델에 저장하고 html에서 출력
			model.addAttribute("board", board);
			model.addAttribute("replylist", replylist);
			
			return "/boardView/readboard";
		}
		
		@GetMapping("replywrite")
		public String replywrite() {
			return "/boardView/writereply";
		}
		
		//리플 저장
		@PostMapping("replyWrite")
		public String replyWrite(
				Reply reply
				, @AuthenticationPrincipal UserDetails user) {
			//본문글번호, 리플내용 전달받아 로그인한 아이디 추가
			String id = user.getUsername();
			reply.setM_id(id);
			//DB에 저장
			log.debug("reply : {}", reply);
			service.insertReply(reply);
			
			//아까 읽던 글로 돌아감
			return "redirect:/boardView/readboard?b_num=" + reply.getB_num();
		}
		
		@PostMapping("deleteReply")
		public String deleteReply(
				int br_num
				, @AuthenticationPrincipal UserDetails user) {
			
			// 해당번호의 댓글 조회
			Reply reply = service.selectOneReply(br_num);
			log.debug("reply : {}", reply);
			
			//결과가 없으면 글목록으로 리다이렉트
			if (reply == null) {
				return "redirect:/";
			}
			
			//로그인한 아이디 확인
			String m_id = user.getUsername();
			reply.setM_id(m_id);
			// ~로그인한 아이디 다시 집어넣어서 댓글 작성자 아이디를 로그인한 아이디로 고침 / 혹시 다른 사람의 글로 접근했을 때
			
			//전달된 번호의 글 삭제 // 0 : 글 못지움 / 1 : 글 삭제함
			service.deleteReply(reply);
				
			//글읽기로 이동
			return "redirect:/boardView/readboard?b_num=" + reply.getB_num();
		}
		
		
}