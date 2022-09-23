package com.project.service;

import java.util.ArrayList;

import org.springframework.core.annotation.Order;

import com.project.domain.Cart;

public interface OrderService {
	
	public int insertCart(Cart cart);

	public ArrayList<Cart> selectCart(String username);

}
