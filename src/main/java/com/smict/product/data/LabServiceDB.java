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

public class LabServiceDB {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
 
	
	public int Addlabservice(String lab_id, String service_id, String est_fee) {
		String sqlQuery = "insert into lab_service (lab_id,service_id,est_fee) "
				+ "values (?,?,?)";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, lab_id);
			pStmt.setString(2, service_id);
			pStmt.setString(3, est_fee);
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

	public Boolean Deletelabservice(String lab_id,String service_id) {
		String sqlQuery = "delete from lab_service where lab_id = ? and service_id=?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, lab_id);
			pStmt.setString(2, service_id);
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

	public int Updatelab(String lab_id, String service_id, String est_fee, String lab_id_hd, String service_id_hd) {
		String sqlQuery = "update lab_service set lab_id = ? , service_id = ? , est_fee = ? "
				+ "where lab_id = ? and service_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, lab_id);
			pStmt.setString(2, service_id); 
			pStmt.setString(3, est_fee);
			pStmt.setString(4, lab_id_hd); 
			pStmt.setString(5, service_id_hd);
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

	public List<LabServiceModel> Get_LabServiceList(String lab_id, String service_id) throws IOException, Exception {
		String sqlQuery = "select * from lab_service a "
				+ "left join lab_allservice b on(b.service_id = a.service_id) "
				+ "left join lab c on(c.lab_id = a.lab_id) " 
				+ "where ";

		if (new Validate().Check_String_notnull_notempty(lab_id))
			sqlQuery += "a.lab_id = '" + lab_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(service_id))
			sqlQuery += "a.service_id = '" + service_id + "' and ";

		sqlQuery += "a.lab_id <> '' and a.service_id <> '' order by a.lab_id,a.service_id ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<LabServiceModel> ResultList = new ArrayList<LabServiceModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new LabServiceModel(rs.getString("lab_id"), rs.getString("lab_name"), rs.getString("service_id"), 
					rs.getString("service_name"), rs.getString("est_fee") )) ;
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
