package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.project.domain.FoodDetail;
import com.project.domain.Order;
import com.project.domain.OrderDetail;



public interface OrderService {
	
	public int insertCart(Order order, List<FoodDetail> foodDetailList);

	public Order selectOrderByUsrid(String usrid);

	public int deleteOrderDetail(OrderDetail cartIdAndMemberId);

	public int deleteOrder(String username);

	public List<OrderDetail> selectOrderDetailListByOid(int o_id);

}