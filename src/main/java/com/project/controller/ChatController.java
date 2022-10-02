package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chatView")
public class ChatController {

	@GetMapping("chat")
	public String chat() {
		return "chatView/chat";

}
}
