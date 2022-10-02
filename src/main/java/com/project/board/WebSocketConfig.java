package com.project.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.project.handler.ChattingHandler;

import lombok.RequiredArgsConstructor;

	@Configuration
	@EnableWebSocket
	@RequiredArgsConstructor
	public class WebSocketConfig implements WebSocketConfigurer { // 채팅 board
	   
	   @Autowired
	   private final ChattingHandler chatHandler;
	   
	   @Override
	   public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
	      registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*")
	      .addInterceptors(new HttpSessionHandshakeInterceptor());   
	   }
	   
	}