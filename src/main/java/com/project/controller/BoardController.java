package com.project.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.project.domain.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("boardView")
public class BoardController { // 후기 컨트롤러 
	
	@Autowired
	BoardService service;

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
			
			return "redirect:list";
		}
		//후기 상세 읽기
		@GetMapping("/readboard")
		public String read(Model model
				, @RequestParam(name="boardnum", defaultValue="0") int boardnum) { // 안들어오면 기본값 0으로 처리하겠다는 @getParameter
			log.debug("전달된 번호 : {}", boardnum);
			
		//	service.updateHits(boardnum); // 조회수 1
			
			//전달된 번호로 글을 읽어서 Board객체를 리턴받음
			Board board = service.selectOne(boardnum); //DB에서 글을 읽어서
			log.debug("전달된 값 : {}", board);
			
//			service.updateHits(boardnum); // 조회수 0
			
			//결과가 없으면 글목록으로 리다이렉트
			if(boardnum == 0) {
				return "redirect:/";
			}
			
			//현재 글의 리플 목록 읽기
		//	ArrayList<Reply> replylist = service.selectReply(boardnum);
			
			//결과가 있으면 모델에 저장하고 html에서 출력
			model.addAttribute("board", board);
		//	model.addAttribute("replylist", replylist);
			
			return "/boardView/readboard";
		}
}