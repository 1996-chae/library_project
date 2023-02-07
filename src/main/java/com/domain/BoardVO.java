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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
	private int bno;
	private int rno;
	private String writeType;
	private String title;
	private String content;
	private String writerId;
	private String writerNickname;
	private String fileName;
	private Date writeDate;
	private Date modifyDate;
	private int replyCount;
	private int hitsCount;
	
}
