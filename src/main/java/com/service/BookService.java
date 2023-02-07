package com.service;

import java.util.List;

import com.dao.BookDao;
import com.domain.BookDTO;
import com.domain.BookVO;

public class BookService {

	private BookDao dao;
	
	public BookService(BookDao dao) {
		this.dao = dao;
	}

	// 검색 가져오기
	public List<BookVO> find(BookDTO dto) {
		return dao.selectTotal(dto);
	}
	
}
