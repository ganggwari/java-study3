package com.mbc.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mbc.jdbc.database.DBClose;
import com.mbc.jdbc.database.DBConnection;
import com.mbc.jdbc.dto.MemberDto;

public class MemberDao {
	
	public MemberDao() {
		DBConnection.initConnection();
	}
	
	public int insert(String user_id, String name, int age) {
		String sql = "	insert into member(user_id, name, age, joindate)"
				+ "		values(?, ?, ?, now())";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setString(2, name);
			psmt.setInt(3, age);
			
			count = psmt.executeUpdate();
			System.out.println("Insert Success");
			
		} catch (SQLException e) {
			System.out.println("Insert Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count;
	}
	
	public int delete(String id) {
		String sql = "	delete from member "
				+ "		where user_id = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			count = psmt.executeUpdate();
			System.out.println("Delete Success");
			
		} catch (SQLException e) {
			System.out.println("Delete Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count;
	}
	
	// 1개의 데이터(row)를 산출
	public MemberDto selectOne(String usre_id) {
		String sql = "	select user_id, name, age, joindate"
				+ "		from member"
				+ "		where user_id = ?";
		
		Connection conn = null; // database와 연결
		PreparedStatement psmt = null; // query 실행
		
		ResultSet rs = null; // 결과값을 받기 위한 객체
		
		MemberDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, usre_id);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("user_id"); // 컬럼명 지정해서 가져옴
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String joindate = rs.getString("joindate");
				
				dto = new MemberDto(id, name, age, joindate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
		
	}
	
	// 다중 데이터를 산출
	public List<MemberDto> selectList() {
		String sql = "	select user_id, name, age, joindate"
				+ "		from member";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("user_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String joindate = rs.getString("joindate");
				
				MemberDto dto = new MemberDto(id, name, age, joindate);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	// update
	public int update(String id, String name) {
		String sql = "	update member"
				+ "		set name = ?"
				+ "		where user_id = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int cnt = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, id);
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return cnt;
	}
}
