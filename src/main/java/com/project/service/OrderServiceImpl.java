package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.OrderDAO;
import com.project.dao.OrderDetailDAO;
import com.project.domain.FoodDetail;
import com.project.domain.Order;
import com.project.domain.OrderDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderdao;
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 서비스 진행중에 에러가 나면 서비스 초기상태로 롤백시켜버림
	public int insertCart(Order order, List<FoodDetail> foodDetailList) {
		log.debug("insertCart() called");
		
		// 카트를 추가
		if(order.getO_id() == 0) {
			int result = orderdao.insertCart(order);
			if(result != 1)
				return 0;
		}
		
		
		List<OrderDetail> orderDetailList = orderDetailDAO.selectOrderDetailListByOid(order.getO_id());
		log.debug("orderDetailList : {}", orderDetailList);
		Map<String, Object> orderDetailMap = new HashMap<>();
		// 기존에 장바구니에 있는 재료들을 Map으로 바꿈
		for(OrderDetail orderDetail : orderDetailList) {
			orderDetailMap.put(orderDetail.getI_name(), orderDetail);
		}
		
		// 레시피의 재료들을 OrderDetail로 바꿔서 리스트화 함
		List<OrderDetail> newOrderDetailList = new ArrayList<>();
		for(FoodDetail foodDetail : foodDetailList) {
			// 레시피의 재료중 기존 장바구니에 이미 담겨있는지 확인
			if(orderDetailMap.containsKey(foodDetail.getI_name())) {
				OrderDetail temp = (OrderDetail)orderDetailMap.get(foodDetail.getI_name());
				temp.setOd_quantity(temp.getOd_quantity()+foodDetail.getQuantity());
				newOrderDetailList.add(temp);
			} else {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setO_id(order.getO_id());
				orderDetail.setI_name(foodDetail.getI_name());
				orderDetail.setOd_quantity(foodDetail.getQuantity());
				newOrderDetailList.add(orderDetail);
			}
		}
		log.debug("newOrderDetailList : {}", newOrderDetailList);
		
		// OrderDetail들을 insert
		for(int i=0 ; i<newOrderDetailList.size() ; i++) {
			orderDetailDAO.insertOrderDetail(newOrderDetailList.get(i));
		}

		return 1;

	}

	@Override
	public Order selectOrderByUsrid(String usrid) {
		log.debug("selectOrderByUsrid() called");
		return orderdao.selectOrderByUsrid(usrid);
	}
	

	@Override
	public int deleteOrderDetail(OrderDetail cartIdAndMemberId) {
		int list=orderDetailDAO.deleteOrderDetail(cartIdAndMemberId);
		return list;
	}


	@Override
	public List<OrderDetail> selectOrderDetailListByOid(int o_id) {
		log.debug("selectOrderDetailListByOid() called");
		return orderDetailDAO.selectOrderDetailListByOid(o_id);
	}

	@Override
	public int deleteOrder(String username) {
		int list=orderdao.deleteOrder(username);
		return list;
	}
}

