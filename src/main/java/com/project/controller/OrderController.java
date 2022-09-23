package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.domain.Cart;
import com.project.domain.Food;
import com.project.service.FoodService;
import com.project.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("orderView")
@Controller
public class OrderController {

	@Autowired
	OrderService orderservice;
	
	@Autowired
	FoodService foodservice;
	
	@PostMapping("/cart")
	public String cart(@RequestParam(name="food_id", defaultValue="0") int food_id, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("cart() called");
		log.debug("food_id {}", food_id);
		
		Food food=foodservice.getDetail(food_id);
		Cart cart=new Cart();
		log.debug("cart : {}", cart);
		cart.setM_id(user.getUsername());
		cart.setFood_id(food.getFood_id());
		log.debug("cart : {}", cart);
		int result=orderservice.insertCart(cart);
		log.debug("result : {}", result);

		return "/orderView/cart";
	}
	
	@GetMapping("/cartList")
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
