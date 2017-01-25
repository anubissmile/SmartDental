package ldc.util;

import java.io.IOException;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.Statement;

public class Thailand {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null; 
	ResultSet rs = null;
	
	public String Get_Provinceid(String addr_provinceid)
	
	{
		String sqlQuery = "select province_name from provinces WHERE province_id = '"+addr_provinceid+"' ";
		String addr_provincename = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				addr_provincename = rs.getString("province_name"); 
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addr_provincename;
	} 
	public String Get_Amphures(String addr_aumphurid)
	
	{
		String sqlQuery = "select AMPHUR_NAME from amphures WHERE AMPHUR_ID = '"+addr_aumphurid+"' ";
		String addr_aumphurname = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				addr_aumphurname = rs.getString("AMPHUR_NAME"); 
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return addr_aumphurname;
	}
	public String Get_District(String addr_districtid)
	
	{
		String sqlQuery = "select DISTRICT_NAME from districts WHERE DISTRICT_ID = '"+addr_districtid+"' ";
		String addr_districtname = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				addr_districtname = rs.getString("DISTRICT_NAME"); 
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return addr_districtname;
	}
}
