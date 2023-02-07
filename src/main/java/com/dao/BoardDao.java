package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;
import com.domain.Criteria;

public class BoardDao {
	
	private DataSource dataSource;
	
	public BoardDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 게시판 가져오기
	public List<BoardVO> selectBoard(Criteria criteria) {
		String allOrOne = "";
		// 타입이 있다면
		if(criteria.getType()!=null) {
			allOrOne = "WRITETYPE=? and";
		}
		String query = "SELECT ROWNUM, BNO, WRITETYPE, TITLE, WRITERID, WRITERNICKNAME, WRITEDATE, MODIFYDATE, replyCount, hitsCount "
				+ " FROM (SELECT /*+INDEX_DESC(BOARD_seohyanglim PK_BOARD_seohyanglim)*/"
				+ " ROWNUM AS RN, BNO, WRITETYPE, TITLE, WRITERID, WRITERNICKNAME, WRITEDATE, MODIFYDATE, replyCount, hitsCount "
				+ " FROM BOARD_seohyanglim WHERE " + allOrOne + " ROWNUM <= ?) WHERE RN > ?";
		List<BoardVO> list = new ArrayList<>();
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			
			// 하나의 게시판
			if(criteria.getType()!=null) {
				int maxRow = criteria.getPageNum()*criteria.getAmount();
				int minRow = (criteria.getPageNum()-1)*criteria.getAmount();
				pstmt.setString(1, criteria.getType());
				pstmt.setInt(2, maxRow);
				pstmt.setInt(3, minRow);
				
				// 모든 게시판
			} else {
				int maxRow = criteria.getPageNum()*criteria.getAmount(); 
				int minRow = (criteria.getPageNum()-1)*criteria.getAmount(); 
				pstmt.setInt(1, maxRow);
				pstmt.setInt(2, minRow);
			}
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					BoardVO vo = BoardVO.builder()
							.bno(rs.getInt("BNO"))
							.writeType(rs.getString("WRITETYPE"))
							.title(rs.getString("TITLE"))
							.writerId(rs.getString("WRITERID"))
							.writerNickname(rs.getString("WRITERNICKNAME"))
							.writeDate(rs.getDate("WRITEDATE"))
							.modifyDate(rs.getDate("MODIFYDATE"))
							.replyCount(rs.getInt("replyCount"))
							.hitsCount(rs.getInt("hitsCount"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 리스트 종류 이름 하나씩 가져오기
	public List<String> selectAlltype() {
		String query = "SELECT distinct(WRITETYPE) FROM BOARD_seohyanglim";
		List<String> list = new ArrayList<String>();
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				){
			while(rs.next()) {
				list.add(rs.getString("WRITETYPE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 전체 게시글수
	public int selectAllCount() {
		String query = "SELECT COUNT(BNO) FROM BOARD_seohyanglim";
		int totalCount = 0;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(BNO)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 선택한 게시글수
	public int selectChoiceCount(String type) {
		String query = "SELECT COUNT(BNO) FROM BOARD_seohyanglim where WRITETYPE=?";
		int totalCount = 0;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, type);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					totalCount = rs.getInt("COUNT(BNO)");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 글 상세 
	public BoardVO selectOne(int bno) {
		BoardVO vo = null; 
		String query = "select * from BOARD_seohyanglim where bno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setInt(1, bno);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = BoardVO.builder()
							.bno(rs.getInt("BNO"))
							.writeType(rs.getString("WRITETYPE"))
							.title(rs.getString("TITLE"))
							.content(rs.getString("CONTENT"))
							.writerId(rs.getString("WRITERID"))
							.writerNickname(rs.getString("WRITERNICKNAME"))
							.replyCount(rs.getInt("replyCount"))
							.hitsCount(rs.getInt("hitsCount"))
							.fileName(rs.getString("FileName"))
							.modifyDate(rs.getDate("MODIFYDATE"))
							.writeDate(rs.getDate("writeDate"))
							.build();
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
			return vo; 
	}
	
	// 조회시 조회수 상승
	public void updateHit(int bno) {
		String query = "update BOARD_seohyanglim set hitsCount=hitsCount+1 where bno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	

	// 글쓰기
	public int insertBoard(BoardVO vo) {
		String query = "INSERT INTO BOARD_seohyanglim"
				+ " (BNO,WRITETYPE, TITLE, CONTENT, WRITERID, WRITERNICKNAME,FileName)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		int boardNo = getNewBno();
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, boardNo);
			pstmt.setString(2,vo.getWriteType());
			pstmt.setString(3,vo.getTitle());
			pstmt.setString(4,vo.getContent());
			pstmt.setString(5,vo.getWriterId());
			pstmt.setString(6,vo.getWriterNickname());
			pstmt.setString(7,vo.getFileName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardNo;
	}
	
	// 새로운 글번호 생성 
	public int getNewBno() {
		int boardNo = 0; 
		String query = "SELECT MAX(BNO)+1 as boardNO FROM BOARD_seohyanglim";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		){
			if(rs.next()) {
				boardNo = rs.getInt("boardNO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardNo;
	}
	
	// 글수정
	public void updateBoard(BoardVO vo) {
		if(vo.getFileName()!=null || vo.getFileName().length()>0) { // 새 파일업로드가 있을때
			String query = "update BOARD_seohyanglim set writeType=?, title=?, content=?, fileName=? where bno=?";
			try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setString(1,vo.getWriteType());
				pstmt.setString(2,vo.getTitle());
				pstmt.setString(3,vo.getContent());
				pstmt.setString(4,vo.getFileName());
				pstmt.setInt(5, vo.getBno());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else { // 새 파일 업로드가 있을때
			String query = "update BOARD_seohyanglim set writeType=?, title=?, content=? where bno=?";
			try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setString(1,vo.getWriteType());
				pstmt.setString(2,vo.getTitle());
				pstmt.setString(3,vo.getContent());
				pstmt.setInt(4, vo.getBno());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 글 삭제
	public void deleteBnoard(int bno) {
		String query = "delete from BOARD_seohyanglim where bno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
