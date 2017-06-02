package com.smict.treatment.data;

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

import com.smict.all.model.TreatmentMasterModel;

import ldc.util.DBConnect;
import ldc.util.Validate;

public class TreatmentGroupData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	public List<TreatmentMasterModel> Select_treatment_group(String treatment_group_code,String treatment_group_name,String labmode_id){
		List<TreatmentMasterModel> ResultList = new ArrayList();
		String sqlQuery = "select treatment_group_code,labmode_id,treatment_group_name from treatment_group where ";
		if (new Validate().Check_String_notnull_notempty(treatment_group_code))
			sqlQuery += "treatment_group_code = '" + treatment_group_code + "' and ";

		if (new Validate().Check_String_notnull_notempty(treatment_group_name))
			sqlQuery += "treatment_group_name like '%" + treatment_group_name + "%' and ";

		if (new Validate().Check_String_notnull_notempty(labmode_id))
			sqlQuery += "labmode_id like '%" + labmode_id + "%' and ";
		
		sqlQuery += "treatment_group_code != '' ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			
			while(rs.next()){
				TreatmentMasterModel tmd = new TreatmentMasterModel();
				tmd.setTreatment_group_code(rs.getString("treatment_group_code"));
				tmd.setTreatment_group_name(rs.getString("treatment_group_name"));
				tmd.setLabmode_id(rs.getString("labmode_id"));
				ResultList.add(tmd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultList;
	}
	public int AddTreatmentGroup(TreatmentMasterModel teatmentModel) {
		String sqlQuery = "INSERT INTO treatment_group (treatment_group_code,labmode_id,treatment_group_name) "
				+ "values (?,?,?)";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery); 
			pStmt.setString(1, teatmentModel.getTreatment_group_code());
			pStmt.setString(2, teatmentModel.getLabmode_id());
			pStmt.setString(3, teatmentModel.getTreatment_group_name());
			rowsupdate = pStmt.executeUpdate();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowsupdate;
	}
	
	public Boolean DeleteTreatmentGroup(String treatment_group_code) {
		String sqlQuery = "delete from treatment_group where treatment_group_code = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, treatment_group_code);
			int rowsupdate = pStmt.executeUpdate();
			

			if (rowsupdate > 0)
				delete_success = true;
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return delete_success;
	}
	public int UpdateTreatmentGroup(TreatmentMasterModel teatmentModel) {
		String sqlQuery = "update treatment_group set labmode_id = ? , treatment_group_name = ? "
				+ "where treatment_group_code = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, teatmentModel.getLabmode_id());
			pStmt.setString(2, teatmentModel.getTreatment_group_name());
			pStmt.setString(3, teatmentModel.getTreatment_group_code());
			
			rowsupdate = pStmt.executeUpdate();

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

		return rowsupdate;
	}
	public List<TreatmentMasterModel> gettreatmentCategorylist(){
		
		String sqlQuery = "select treatment_category.id,treatment_category.name,treatment_category.code,group_id,treatment_group.name, "
							+ "treatment_group.code "
							+ "from treatment_category "
							+ "inner join treatment_group on treatment_group.id = treatment_category.group_id ";
		
		
		List<TreatmentMasterModel> ResultList = new ArrayList<TreatmentMasterModel>();
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			
			while(rs.next()){
				TreatmentMasterModel tmd = new TreatmentMasterModel();
				tmd.setTreatCategory_id(rs.getString("treatment_category.id"));
				tmd.setTreatCategory_code(rs.getString("treatment_category.code"));
				tmd.setTreatCategory_name(rs.getString("treatment_category.name"));
				tmd.setTreatCategory_groupid(rs.getString("treatment_category.group_id"));
				tmd.setTreatment_group_name(rs.getString("treatment_group.name"));
				tmd.setTreatment_group_code(rs.getString("treatment_group.code"));
				ResultList.add(tmd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultList;
	}
	public int AddtreatmentCategory(TreatmentMasterModel teatmentModel) {
		String sqlQuery = "INSERT INTO treatment_category (code,group_id,name) "
				+ "values (?,?,?)";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery); 
			pStmt.setString(1, teatmentModel.getTreatCategory_code());
			pStmt.setString(2, teatmentModel.getTreatCategory_groupid());
			pStmt.setString(3, teatmentModel.getTreatCategory_name());
			rowsupdate = pStmt.executeUpdate();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowsupdate;
	}
	
	public Boolean DeletetreatmentCategory(String id) {
		String sqlQuery = "delete from treatment_category where id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, id);
			int rowsupdate = pStmt.executeUpdate();
			

			if (rowsupdate > 0)
				delete_success = true;
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return delete_success;
	}
	public int UpdatetreatmentCategory(TreatmentMasterModel teatmentModel) {
		String sqlQuery = "update treatment_category set code = ? , name = ? , group_id = ?"
				+ "where id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, teatmentModel.getTreatCategory_code());
			pStmt.setString(2, teatmentModel.getTreatCategory_name());
			pStmt.setString(3, teatmentModel.getTreatCategory_groupid());
			pStmt.setString(4, teatmentModel.getTreatCategory_id());
			
			rowsupdate = pStmt.executeUpdate();

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

		return rowsupdate;
	}

}
