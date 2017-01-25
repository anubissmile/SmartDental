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

public class ProducttypeDB {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
public String GetHighest_ProducttypeID() throws IOException, Exception{
		
		String sqlQuery = "select MAX(producttype_id) as producttype_id from pro_producttype";
		String ResultString = "";
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		if(rs.next()){
			ResultString = rs.getString("producttype_id");
		}
		
		
		
		return ResultString;
	}
	
	public String PlusOneID_FormatID(String producttype_id){
		if(producttype_id == null){
			
			producttype_id = "0001";
			
		}else{
			
			String ResultString_plusone = String.valueOf((Integer.parseInt(producttype_id)+1));
			switch (ResultString_plusone.length()) {
			case 1:producttype_id="000"+ResultString_plusone; break;
			case 2:producttype_id="00"+ResultString_plusone; break;
			case 3:producttype_id="0"+ResultString_plusone; break;
			case 4:producttype_id=ResultString_plusone; break;
			}
		}
		
		return producttype_id;
	}
	
	public int Addtype(String producttype_id, String producttype_name, String create_by) {
		String sqlQuery = "insert into pro_producttype (producttype_id,producttype_name,create_by,create_datetime) "
				+ "values (?,?,?,now())";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, producttype_id);
			pStmt.setString(2, producttype_name);
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

	public Boolean Deletetype(String producttype_id) {
		String sqlQuery = "delete from pro_producttype where producttype_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, producttype_id);
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

	public int Updatetype(String producttype_id, String producttype_name, String update_by) {
		String sqlQuery = "update pro_producttype set producttype_id = ? , producttype_name = ? , update_by = ? , update_datetime = now() "
				+ "where producttype_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, producttype_id);
			pStmt.setString(2, producttype_name);
			pStmt.setString(3, update_by);
			pStmt.setString(4, producttype_id);
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

	public List<ProducttypeModel> Get_typeList(String producttype_id, String producttype_name) throws IOException, Exception {
		String sqlQuery = "select * from pro_producttype where ";

		if (new Validate().Check_String_notnull_notempty(producttype_id))
			sqlQuery += "producttype_id = '" + producttype_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(producttype_name))
			sqlQuery += "producttype_name like '%" + producttype_name + "%' and ";

		sqlQuery += "producttype_id != '' ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<ProducttypeModel> ResultList = new ArrayList<ProducttypeModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new ProducttypeModel(rs.getString("producttype_id"), rs.getString("producttype_name"),
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
