package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	int c_id;
	int food_id;
	String m_id;
	int c_cal;
	int c_price;
	int c_amount;
}
