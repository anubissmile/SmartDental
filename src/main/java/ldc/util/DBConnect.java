package ldc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
	
	private static Connection conn = null;
	private static PreparedStatement Pstmt = null;
	private static Statement Stmt = null;
	private static ResultSet rs = null;
	
	/**
	 * commandType was exist for checking command type that were query or manipulate.
	 * 1 = Query, 2 = Manipulate (Insert, Update, Delete), 3 = Nothing (Default).
	 */
	private static int commandType = 3;
	
	public Connection getConnectMYSql()  throws Exception, IOException {
			
			try	{ 
				
				Class.forName ("com.mysql.jdbc.Driver");
				//Class.forName ("org.gjt.mm.mysql.Driver");
				String dbName = "smart_dental";
				//String hostname = "pcpnru.cre4njgwawzc.ap-southeast-1.rds.amazonaws.com";  // amazon
				//String hostname = "smartict.ar-bro.net";  // smart server
				String hostname = "127.0.0.1";
				String port = "3306";
				String dbUserName = "root";
				//String dbPassword = "a8s5T5d4"; // amazon
				//String dbPassword = "a010103241c"; // smart server
				String dbPassword = "";
				String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
				port + "/" + dbName + "?useUnicode=yes&characterEncoding=UTF-8&user=" + dbUserName + "&password=" + dbPassword;

				conn = DriverManager.getConnection (jdbcUrl);
				
				return conn;
				
			} catch(ClassNotFoundException e) {
				throw new Exception("class not found "+e);
			
			}
			catch(SQLException se)
			{
				if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
				throw new Exception(se);	  
			}
			
		//	finally {
		//	    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
				 
		//	}
	}
	
	/**
	 * connecting to mysql.
	 * @author wesarut
	 * @return boolean | if connection succes then return true.
	 */
	public boolean connectMySQL(){
		try {
			getConnectMYSql();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Close the connection.
	 * @author wesarut
	 * @return boolean | if success then return true.
	 */
	public boolean disconnectMySQL(){
		try {
			
			if(commandType == 2){
				if(!Pstmt.isClosed()) Pstmt.close();
			}else if(commandType == 1){
				if(!Stmt.isClosed()) Stmt.close();
				if(!rs.isClosed()) rs.close();
			}
			
			if(!conn.isClosed()) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Execute the SQL manipulate command for insert, update, delete.
	 * @author wesarut
	 * @param SQL | String of SQL commands.
	 * @return integer | either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing.
	 */
	public int exeUpdate(String SQL){
		try {
			Pstmt = conn.prepareStatement(SQL);
			commandType = 2;
			return Pstmt.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Execute the Query command
	 * @author wesarut
	 * @param SQL | The String of SQL command.
	 * @return Object ResultSet | return the result record set.
	 */
	public ResultSet exeQuery(String SQL){
		try {
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			commandType = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

	
	/**
	 * GETTER SETTER ZONE
	 * @return
	 */
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}


















