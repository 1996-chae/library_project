package com.service;

import java.util.List;

import com.dao.InquiryDao;
import com.domain.Criteria;
import com.domain.InquiryVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InquiryService {
	
	private InquiryDao dao;
	
	// 게시판 이용
	public List<InquiryVO> list(Criteria criteria){
		return dao.selectInquiry(criteria);
	}
	
	// 게시글 갯수
	public int getTotalCount() {
		return dao.selectAllCount();
	}
	
	// 사용자 별 갯수
	public int getOneCount(String writer) {
		return dao.selectChoiceCount(writer);
	}
	
	// 글상세
	
	// 글쓰기
	
	// 글삭제
	
	
}
