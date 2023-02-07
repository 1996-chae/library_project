package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Criteria {
	String type;
	int pageNum; // 현제 페이지
	int amount; // 페이지 개시물(10)
	
	public Criteria() {
		this(null,1,10); // 페이지 당 10개
	}

}
