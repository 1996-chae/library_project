package com.domain;

import com.domain.MemberVO.MemberGrade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthVO {
	private String id; 
	private String nickname;
	private MemberGrade grade;
}
