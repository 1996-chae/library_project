package com.service;

import com.dao.MemberDao;
import com.domain.AuthVO;
import com.domain.MemberVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService {

	MemberDao dao;
	
	// 로그인 체크
	public boolean loginService(MemberVO vo) {
		return dao.loginCheck(vo);
	}
	
	
	// 아이디로 통한 회원 정보
	public AuthVO getMemberGrade(String id) {
		return dao.findMemberGradeById(id);
	}
	
	// 회원 가입
	public void memberJoin(MemberVO vo) {
		dao.insertMember(vo);
	}
	
	// 회원 정보 조회
	public MemberVO getMemberAll(String id) {
		return dao.selectAll(id);
	}
	
	
	// 회원 정보 수정
	public void modMember(MemberVO vo) {
		dao.updateMember(vo);
	}
	
	// 비밀번호 변경
	public void modPwdChange(MemberVO vo) {
		dao.updatePwd(vo);
	}

	// 닉네임 변경
	public String modNickChange(MemberVO vo) {
		return dao.updateNick(vo);
	}
	
	// 닉변시 보드 전체 변경
	public void chageNickname(MemberVO vo) {
		dao.updateNicknameToBoard(vo);
	}	
	
	// 중복 체크 확인
	public boolean dupChek(String dup, String input) {
		return dao.selectDup(dup,input);
	}


	
	
}
