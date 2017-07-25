import java.sql.*;

public class JDBCTestQuery {	
	public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "c##root"; 
        String password = "root"; 
		String str = "select * from dept";
		//1)finally���rs��ҲҪ��ʶ�������ڿ�ͷ����
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//2)ʹ��try..catch..�����쳣������throws exception
		try {			
			//Class.forName("oracle.jdbc.OracleDriver").newInstance();
			//new oracle.jdbc.OracleDriver();
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(str);
			while(rs.next()){
				//rs.getString(1)����һ�еĽ��ת����String����
				System.out.println("DEPTNO: " + rs.getString(1));
				//System.out.println("DEPTNO: " + rs.getInt("deptno"));
			}
//			����intֵ��ʾִ�н��
//			stmt.executeUpdate("update dept set loc='60' where deptno=60");
		} catch (ClassNotFoundException e) {
			//3)����ܹ���¼��־log4j
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//4)��֤rs.close�������쳣
				//5)���ܷ��������쳣����֤close
				//6)�������֣����������ռ����ռ�����ʵrs.close()���Զ���rs���ó�null
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
