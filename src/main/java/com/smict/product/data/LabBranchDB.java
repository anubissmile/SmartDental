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

public class LabBranchDB {
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
	
	public int Addlab(String lab_name, String addr_id, String branch_id) {
		String sqlQuery = "insert into lab_branch (lab_id,addr_id,branch_id) "
				+ "values (?,?,?)";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
		//	pStmt.setString(1, lab_id);
			pStmt.setString(1, lab_name);
			pStmt.setString(2, addr_id);
			pStmt.setString(3, branch_id);
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

	public Boolean Deletelab(String lab_id) {
		String sqlQuery = "delete from lab where lab_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, lab_id);
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

	public int Updatelab(String lab_id, String addr_id, String branch_id, String lab_idhd, String branch_idhd) {
		String sqlQuery = "update lab_branch set lab_id = ?, addr_id = ? , branch_id = ? "
				+ "where lab_id = ? and branch_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, lab_id);
			pStmt.setString(2, addr_id); 
			pStmt.setString(3, branch_id);
			pStmt.setString(4, lab_idhd);
			pStmt.setString(5, branch_idhd);
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

	public List<LabBranchModel> Get_LabBranchList(String lab_id, String lab_name, String branch_id) throws IOException, Exception {
		String sqlQuery = "select * from lab_branch left join lab on(lab.lab_id = lab_branch.lab_id) "
				+ "left join branch on(lab_branch.branch_id = branch.branch_id) where ";

		if (new Validate().Check_String_notnull_notempty(lab_id))
			sqlQuery += "lab_branch.lab_id = '" + lab_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(lab_name))
			sqlQuery += "lab.lab_name like '%" + lab_name + "%' and ";

		if (new Validate().Check_String_notnull_notempty(branch_id))
			sqlQuery += "lab_branch.branch_id = '" + branch_id + "' and ";
		
		sqlQuery += "lab_branch.lab_id <> '' ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<LabBranchModel> ResultList = new ArrayList<LabBranchModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new LabBranchModel(rs.getString("lab_id"), rs.getString("lab_name"), rs.getString("addr_id"), rs.getString("branch_id"), rs.getString("branch_name")));
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
