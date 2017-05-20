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
import com.smict.person.model.Person;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;


	public class EducationData
	{
	
		DBConnect agent = new DBConnect();
		Connection conn = null;
		Statement Stmt = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		DateUtil dateUtil = new DateUtil();
	
	public void add_Education_vocabulary(PatientModel patModel)
	{
		String sql = "insert into education_vocabulary(education_vocabulary_id, education_vocabulary_th, education_vocabulary_en) values (?,?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			//System.out.println(sql);		
			
			pStmt.setInt(1,patModel.getEducation_vocabulary_id());
			pStmt.setString(2,patModel.getEducation_vocabulary_th());
			pStmt.setString(3,patModel.getEducation_vocabulary_en());
			
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
	public void add_Education(PatientModel patModel)
	{
		String sql = "insert into education(education_id, education_th, education_en) values (?,?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			//System.out.println(sql);		
			
			pStmt.setString(1,patModel.getEducation_id());
			pStmt.setString(2,patModel.getEducation_th());
			pStmt.setString(3,patModel.getEducation_en());
			
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


	public List<PatientModel> select_education_vocabulary(String education_vocabulary_id, String education_vocabulary_th, String education_vocabulary_en) throws IOException, Exception
	{
		String sqlQuery = "select * from education_vocabulary where ";

		if (new Validate().Check_String_notnull_notempty(education_vocabulary_id))
			sqlQuery += "education_vocabulary_id = '" + education_vocabulary_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(education_vocabulary_th))
			sqlQuery += "education_vocabulary_th like '%" + education_vocabulary_th + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(education_vocabulary_en))
			sqlQuery += "education_vocabulary_en like '%" + education_vocabulary_en + "%' and ";

		sqlQuery += "education_vocabulary_id != '' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<PatientModel> ResultList = new ArrayList<PatientModel>();
		while (rs.next())
		{
			PatientModel patModel = new PatientModel();
			patModel.setEducation_vocabulary_id(rs.getInt("education_vocabulary_id"));
			patModel.setEducation_vocabulary_th(rs.getString("education_vocabulary_th"));
			patModel.setEducation_vocabulary_en(rs.getString("education_vocabulary_en"));
			ResultList.add(patModel);		
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	public List<PatientModel> select_education(String education_id, String education_th, String education_en) throws IOException, Exception
	{
		String sqlQuery = "select * from education where ";

		if (new Validate().Check_String_notnull_notempty(education_id))
			sqlQuery += "education_id = '" + education_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(education_th))
			sqlQuery += "education_th like '%" + education_th + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(education_en))
			sqlQuery += "education_en like '%" + education_en + "%' and ";

		sqlQuery += "education_id != '' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<PatientModel> ResultList = new ArrayList<PatientModel>();
		while (rs.next())
		{
			PatientModel patModel = new PatientModel();
			patModel.setEducation_id(rs.getString("education_id"));
			patModel.setEducation_th(rs.getString("education_th"));
			patModel.setEducation_en(rs.getString("education_en"));
			ResultList.add(patModel);		
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	public Boolean DeleteEducation_vocabulary(String education_vocabulary_id){
		
		String sqlQuery = "delete from education_vocabulary where education_vocabulary_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, education_vocabulary_id);
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
	public Boolean DeleteEducation(String education_id){
		
		String sqlQuery = "delete from education where education_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, education_id);
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
	public int UpdateEducation_vocabulary(String education_vocabulary_id, String education_vocabulary_th, String education_vocabulary_en)
	{
		String sqlQuery = "update education_vocabulary set education_vocabulary_id = ? , education_vocabulary_th = ? , education_vocabulary_en = ? "
				+ "where education_vocabulary_id = ?";
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, education_vocabulary_id);
			pStmt.setString(2, education_vocabulary_th);
			pStmt.setString(3, education_vocabulary_en);
			pStmt.setString(4, education_vocabulary_id);
			
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
	public int UpdateEducation(String education_id, String education_th, String education_en)
	{
		String sqlQuery = "update education set education_id = ? , education_th = ? , education_en = ? "
				+ "where education_id = ?";
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, education_id);
			pStmt.setString(2, education_th);
			pStmt.setString(3, education_en);
			pStmt.setString(4, education_id);
			
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
	public int add_multi_edu(List<Person> eduList){
		int edu_id = 0;
		String sql = "select max(doc_education_id)+1 as doc_education_id from doctor_education_vocabulary";
		try {
			conn = agent.getConnectMYSql();
		//	conn.setAutoCommit(false);
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				edu_id = rs.getInt("doc_education_id");
			}
			sql = "INSERT INTO doctor_education_vocabulary (doc_education_id,education_vocabulary_id,education_name,educational_background) VALUES ";
			int i = 0;
			for (Person eduModel : eduList) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+edu_id+",'"+eduModel.getEducation_vocabulary_id()+"','"+eduModel.getEducation_th()+"', '"+eduModel.getEducational_background()+"')";				
			}
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			edu_id = 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			edu_id = 0;
		}
		
		return edu_id;
	}
	public int add_multi_edu(List<Person> eduList,int edu_id){
		
		try {
			conn = agent.getConnectMYSql();
		//	conn.setAutoCommit(false);
		
			String sql = "INSERT INTO doctor_education_vocabulary (doc_education_id,education_vocabulary_id,education_name,educational_background) VALUES ";
			int i = 0;
			for (Person eduModel : eduList) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+edu_id+",'"+eduModel.getEducation_vocabulary_id()+"','"+eduModel.getEducation_th()+"','"+eduModel.getEducational_background()+"')";				
			}
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return edu_id;
	}
	public int del_multi_edu(int edu_id){
		
		try {
			conn = agent.getConnectMYSql();
		//	conn.setAutoCommit(false);
		
			String sql = "DELETE FROM doctor_education_vocabulary WHERE doc_education_id = "+edu_id;
			
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return edu_id;
	}
	public List<DoctorModel> get_multi_edu(int doc_education_id) throws IOException, Exception
	{
		String sqlQuery = "SELECT doctor_education_vocabulary.doc_education_id, doctor_education_vocabulary.education_vocabulary_id,"
				+ "education_vocabulary.education_vocabulary_th,doctor_education_vocabulary.education_name,doctor_education_vocabulary.educational_background "
				+ "FROM doctor_education_vocabulary "
				+ "INNER JOIN education_vocabulary ON education_vocabulary.education_vocabulary_id = doctor_education_vocabulary.education_vocabulary_id "
				+ "WHERE doctor_education_vocabulary.doc_education_id = "+doc_education_id;
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		while (rs.next())
		{
			DoctorModel pModel = new DoctorModel();
			pModel.setDoctor_education_vocabulary(rs.getInt("doc_education_id"));
			pModel.setEducation_vocabulary_id(rs.getInt("education_vocabulary_id"));
			pModel.setEducation_vocabulary_th(rs.getString("education_vocabulary_th"));
			pModel.setEducation_name(rs.getString("education_name"));
			pModel.setEducational_background(rs.getString("educational_background"));
			ResultList.add(pModel);		
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
}