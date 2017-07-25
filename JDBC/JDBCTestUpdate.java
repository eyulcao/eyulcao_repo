import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTestUpdate {

	public static void main(String[] args) {
		//参数右键 Open Declaration查看变量在哪里声明
		//Run As -> Run Configuration -> Arguments
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
			String sql = "insert into dept values("+deptno+",'"+dname+"','"+loc+"')";
			//调试SQL经常使用的方法->SQL语句打印出来，放PL/SQL执行，看看具体错误。
			System.out.println(sql);
			stmt.executeUpdate(sql);

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
