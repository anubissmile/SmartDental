package ldc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static Connection conn = null;
	public Connection getConnectMYSql()  throws Exception, IOException {
			
			try	{ 
				
				Class.forName ("com.mysql.jdbc.Driver");
				//Class.forName ("org.gjt.mm.mysql.Driver");
				String dbName = "smart_dental";
				//String hostname = "pcpnru.cre4njgwawzc.ap-southeast-1.rds.amazonaws.com";  // amazon
<<<<<<< HEAD
				//	String hostname = "smartict.ar-bro.net";  // smart server
				String hostname = "192.168.1.96";
=======
				//String hostname = "smartict.ar-bro.net";  // smart server
				String hostname = "127.0.0.1";
>>>>>>> 09a14bfdd9683c3a2427f030986c861f39570c07
				String port = "3306";
				//	String dbUserName = "root";
				String dbUserName = "james";
				//String dbPassword = "a8s5T5d4"; // amazon
<<<<<<< HEAD
				//	String dbPassword = "a010103241c"; // smart server
				String dbPassword = "1234";
=======
				//String dbPassword = "a010103241c"; // smart server
				String dbPassword = "";
>>>>>>> 09a14bfdd9683c3a2427f030986c861f39570c07
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
}
