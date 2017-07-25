import java.sql.*;
import javax.sql.*;

public class JDBCTestResultSet {	
	public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "c##root"; 
        String password = "root"; 
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
			//TYPE_SCROLL_INSENSITIVE->结果集拿出来后对滚动是否敏感
			//CONCUR_READ_ONLY->并发访问时，只读
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select * from dept order by deptno desc");
/*			while(rs.next()){
				System.out.println("DEPTNO: " + rs.getString("deptno"));
			}*/
			//rs.last()+ rs.getRow()返回值可确定一共有多少条记录
			rs.last();
			System.out.println("共" + rs.getRow() +"条记录");
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
//			rs.previous();
//			System.out.println(rs.absolute(1));
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
