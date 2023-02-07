package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.ReplyVO;

public class ReplyDao {
	
	private DataSource dataSource; 
	
	public ReplyDao() {
		dataSource = ConnectionUtil.getDatasource();
	}

	public List<ReplyVO> list(int bno) {
		List<ReplyVO> list = new ArrayList<>(); 
		String query = "SELECT * FROM reply_tbl where bno=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
						.rno(rs.getInt("RNO"))
						.bno(rs.getInt("BNO"))
						.reply(rs.getString("REPLY"))
						.writerId(rs.getString("WRITERID"))
						.writerNick(rs.getString("WRITERNICK"))
						.replyDate(rs.getDate("REPLYDATE"))
						.modifyDate(rs.getDate("MODIFYDATE"))
						.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insert(ReplyVO vo) {
		String query = "insert into reply_tbl (rno,bno,reply,writerId,writerNick) values(seq_reply.nextval, ?, ?, ?,?)";
		String query2 = "update BOARD_seohyanglim set replyCount=replyCount+1 where bno = ?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
					conn.setAutoCommit(false);
					pstmt.setInt(1, vo.getBno());
					pstmt.setString(2, vo.getReply());
					pstmt.setString(3, vo.getWriterId());
					pstmt.setString(4, vo.getWriterNick());
					pstmt.executeUpdate();
					pstmt2.setInt(1, vo.getBno());
					pstmt2.executeUpdate();
					conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void modify(ReplyVO vo) {
		String query = "update from reply_tbl set reply=? where rno=?";
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, vo.getReply());
			pstmt.setInt(2, vo.getRno());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void delete(int rno, int bno) {
		String query = "delete from reply_tbl where rno=?";
		String query2 = "update BOARD_seohyanglim set replyCount=replyCount-1 where bno = ?";
		
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
					conn.setAutoCommit(false);
					pstmt.setInt(1, rno);
					pstmt.executeUpdate();
					pstmt2.setInt(1, bno);
					pstmt2.executeUpdate();
					conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
