import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTestBatchUpdate {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "c##root";
		String passwd = "root";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
			stmt = conn.createStatement();
/*			rs = stmt.executeQuery("select * from dept");
			while(rs.next()){
				System.out.println(rs.getInt("deptno"));
			}	*/	
			stmt.addBatch("insert into dept values(21,'test','test')");
			stmt.addBatch("insert into dept values(22,'test2','test')");
			stmt.addBatch("insert into dept values(23,'test3','test')");
			//默认自动提交，如果中途出错，或有问题
			stmt.executeBatch();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
					rs = null;
				} 
				if (stmt!=null) {
					stmt.close();
					stmt = null;
				} 
				if (conn!=null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
