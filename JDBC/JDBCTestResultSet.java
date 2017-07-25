import java.sql.*;
import javax.sql.*;

public class JDBCTestResultSet {	
	public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "c##root"; 
        String password = "root"; 
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
			//TYPE_SCROLL_INSENSITIVE->������ó�����Թ����Ƿ�����
			//CONCUR_READ_ONLY->��������ʱ��ֻ��
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select * from dept order by deptno desc");
/*			while(rs.next()){
				System.out.println("DEPTNO: " + rs.getString("deptno"));
			}*/
			//rs.last()+ rs.getRow()����ֵ��ȷ��һ���ж�������¼
			rs.last();
			System.out.println("��" + rs.getRow() +"����¼");
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
//			rs.previous();
//			System.out.println(rs.absolute(1));
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
