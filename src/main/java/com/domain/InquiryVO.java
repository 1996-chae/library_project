package com.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InquiryVO {
	private int ino;
	private String title;
	private String content;
	private String writer;
	private String answer;
	private Response response;
	private Date writeDate;
	
	public enum Response {
		STANDBY,COMPLETION
	}
}
