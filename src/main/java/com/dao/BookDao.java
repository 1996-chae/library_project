package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BookDTO;
import com.domain.BookVO;

public class BookDao{

	private DataSource dataSource;
	
	public BookDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 검색 엔진
	public List<BookVO> selectTotal(BookDTO dto) {
		String query = "SELECT * FROM book_seohyanglim";
		query += addSelect(dto);
		System.out.println(query);
		List<BookVO> list = new ArrayList<>();
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()
				){
			while(rs.next()) {
				BookVO vo = BookVO.builder()
						.code(rs.getString("CODE"))
						.name(rs.getString("NAME"))
						.price(rs.getInt("PRICE"))
						.writer(rs.getString("WRITER"))
						.shop(rs.getString("SHOP"))
						.registdate(rs.getDate("REGISTDATE"))
						.build();
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 검색기 추가 메소드
	public String addSelect(BookDTO dto) {
		String[] category = dto.getCategory().split(",");
		String[] searchArr = dto.getSearch().split(" ");
		if (category.length == 0 || searchArr.length == 0) {
			return null;
		}
		String query = " WHERE";
		for (int i = 0; i < category.length; i++) {
			query += " (";
			for (int j = 0; j < searchArr.length; j++) {
				if(j != 0) {
					query += " AND " + category[i] + " LIKE '%" + searchArr[j] + "%' ";
				} else {
					query += category[i] + " LIKE '%" + searchArr[j] + "%'";
				}
			}
			if(i != category.length-1) {
				query += ") OR";
			} else {
				query += ")";
			}
			
		}
		return query;
	}
	
}
