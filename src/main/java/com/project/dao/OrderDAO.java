package com.project.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

import com.project.domain.Cart;

@Mapper
public interface OrderDAO {

	public int insertCart(Cart cart);

	public ArrayList<Cart> selectCart(String username);

}
