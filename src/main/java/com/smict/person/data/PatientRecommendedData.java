package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.smict.person.model.Pre_nameModel;
import com.smict.person.model.RecommendedModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;


	public class PatientRecommendedData
	{
	
		DBConnect agent = new DBConnect();
		Connection conn = null;
		Statement Stmt = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		DateUtil dateUtil = new DateUtil();
	
	public void add_Recommended(RecommendedModel recommendedModel)
	{
		String sql = "insert into patient_typerecommended(typerecommended, typerecommended_name) values (?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			//System.out.println(sql);		
			
			pStmt.setInt(1,recommendedModel.getTyperecommended());
			pStmt.setString(2,recommendedModel.getTyperecommended_name());
			
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
	

	
	public List<RecommendedModel> select_Recommended(RecommendedModel recommendedModel) throws IOException, Exception
	{
		String sqlQuery = "select * from patient_typerecommended where ";

		if (new Validate().checkIntegerNotZero(recommendedModel.getTyperecommended()))
			sqlQuery += "typerecommended = '" + recommendedModel.getTyperecommended() + "' and ";

		if (new Validate().Check_String_notnull_notempty(recommendedModel.getTyperecommended_name()))
			sqlQuery += "typerecommended_name like '%" + recommendedModel.getTyperecommended_name() + "%' and ";

		sqlQuery += "typerecommended != '' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<RecommendedModel> ResultList = new ArrayList<RecommendedModel>();
		while (rs.next()){
			ResultList.add(new RecommendedModel(rs.getInt("typerecommended"), rs.getString("typerecommended_name")));
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
	}
	
	public Map<String,String> getMapRecommended(RecommendedModel recommendedModel){
		List<RecommendedModel> recommendedList = null;
		try {
			recommendedList = select_Recommended(recommendedModel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> mapRecommended = new HashMap<String, String>();
		for(RecommendedModel recomModel : recommendedList){
			mapRecommended.put(String.valueOf(recomModel.getTyperecommended()) , recomModel.getTyperecommended_name());
		}
		return mapRecommended;
	}

	public Boolean CheckRecommended(int typerecommended)
	{
		String sqlQuery = "select typerecommended from patient where typerecommended = ? limit 1";
		Boolean checkPreName = false;
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1, typerecommended);
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
	public Boolean DeleteRecommended(int typerecommended){
		
		String sqlQuery = "delete from patient_typerecommended where typerecommended = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1,typerecommended);
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



	public int UpdateRecommended(RecommendedModel recommendedModel)
	{
		String sqlQuery = "update patient_typerecommended set typerecommended_name = ? "
				+ "where typerecommended = ?";
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, recommendedModel.getTyperecommended_name());
			pStmt.setInt(2, recommendedModel.getTyperecommended());
			
			
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
			pre_name_th = "���";
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