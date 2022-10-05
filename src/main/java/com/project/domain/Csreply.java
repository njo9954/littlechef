package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Csreply {
	 int cr_num;
	    int c_num;
	    String m_id;
	    String cr_datetime;
	    String cr_replytext;
}
