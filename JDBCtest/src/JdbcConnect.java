import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnect {

	public JdbcConnect() {
		try {
			Class.forName("org.postgresql.Driver");
			
			System.out.println("Driver Loading Success");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Fail");
		}
	}
	
	// database와 연결하는 작업
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			
			System.out.println("PostgreSQL Connection Success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int insert(String user_id, String name, int age) {
		// query
		String sql = "	insert into member(user_id, name, age, joindate)" 
				+ "		values('" + user_id + "','" + name + "'," + age +", now())";
		
		// connection - DB와 연결
		Connection conn = getConnection();
		// 상태 조사 - DB에 처리
		PreparedStatement stat = null;
		// 추가되었는지 확인하는 변수
		int count = 0;
		
		try {
			stat = conn.prepareStatement(sql);
			count = stat.executeUpdate(); // query 실행되는 부분 
			
			System.out.println("성공적으로 추가되었습니다");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// disconnect
			try {
				if(conn != null) {
					conn.close(); // 반드시 닫아주야 됨
				}
				if(stat != null) {
					stat.close(); // 너도
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return count;
	}
	
	
	public int delete(String id) {
		String sql = "delete from member where user_id = '" + id +"'";
		// 추가되었는지 확인하는 변수
		int count = 0;
		
		try (Connection conn = getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);){
			count = psmt.executeUpdate();
			
			System.out.println("성공적으로 삭제되었습니다");		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;	
	}
	
	
}
