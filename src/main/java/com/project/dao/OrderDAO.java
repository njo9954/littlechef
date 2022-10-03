package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.Order;
import com.project.domain.OrderDetail;

@Mapper
public interface OrderDAO {

	public int insertCart(Order order);

	public Order selectOrderByUsrid(String usrid);

	public int deleteCartAll(String username);

	public int deleteOrder(String username);
}
