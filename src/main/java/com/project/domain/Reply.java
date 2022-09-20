package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    int br_num;
    int b_num;
    String m_id;
    String br_title;
    String br_datetime;
    String br_contents;
    int br_likecount;	
}
