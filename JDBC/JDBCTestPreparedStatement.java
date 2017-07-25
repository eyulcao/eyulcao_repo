import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTestPreparedStatement {

	public static void main(String[] args) {
		if(args.length != 3){
			System.out.println("输入参数需要是3个");
		}
		int deptno = 0;
		try {
			deptno = Integer.parseInt(args[0]);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		String dname = args[1];
		String loc = args[2];
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "c##root";
		String passwd = "root";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
			//? -> 占位符
			String sql = "insert into dept values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			pstmt.executeUpdate();
			System.out.println(sql);

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
				if (pstmt!=null) {
					pstmt.close();
					pstmt = null;
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
