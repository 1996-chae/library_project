package com.domain;

import java.sql.Date;

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
public class ReplyVO {
	private int rno; 
	private int bno; 
	private String reply;
	private String writerId;
	private String writerNick;
	private Date replyDate; 
	private Date modifyDate; 
}
