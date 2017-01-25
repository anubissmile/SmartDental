package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.person.model.DoctorModel;
import com.smict.person.model.PatientModel;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;


	public class DoctorTypeData
	{
	
		DBConnect agent = new DBConnect();
		Connection conn = null;
		Statement Stmt = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		DateUtil dateUtil = new DateUtil();
	
	
	public void add_DocType(DoctorModel docModel)
	{
		String sql = "insert into doctor_position(position_id, position_name_th, position_name_en,position_name_short) values (?,?,?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			//System.out.println(sql);		
			
			pStmt.setString(1,docModel.getPosition_id());
			pStmt.setString(2,docModel.getPosition_name_th());
			pStmt.setString(3,docModel.getPosition_name_en());
			pStmt.setString(4,docModel.getPosition_name_short());
			
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
	
	public List<DoctorModel> select_DocType(String position_id,String position_name_th,String position_name_en,String position_name_short) throws IOException, Exception
	{
		String sqlQuery = "select * from doctor_position where ";

		if (new Validate().Check_String_notnull_notempty(position_id))
			sqlQuery += "position_id = '" + position_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(position_name_th))
			sqlQuery += "position_name_th like '%" + position_name_th + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(position_name_en))
			sqlQuery += "position_name_en like '%" + position_name_en + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(position_name_short))
			sqlQuery += "position_name_short like '%" + position_name_short + "%' and ";

		sqlQuery += "position_id != '' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		while (rs.next())
		{
			DoctorModel docModel = new DoctorModel();
			docModel.setPosition_id(rs.getString("position_id"));
			docModel.setPosition_name_th(rs.getString("position_name_th"));
			docModel.setPosition_name_en(rs.getString("position_name_en"));
			docModel.setPosition_name_short(rs.getString("position_name_short"));
			ResultList.add(docModel);		
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	public Boolean DeleteDocType(String position_id){
		
		String sqlQuery = "delete from doctor_position where position_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, position_id);
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
	public int UpdateDocType(String position_id,String position_name_th,String position_name_en,String position_name_short)
	{
		String sqlQuery = "update doctor_position set position_id = ? , position_name_th = ? , position_name_en = ? ,position_name_short=? "
				+ "where position_id = ?";
		int rowsupdate = 0;
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, position_id);
			pStmt.setString(2, position_name_th);
			pStmt.setString(3, position_name_en);
			pStmt.setString(4, position_name_short);
			pStmt.setString(5, position_id);
			rowsupdate = pStmt.executeUpdate();

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
}