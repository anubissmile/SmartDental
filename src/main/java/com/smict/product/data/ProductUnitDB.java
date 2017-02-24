package com.smict.product.data;

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

import com.smict.product.model.*;

import ldc.util.*;

public class ProductUnitDB {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
public String GetHighest_ProductUnitID() throws IOException, Exception{
		
		String sqlQuery = "select MAX(productunit_id) as productunit_id from pro_productunit";
		String ResultString = "";
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		if(rs.next()){
			ResultString = rs.getString("productunit_id");
		}
		
		return ResultString;
	}
	
	public String PlusOneID_FormatID(String productunit_id){
		if(productunit_id == null){
			
			productunit_id = "0001";
			
		}else{
			
			String ResultString_plusone = String.valueOf((Integer.parseInt(productunit_id)+1));
			switch (ResultString_plusone.length()) {
			case 1:productunit_id="000"+ResultString_plusone; break;
			case 2:productunit_id="00"+ResultString_plusone; break;
			case 3:productunit_id="0"+ResultString_plusone; break;
			case 4:productunit_id=ResultString_plusone; break;
			}
		}
		
		return productunit_id;
	}
	
	public int AddUnit(String productunit_id, String productunit_name, String create_by) {
		String sqlQuery = "insert into pro_productunit (productunit_id,productunit_name,create_by,create_datetime) "
				+ "values (?,?,?,now())";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productunit_id);
			pStmt.setString(2, productunit_name);
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

	public Boolean DeleteUnit(String productunit_id) {
		String sqlQuery = "delete from pro_productunit where productunit_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productunit_id);
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

	public int UpdateUnit(String productunit_id, String productunit_name, String update_by) {
		String sqlQuery = "update pro_productunit set productunit_id = ? , productunit_name = ? , update_by = ? , update_datetime = now() "
				+ "where productunit_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productunit_id);
			pStmt.setString(2, productunit_name);
			pStmt.setString(3, update_by);
			pStmt.setString(4, productunit_id);
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

	public List<ProductUnitModel> Get_UnitList(String productunit_id, String productunit_name) throws IOException, Exception {
		String sqlQuery = "select * from pro_productunit where ";

		if (new Validate().Check_String_notnull_notempty(productunit_id))
			sqlQuery += "productunit_id = '" + productunit_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(productunit_name))
			sqlQuery += "productunit_name like '%" + productunit_name + "%' and ";

		sqlQuery += "productunit_id != '' ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<ProductUnitModel> ResultList = new ArrayList<ProductUnitModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new ProductUnitModel(rs.getString("productunit_id"), rs.getString("productunit_name"),
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
	
	public Map<String,String> Get_unitList() throws IOException, Exception {
		String sqlQuery = "select * from pro_productunit";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		Map <String,String>ResultList = new HashMap<String,String>();
		
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.put(rs.getString("productunit_Id"), rs.getString("productunit_name"));
					
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
