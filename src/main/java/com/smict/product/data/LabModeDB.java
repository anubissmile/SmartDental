package com.smict.product.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.product.model.*;

import ldc.util.*;

public class LabModeDB {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
public String GetHighest_ProductgroupID() throws IOException, Exception{
		
		String sqlQuery = "select MAX(productgroup_id) as productgroup_id from pro_productgroup";
		String ResultString = "";
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		if(rs.next()){
			ResultString = rs.getString("productgroup_id");
		}
		
		
		
		return ResultString;
	}
	
	public String PlusOneID_FormatID(String productgroup_id){
		if(productgroup_id == null){
			
			productgroup_id = "0001";
			
		}else{
			
	
			
			String ResultString_plusone = String.valueOf((Integer.parseInt(productgroup_id)+1));
			switch (ResultString_plusone.length()) {
			case 1:productgroup_id="000"+ResultString_plusone; break;
			case 2:productgroup_id="00"+ResultString_plusone; break;
			case 3:productgroup_id="0"+ResultString_plusone; break;
			case 4:productgroup_id=ResultString_plusone; break;
			}
		}
		
		return productgroup_id;
	}
	
	public int Addlabmode(String labmode_id, String labmode_name) {
		String sqlQuery = "insert into lab_mode (labmode_id,labmode_name) "
				+ "values (?,?)";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery); 
			pStmt.setString(1, labmode_id);
			pStmt.setString(2, labmode_name);
			rowsupdate = pStmt.executeUpdate();
			conn.commit();

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

	public Boolean Deletelabmode(String labmode_id) {
		String sqlQuery = "delete from lab_mode where labmode_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, labmode_id);
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

	public int Updatelabmode(String labmode_id, String labmode_name, String hdlabmode_id) {
		String sqlQuery = "update lab_mode set labmode_id = ? , labmode_name = ? "
				+ "where labmode_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, labmode_id);
			pStmt.setString(2, labmode_name); 
			pStmt.setString(3, hdlabmode_id);
			rowsupdate = pStmt.executeUpdate();
			conn.commit();

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

	public List<LabModeModel> Get_LabModeList(String labmode_id, String labmode_name) throws IOException, Exception {
		String sqlQuery = "select * from lab_mode where ";

		if (new Validate().Check_String_notnull_notempty(labmode_id))
			sqlQuery += "labmode_id = '" + labmode_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(labmode_name))
			sqlQuery += "labmode_name like '%" + labmode_name + "%' and ";

		sqlQuery += "labmode_id <> '' ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<LabModeModel> ResultList = new ArrayList<LabModeModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new LabModeModel(rs.getString("labmode_id"), rs.getString("labmode_name")));
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
