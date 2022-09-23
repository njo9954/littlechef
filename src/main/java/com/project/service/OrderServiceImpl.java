package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.project.dao.OrderDAO;
import com.project.domain.Cart;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderdao;
	
	@Override
	public int insertCart(Cart cart) {
		int list = orderdao.insertCart(cart);
		return list;

	}
	
}
