package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.FoodDAO;
import com.project.dao.FoodDetailDAO;
import com.project.domain.Board;
import com.project.domain.Food;
import com.project.domain.FoodDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDAO fooddao;
	
	@Autowired
	private FoodDetailDAO foodDetailDAO;


	@Override
	public Food getDetail(int food_id) {
		log.debug("getDetail");
		Food food = fooddao.getDetail(food_id);
		
		return food;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 서비스 진행중에 에러가 나면 서비스 초기상태로 롤백시켜버림
	public int writeFood(Food food, String[] arrIname, String[] arrQty) throws Exception {
		int result = fooddao.writeFood(food);
		if(result != 1)
			throw new Exception("레시피 작성중 오류 발생!");
		
		log.debug("food_id : {}", food.getFood_id());
		
		List<FoodDetail> foodDetailList = new ArrayList<>();
		for(int i=0 ; i<arrIname.length ; i++) {
			FoodDetail foodDetail = new FoodDetail();
			foodDetail.setFood_id(food.getFood_id());
			foodDetail.setI_name(arrIname[i]);
			foodDetail.setQuantity(Integer.parseInt(arrQty[i]));
			foodDetailList.add(foodDetail);
		}
		
		foodDetailDAO.insertFoodDetailList(foodDetailList);
		
		return result;
		
	}


	@Override
	public ArrayList<Food> foodList() {
		ArrayList<Food> foodlist=fooddao.getFoodList();
		return foodlist;
	}

	@Override
	public ArrayList<Food> search(String searchWord) {
		ArrayList<Food> result = fooddao.search(searchWord);
		return result;
	}

	@Override
	public ArrayList<Board> selectboard(int food_id) {
		ArrayList<Board> list = fooddao.selectboard(food_id);
		return list;
	}

	@Override
	public int updateReadCount(int food_id) {
		
			int result = fooddao.updateReadCount(food_id);
			return result;		
	}
}
