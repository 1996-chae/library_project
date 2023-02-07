package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.AuthVO;
import com.domain.MemberVO;
import com.domain.MemberVO.MemberGrade;

public class MemberDao {

	private DataSource dataSource;
	
	public MemberDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 로그인 체크
	public boolean loginCheck(MemberVO vo) {
		boolean result = false;
		String query = "SELECT DECODE(COUNT(*),1,'TRUE','FALSE') AS RESULT FROM MEMBER_seohyanglim WHERE ID=? AND PWD=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					result = Boolean.parseBoolean(rs.getString("RESULT"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}

	// 로그인 로그인등급정보 가져오기
	public AuthVO findMemberGradeById(String id) {
		AuthVO authVO = new AuthVO();
		String query = "SELECT GRADE, NICKNAME FROM MEMBER_seohyanglim WHERE ID=?";
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					authVO.setNickname(rs.getString("NICKNAME"));
					authVO.setGrade(MemberGrade.valueOf(rs.getString("grade")));
					authVO.setId(id);
				}
			} 
		}catch (Exception e) {
			e.printStackTrace();
			}
		return authVO;
	}
	
	// 그 아이디에 가진정보 강능한 불러오기
	public MemberVO selectAll(String id) {
		MemberVO vo = new MemberVO();
		String query = "SELECT * FROM MEMBER_seohyanglim WHERE ID=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = MemberVO.builder()
							.id(id)
							.name(rs.getString("name"))
							.nickname(rs.getString("nickname"))
							.email(rs.getString("email"))
							.joinDate(rs.getDate("joindate"))
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 회원 가입
	public void insertMember(MemberVO vo) {
		String query = "INSERT INTO MEMBER_seohyanglim(MNO,ID,PWD,NICKNAME,NAME,EMAIL) VALUES (seohyanglim_mno_seq.nextval,?,?,?,?,?)";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNickname());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 정보 수정 작업
	public void updateMember(MemberVO vo) {
		String query = "update MEMBER_seohyanglim set name=?, email=? where id=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 비밀번호 변경
	public void updatePwd(MemberVO vo) {
		String query = "update MEMBER_seohyanglim set pwd=? where id=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 닉네임 변경
	public String updateNick(MemberVO vo) {
		String query = "update MEMBER_seohyanglim set Nickname=? where id=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, vo.getNickname());
			pstmt.setString(2, vo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo.getNickname();
	}
	// 닉네임 변경시 여태 작성한 게시글 닉네임 변경
	public void updateNicknameToBoard(MemberVO vo) {
		String query = "update BOARD_seohyanglim set writernickname=? where writerid=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, vo.getNickname());
			pstmt.setString(2, vo.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	
	
	// 중복 체크
	public boolean selectDup(String dup, String input) {
		boolean result = false;
		String query = "SELECT DECODE(COUNT(*),1,'TRUE','FALSE') AS RESULT FROM MEMBER_seohyanglim WHERE "+dup+"=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, input);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {																										
					result = Boolean.parseBoolean(rs.getString("RESULT"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}


}
