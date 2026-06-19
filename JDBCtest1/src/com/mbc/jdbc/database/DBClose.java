package com.mbc.jdbc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBClose {
	// 언제든지 호출 가능 
	public static void close(Connection conn, PreparedStatement psmt, ResultSet rs) {
		try {
			
			if(conn!=null) {
				conn.close();
			}
			
			if(psmt!=null) {
				psmt.close();
			}
			
			if(rs!=null) {
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
