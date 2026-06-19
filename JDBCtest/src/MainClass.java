
public class MainClass {
	public static void main(String[] args) {

	/*
		JDBC(Java Data Base Connectivity)
		java와 database를 연결

	*/
		
		JdbcConnect jdbc = new JdbcConnect();
		//jdbc.getConnection();
		/*
		// insert
		String user_id = "def65";
		String name = "bob";
		int age = 20;
		
		int cnt = jdbc.insert(user_id, name, age);
		System.out.println("count = "+cnt);
		*/
		
		// delete
		int count = jdbc.delete("def65");
		System.out.println("count = "+count);
	}
}
