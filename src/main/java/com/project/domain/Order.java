package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	int o_id;
	String m_id;
	String o_date;
	int o_state;
	int o_price;
	
}