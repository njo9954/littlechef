package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	//장바구니 담기
	@PostMapping("/cart")
	public String cart(@RequestParam(name="food_id", defaultValue="0") int food_id, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("cart() called");
		log.debug("food_id {}", food_id);
		
		Food food=foodservice.getDetail(food_id);
		Cart cart=new Cart();
		log.debug("cart : {}", cart);
		log.debug("food: {}", food);
		cart.setM_id(user.getUsername());
		cart.setFood_id(food_id);
		cart.setC_amount(1);
		cart.setC_price(food.getFood_price());
		cart.setFood_name(food.getFood_name());
		log.debug("cart : {}", cart);
		int result=orderservice.insertCart(cart);
		log.debug("result : {}", result);

		return "redirect:/orderView/cart";
	}
	
	//장바구니 목록
	@GetMapping("/cart")
	public String cartlist(Model model, @AuthenticationPrincipal UserDetails user) throws Exception {
		log.debug("cart() called");
		
		String username=user.getUsername();
		ArrayList<Cart> cartlist=orderservice.selectCart(username);

		log.debug("cartlist : {}", cartlist);

		model.addAttribute("cartlist", cartlist);

		return "/orderView/cart";
	}
	
	//장바구니 선택 삭제
	@PostMapping("/delete")
	public String delete(int c_id, @AuthenticationPrincipal UserDetails user) {
		log.debug("delete() called");
		
		String username=user.getUsername();
		
		Cart cartIdAndMemberId = new Cart();
		cartIdAndMemberId.setC_id(c_id);
		cartIdAndMemberId.setM_id(user.getUsername());
		int result = orderservice.deleteCart(cartIdAndMemberId);

		log.debug("delete", result);

		return "redirect:/orderView/cart";

	}
	
	//장바구니 전체 삭제
	@PostMapping("/deleteall")
	public String deleteall(@AuthenticationPrincipal UserDetails user) {
		log.debug("deleteall() called");
		
		String username=user.getUsername();
		int result = orderservice.deleteCartAll(username);

		log.debug("delete", result);

		return "redirect:/orderView/cart";

	}

	
	@PostMapping("order")
	public String order(Model model, @AuthenticationPrincipal UserDetails user) {
		return "/orderView/order";
	}
	
	@GetMapping("orderList")
	public String orderList() {
		return "/orderView/orderList";
	}
}
