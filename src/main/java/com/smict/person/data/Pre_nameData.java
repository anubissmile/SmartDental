package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.smict.person.model.Pre_nameModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;


	public class Pre_nameData
	{
	
		DBConnect agent = new DBConnect();
		Connection conn = null;
		Statement Stmt = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		DateUtil dateUtil = new DateUtil();
	
	public void add_pre_name(Pre_nameModel Class_Pre_nameModel)
	{
		String sql = "insert into pre_name(pre_name_id, pre_name_th, pre_name_en) values (?,?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			//System.out.println(sql);		
			
			pStmt.setString(1,Class_Pre_nameModel.getPre_name_id());
			pStmt.setString(2,Class_Pre_nameModel.getPre_name_th());
			pStmt.setString(3,Class_Pre_nameModel.getPre_name_en());
			
			pStmt.executeUpdate();
			if(!conn.isClosed()) conn.close();
			if(pStmt.isClosed()) pStmt.close();
			
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
	}
	

	
	public List<Pre_nameModel> select_pre_name(String pre_name_id, String pre_name_th, String pre_name_en) throws IOException, Exception
	{
		String sqlQuery = "select * from pre_name where ";

		if (new Validate().Check_String_notnull_notempty(pre_name_id))
			sqlQuery += "pre_name_id = '" + pre_name_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(pre_name_th))
			sqlQuery += "pre_name_th like '%" + pre_name_th + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(pre_name_en))
			sqlQuery += "pre_name_en like '%" + pre_name_en + "%' and ";

		sqlQuery += "pre_name_id != '' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<Pre_nameModel> ResultList = new ArrayList<Pre_nameModel>();
		while (rs.next())
		{
			
			ResultList.add(new Pre_nameModel(rs.getString("pre_name_id"), rs.getString("pre_name_th"), rs.getString("pre_name_en")));
		

		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}



	public String GetHighest_add_pre_name()
	
	{
		String sqlQuery = "select MAX(pre_name_id) as pre_name_id from pre_name";
		String ResultString = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultString = rs.getString("pre_name_id");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
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

		return ResultString;
	}



	public String PlusOneID_FormatID(String pre_name_id)
	{
		if(pre_name_id == null)
		{
			pre_name_id = "01";
		}
		else
		{
			String ResultString_plusone = String.valueOf((Integer.parseInt(pre_name_id)+1));
			switch(ResultString_plusone.length())
			{
			case 1:pre_name_id="0"+ResultString_plusone; break;
			default :pre_name_id=ResultString_plusone; break;
			}
		}
		return pre_name_id;
	}


	public Boolean CheckPre_name(String pre_name_id)
	{
		String sqlQuery = "select pre_name_id from patient where pre_name_id = ? limit 1";
		Boolean checkPreName = false;
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, pre_name_id);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				checkPreName = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return checkPreName;
	}
	public Boolean DeletePre_name(String pre_name_id){
		
		String sqlQuery = "delete from pre_name where pre_name_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, pre_name_id);
			int rowsupdate = pStmt.executeUpdate();
			

			if (rowsupdate > 0)
				delete_success = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (!pStmt.isClosed())
					pStmt.close();
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return delete_success;
		
	}



	public int UpdatePre_name(String pre_name_id, String pre_name_th, String pre_name_en)
	{
		String sqlQuery = "update pre_name set pre_name_id = ? , pre_name_th = ? , pre_name_en = ? "
				+ "where pre_name_id = ?";
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, pre_name_id);
			pStmt.setString(2, pre_name_th);
			pStmt.setString(3, pre_name_en);
			pStmt.setString(4, pre_name_id);
			
			rowsupdate = pStmt.executeUpdate();
			conn.commit();

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
		finally 
			{
				try 
				{
					if (!pStmt.isClosed())
						pStmt.close();
					if (!conn.isClosed())
						conn.close();
				} 
			
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return rowsupdate;
	}


	/*
	public String PlusOneID_FormatNameTH(String pre_name_th) 
	{
		// TODO Auto-generated method stub
		if(pre_name_th == null)
		{
			pre_name_th = "¡¡¡";
		}
		else
		{
			String ResultString_plusone = String.valueOf((Integer.parseInt(pre_name_th)+1));
			switch(ResultString_plusone.length())
			{
			case 0:pre_name_th="0"+ResultString_plusone; break;
			default :pre_name_th=ResultString_plusone; break;
			}
		}
		return pre_name_th;
	}
	
	public String PlusOneID_FormatNameEN(String pre_name_en) 
	{
		// TODO Auto-generated method stub
		if(pre_name_en == null)
		{
			pre_name_en = "aaa";
		}
		else
		{
			String ResultString_plusone = String.valueOf((Integer.parseInt(pre_name_en)+1));
			switch(ResultString_plusone.length())
			{
			case 0:pre_name_en="0"+ResultString_plusone; break;
			default :pre_name_en=ResultString_plusone; break;
			}
		}
		return pre_name_en;
	}
	*/
	
	
}