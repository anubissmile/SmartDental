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

public class ProductgroupDB {
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
	
	public int Addgroup(String productgroup_id, String productgroup_name, String create_by) {
		String sqlQuery = "insert into pro_productgroup (productgroup_id,productgroup_name,create_by,create_datetime) "
				+ "values (?,?,?,now())";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productgroup_id);
			pStmt.setString(2, productgroup_name);
			pStmt.setString(3, create_by);
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

	public Boolean Deletegroup(String productgroup_id) {
		String sqlQuery = "delete from pro_productgroup where productgroup_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productgroup_id);
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

	public int Updategroup(String productgroup_id, String productgroup_name, String update_by) {
		String sqlQuery = "update pro_productgroup set productgroup_id = ? , productgroup_name = ? , update_by = ? , update_datetime = now() "
				+ "where productgroup_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productgroup_id);
			pStmt.setString(2, productgroup_name);
			pStmt.setString(3, update_by);
			pStmt.setString(4, productgroup_id);
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

	public List<ProductgroupModel> Get_groupList(String productgroup_id, String productgroup_name) throws IOException, Exception {
		String sqlQuery = "select * from pro_productgroup where ";

		if (new Validate().Check_String_notnull_notempty(productgroup_id))
			sqlQuery += "productgroup_id = '" + productgroup_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(productgroup_name))
			sqlQuery += "productgroup_name like '%" + productgroup_name + "%' and ";

		sqlQuery += "productgroup_id != '' ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<ProductgroupModel> ResultList = new ArrayList<ProductgroupModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new ProductgroupModel(rs.getString("productgroup_id"), rs.getString("productgroup_name"),
					rs.getString("create_by"), rs.getString("create_datetime"), rs.getString("update_by"),
					rs.getString("update_datetime")));
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
