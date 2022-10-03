package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.project.domain.FoodDetail;
import com.project.domain.Ingredient;
import com.project.domain.Order;
import com.project.domain.OrderDetail;
import com.project.service.FoodDetailService;
import com.project.service.FoodService;
import com.project.service.IngredientService;
import com.project.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("orderView")
@Controller
public class OrderController {

	@Autowired
	OrderService orderservice;
	
	@Autowired
	IngredientService ingservice;
	
	@Autowired
	FoodDetailService fooddetailservice;
	
	//장바구니 담기
	@PostMapping("/cart")
	public String cart(@RequestParam(name="food_id", defaultValue="0") int food_id, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("cart() called");
		log.debug("food_id {}", food_id);
		
		List<FoodDetail> foodDetailList=fooddetailservice.selectIngredientListByFoodId(food_id);
		log.debug("foodDetailList: {}", foodDetailList);
		List<Ingredient> ingredientList=ingservice.selectAllIngredient();
		log.debug("ingredientList: {}", ingredientList);
		String username=user.getUsername();
		
		// 내 id로 o_state = 0인거 셀렉해오기 없다면 새 오더 생성
		Order order = orderservice.selectOrderByUsrid(username);
		if(order == null)
			order = new Order();
		
		
		order.setM_id(user.getUsername());
		
		for(FoodDetail foodDetail : foodDetailList) {
			for(Ingredient ingredient : ingredientList) {
				if(ingredient.getI_name().equals(foodDetail.getI_name())) {
					order.setO_price(order.getO_price()+foodDetail.getQuantity()*ingredient.getI_price());
					break;
				}
			}
		}
		log.debug("order : {}", order);
		
		orderservice.insertCart(order, foodDetailList);
		
		//int result=orderservice.insertCart(order);
		//log.debug("result : {}", result);

		return "redirect:/orderView/cart";
	}
	
	//장바구니 목록
	@GetMapping("/cart")
	public String cartlist(Model model, @AuthenticationPrincipal UserDetails user) throws Exception {
		
			log.debug("cart() called");
			
			String username=user.getUsername();
			Order order = orderservice.selectOrderByUsrid(username);
			log.debug("order : {}", order);
			
			
			// 장바구니가 만들어져있는 회원인지 체크
			if(order != null) {
				// 만들어져있다면 그 안의 내용물을 select해옴
				List<OrderDetail> orderDetailList = orderservice.selectOrderDetailListByOid(order.getO_id());
				log.debug("orderDetailList : {}", orderDetailList);
				model.addAttribute("orderDetailList", orderDetailList);
			} else {
				//장바구니가 텅 빈 회원은 빈 오더를 생성해서 보여줌
				order = new Order();
			}
			
			model.addAttribute("order", order);
			return "/orderView/cart";
		}
	
	//장바구니 선택 삭제
	@PostMapping("/delete")
	public String delete(int o_id, String i_name, @AuthenticationPrincipal UserDetails user) {
		log.debug("delete() called");
		log.debug("o_id : {}, i_name : {}", o_id, i_name);
		String username=user.getUsername();
		
		OrderDetail oIdAndIname = new OrderDetail();
		oIdAndIname.setO_id(o_id);
		oIdAndIname.setI_name(i_name);
		
		int result = orderservice.deleteOrderDetail(oIdAndIname);

		return "redirect:/orderView/cart";

	}
	
	//장바구니 전체 삭제
	@PostMapping("/deleteall")
	public String deleteall(@AuthenticationPrincipal UserDetails user) {
		log.debug("deleteall() called");
		
		String username=user.getUsername();
		int result = orderservice.deleteOrder(username);

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
