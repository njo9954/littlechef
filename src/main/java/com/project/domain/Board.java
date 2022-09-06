package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	int b_num;
	int food_id;
	String m_id;
	String b_title;
	String b_datatime;
	String b_content;
	int b_readcount;
	int b_likecount;
}
