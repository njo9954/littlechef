package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("orderView")
@Controller
public class OrderController {

	@GetMapping("cart")
	public String cart() {
		return "/orderView/cart";
	}
	
	@GetMapping("cartList")
	public String cartList() {
		return "/orderView/cartList";
	}
	
	@GetMapping("order")
	public String order() {
		return "/orderView/order";
	}
	
	@GetMapping("orderList")
	public String orderList() {
		return "/orderView/orderList";
	}
}
