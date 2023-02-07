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
@Builder
@ToString
public class MemberVO {
	private int mno; 
	private String id; 
	private String pwd;
	private String nickname; 
	private String name; 
	private String email; 
	private Date joinDate;
	private MemberGrade grade;
	
	public enum MemberGrade{
		ROLE_ADMIN, ROLE_MEMBER
	}
}
