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

import com.smict.person.model.Person;
import com.smict.person.model.Pre_nameModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;


	public class MarriedStatusData
	{
		DBConnect agent = new DBConnect();
		Connection conn = null;
		Statement Stmt = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		DateUtil dateUtil = new DateUtil();
	
	public void add_MarriedStatus(Person person)
	{
		String sql = "insert into married_status(married_statusid, married_statusname) values (?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			//System.out.println(sql);		
			
			pStmt.setString(1,person.getMarried_statusid());
			pStmt.setString(2,person.getMarried_statusname());
			
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
	

	
	public List<Person> select_MarriedStatus(Person person) throws IOException, Exception{
		
		String sqlQuery = "select * from married_status where ";

		if (new Validate().Check_String_notnull_notempty(person.getMarried_statusid()))
			sqlQuery += "married_statusid = '" + person.getMarried_statusid() + "' and ";

		if (new Validate().Check_String_notnull_notempty(person.getMarried_statusname()))
			sqlQuery += "married_statusname like '%" + person.getMarried_statusname() + "%' and ";
	
		sqlQuery += "married_statusid != '' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<Person> ResultList = new ArrayList<Person>();
		
		while (rs.next())
		{			
			ResultList.add(new Person(rs.getString("married_statusid"), rs.getString("married_statusname")));	
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}

	public Boolean Check_MarriedStatus(String married_statusid){
		
		String sqlQuery = "select status_married from patient where status_married = ? limit 1";
		Boolean checkMarried = false;
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, married_statusid);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				checkMarried = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkMarried;
	}
	
	public Boolean DeleteMarriedStatus(String married_statusid){
		
		String sqlQuery = "delete from married_status where married_statusid = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, married_statusid);
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

	public int UpdateMarriedStatus(String married_statusid, String married_statusname){
		
		String sqlQuery = "update married_status set married_statusname = ? "
				+ "where married_statusid = ?";
		int rowsupdate = 0;
		try {
			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, married_statusname);
			pStmt.setString(2, married_statusid);
			
			rowsupdate = pStmt.executeUpdate();
			conn.commit();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if (!pStmt.isClosed())
					pStmt.close();
				if (!conn.isClosed())
					conn.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rowsupdate;
	}	
}