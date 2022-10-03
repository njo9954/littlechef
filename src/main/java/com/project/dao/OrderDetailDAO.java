package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.domain.OrderDetail;

@Mapper
public interface OrderDetailDAO {

	public void insertOrderDetail(OrderDetail orderDetail);

	public List<OrderDetail> selectOrderDetailListByOid(int o_id);

	public int deleteOrderDetail(OrderDetail cartIdAndMemberId);
}

