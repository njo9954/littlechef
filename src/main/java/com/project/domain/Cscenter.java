package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cscenter {
	int c_num;
	String m_id;
	String c_title;
	String c_datetime;
	String c_contents;
}
