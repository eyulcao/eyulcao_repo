import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTestTransaction {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "c##root";
		String passwd = "root";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
			conn.setAutoCommit(false);
			String sql = "insert into dept values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 26);
			pstmt.setString(2, "test4");
			pstmt.setString(3, "test4");
			pstmt.addBatch();
			pstmt.setInt(1, 27);
			pstmt.setString(2, "test5");
			pstmt.setString(3, "test5");
			pstmt.addBatch();
			pstmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if (conn!=null) {
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
