package com.service;

import java.util.List;

import com.dao.BoardDao;
import com.domain.BoardVO;
import com.domain.Criteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardSevice {

	private BoardDao dao;
	
	// 게시물 보기
	public List<BoardVO> boardTotalList(Criteria criteria) {
		return dao.selectBoard(criteria);
	}

	// 게시물 타입 가져오기
	public List<String> boardTotalType() {
		return dao.selectAlltype();
	}
	
	// 게시글 갯수
	public int getTotalCount() {
		return dao.selectAllCount();
	}
	
	// 게시물 종류별 갯수
	public int getOneCount(String type) {
		return dao.selectChoiceCount(type);
	}
	
	// 상세 조회
	public BoardVO findBoard(int bno) {
		return dao.selectOne(bno);
	}
	
	// 조회수 올리기
	public void oneUpHit(int bno) {
		dao.updateHit(bno);
	}

	// 글쓰기
	public int addBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}
	
	// 글수정
	public void chageBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}
	
	public void removeBoard(int bno) {
		dao.deleteBnoard(bno);
	}
}
