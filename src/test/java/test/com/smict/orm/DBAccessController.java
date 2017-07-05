package test.com.smict.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccessController {
	
	/**
	 * Data base connection info
	 */
	private String dbName = "smartdental";
	private String hostname = "127.0.0.1";
	private String port = "3306";
	private String dbUserName = "root";
	private String dbPassword = "";
	
	/**
	 * Make new connection
	 * @author anubi
	 * @param 
	 * @return Connection | connection instance
	 */
	public Connection connect(boolean isAutoCommit){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			

			String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName
					+ "?useUnicode=yes&characterEncoding=UTF-8&user=" + dbUserName + "&password=" + dbPassword
					+ "&zeroDateTimeBehavior=convertToNull"
					+ "&allowMultiQueries=true";
			
			if(isAutoCommit != true && isAutoCommit != false){
				isAutoCommit = true;
			}
			Connection con = DriverManager.getConnection(jdbcUrl);
			con.setAutoCommit(isAutoCommit);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Set auto commit for transaction.
	 * @param Connection con | 
	 * @param boolean isAutoCommit | 
	 * @return Connection 
	 */
	public Connection begin(Connection con, boolean isAutoCommit){
		if(isAutoCommit != true && isAutoCommit != false){
			isAutoCommit = true;
		}
		
		try {
			con.setAutoCommit(isAutoCommit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	/**
	 * Disconnect database
	 * @param Connection con | Connection instance
	 * @param PreparedStatement pSt | 
	 * @param Statement st |
	 */
	public void close(Connection con, PreparedStatement pSt, Statement st){
		try{
			if(st != null){
				if(!st.isClosed()){
					st.close();
				}
			}
			
			if(pSt != null){
				if(!pSt.isClosed()){
					pSt.close();
				}
			}
			
			if(con != null){
				if(!con.isClosed()){
					con.close();
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} 
	}
	
	/**
	 * Make new statement instance
	 * @param Connection con
	 * @return Statement 
	 */
	public Statement makeStatementInstance(Connection con){
		try {
			return con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Make prepared statement instance
	 * @param Connection con
	 * @param String SQL
	 * @return PreparedStatement
	 */
	public PreparedStatement makePreparedStatementInstance(Connection con, String SQL){
		try {
			return con.prepareStatement(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int executeUpdate(PreparedStatement pSt){
		try {
			return pSt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ResultSet executeQuery(Statement st, String SQL){
		try {
			return st.executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void commit(Connection con){
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection con){
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int count(ResultSet rs){
		int row = 0;
		try {
			if (rs.last()) {
				row = rs.getRow();
				rs.beforeFirst();
			}
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
}
