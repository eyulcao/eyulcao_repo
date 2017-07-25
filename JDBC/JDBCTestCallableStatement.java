import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


public class JDBCTestCallableStatement {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "c##root";
		String passwd = "root";
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
			//? -> 占位符
			//procedure p
	        //(v_a in number, v_b number, v_ret out number, v_temp in out number)
			cstmt = conn.prepareCall("{call P(?,?,?,?)}");
			//java.sql.Types -> 指定输出参数的类型
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.setInt(1, 3);
			cstmt.setInt(2, 4);
			cstmt.setInt(4, 5);
			cstmt.execute();
			System.out.println(cstmt.getInt(3));

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
				if (cstmt!=null) {
					cstmt.close();
					cstmt = null;
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
