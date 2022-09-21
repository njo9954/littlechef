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
	int food_cal;
	String food_tag;
	int bookmark_count; 

}
