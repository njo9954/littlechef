package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
	
	int food_id;
	String food_name;
	String food_category;
	String food_contents;
	int food_cal;
	int food_price;
	int food_readcount; 
}
