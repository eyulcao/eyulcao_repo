import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class CollectionPool {
	List<Connection> connections;
	void initPool(){
		//建立100个连接放到connections
	}
	public Connection getConn(){
		//遍历哪一个不忙，从池中拿出Connection
		//所有都忙，就建立一个新的Connection/或扩充池子容量/或建立等待队列
		return new MyConn();
	}
	
	class MyConn implements Connection {
		boolean busy;
		Date startTime;
		Connection conn;
		
		//精灵线程/守护线程
		private class DeamonThread implements Runnable{
			//监视哪些MyConn死掉了，当前系统时间-开始时间超时
			//每隔一段时间检测一下
			public void run(){
			}
		}
		@Override
		public boolean isWrapperFor(Class<?> arg0) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public <T> T unwrap(Class<T> arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void abort(Executor arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void clearWarnings() throws SQLException {
			conn.clearWarnings();//代理Proxy
		}

		@Override
		public void close() throws SQLException {
			//busy = false;(同步考虑)
		}

		@Override
		public void commit() throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Statement createStatement() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Statement createStatement(int arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Statement createStatement(int arg0, int arg1, int arg2)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getClientInfo(String arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isValid(int arg0) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String nativeSQL(String arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String arg0, int arg1, int arg2)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String arg0, int arg1, int arg2,
				int arg3) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int[] arg1)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, String[] arg1)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1, int arg2,
				int arg3) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void releaseSavepoint(Savepoint arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void rollback() throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void rollback(Savepoint arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setAutoCommit(boolean arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setCatalog(String arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setClientInfo(Properties arg0) throws SQLClientInfoException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setClientInfo(String arg0, String arg1)
				throws SQLClientInfoException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setHoldability(int arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setReadOnly(boolean arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Savepoint setSavepoint(String arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSchema(String arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setTransactionIsolation(int arg0) throws SQLException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
			// TODO Auto-generated method stub

		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
