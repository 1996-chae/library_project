package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.Criteria;
import com.domain.InquiryVO;
import com.domain.InquiryVO.Response;
import com.domain.MemberVO.MemberGrade;

public class InquiryDao {

private DataSource dataSource;
	
	public InquiryDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 게시판 가져오기
	public List<InquiryVO> selectInquiry(Criteria criteria) {
		String query = "SELECT ROWNUM, INO, TITLE, WRITER, WRITEDATE, RESPONSE"
				+ " FROM (SELECT /*+INDEX_DESC(inquiry_seohyanglim PK_inquiry_seohyanglim)*/ "
				+ " ROWNUM AS RN, INO, TITLE, WRITER, WRITEDATE, RESPONSE"
				+ " FROM inquiry_seohyanglim WHERE ROWNUM<=?) where RN>?";
		List<InquiryVO> list = new ArrayList<>();
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			int maxRow = criteria.getPageNum()*criteria.getAmount();
			int minRow = (criteria.getPageNum()-1)*criteria.getAmount();
			pstmt.setInt(1, maxRow);
			pstmt.setInt(2, minRow);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					InquiryVO vo = InquiryVO.builder()
							.ino(rs.getInt("INO"))
							.title(rs.getString("TITLE"))
							.writer(rs.getString("WRITER"))
							.writeDate(rs.getDate("WRITEDATE"))
							.response(Response.valueOf(rs.getString("RESPONSE")))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	// 전체 게시글수
	public int selectAllCount() {
		String query = "SELECT COUNT(INO) FROM inquiry_seohyanglim";
		int totalCount = 0;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(INO)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 특정인이 쓴 게시글수
	public int selectChoiceCount(String writer) {
		String query = "SELECT COUNT(INO) FROM inquiry_seohyanglim where WRITER=?";
		int totalCount = 0;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, writer);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					totalCount = rs.getInt("COUNT(INO)");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
}
