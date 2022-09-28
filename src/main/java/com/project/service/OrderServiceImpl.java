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

	@Override
	public ArrayList<Cart> selectCart(String username) {
		ArrayList<Cart> cartlist=orderdao.selectCart(username);
		return cartlist;
	}

	@Override
	public int deleteCart(Cart cartIdAndMemberId) {
		int list=orderdao.deleteCart(cartIdAndMemberId);
		return list;
	}

	@Override
	public int deleteCartAll(String username) {
		int list=orderdao.deleteCartAll(username);
		return list;
	}
	
}
