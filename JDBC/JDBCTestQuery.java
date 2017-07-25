import java.sql.*;

public class JDBCTestQuery {	
	public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "c##root"; 
        String password = "root"; 
		String str = "select * from dept";
		//1)finally里的rs等也要认识，所以在开头定义
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//2)使用try..catch..捕获异常，代替throws exception
		try {			
			//Class.forName("oracle.jdbc.OracleDriver").newInstance();
			//new oracle.jdbc.OracleDriver();
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(str);
			while(rs.next()){
				//rs.getString(1)：第一列的结果转换成String类型
				System.out.println("DEPTNO: " + rs.getString(1));
				//System.out.println("DEPTNO: " + rs.getInt("deptno"));
			}
//			返回int值表示执行结果
//			stmt.executeUpdate("update dept set loc='60' where deptno=60");
		} catch (ClassNotFoundException e) {
			//3)最好能够记录日志log4j
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//4)保证rs.close不发生异常
				//5)不管发不发生异常，保证close
				//6)面试体现，方便垃圾收集器收集，其实rs.close()已自动将rs设置成null
				if(rs!=null){	
					rs.close();	
					rs=null;}
				if(stmt!=null){	
					stmt.close();
					stmt=null;}
				if(conn!=null){	
					conn.close();	
					conn=null;}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}		
	}
}
