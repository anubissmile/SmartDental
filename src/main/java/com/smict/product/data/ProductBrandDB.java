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

public class ProductBrandDB {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
public String GetHighest_productbrand_id() throws IOException, Exception{
		
		String sqlQuery = "select MAX(productbrand_id) as productbrand_id from pro_productbrand";
		String ResultString = "";
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		if(rs.next()){
			ResultString = rs.getString("productbrand_id");
		}
		
		
		
		return ResultString;
	}
	
	public String PlusOneID_FormatID(String productbrand_id){
		if(productbrand_id == null){
			
			productbrand_id = "0001";
			
		}else{
			
			String ResultString_plusone = String.valueOf((Integer.parseInt(productbrand_id)+1));
			switch (ResultString_plusone.length()) {
			case 1:productbrand_id="000"+ResultString_plusone; break;
			case 2:productbrand_id="00"+ResultString_plusone; break;
			case 3:productbrand_id="0"+ResultString_plusone; break;
			case 4:productbrand_id=ResultString_plusone; break;
			}
		}
		
		return productbrand_id;
	}
	
	public int AddBrand(String productbrand_id, String productbrand_name, String create_by) {
		String sqlQuery = "insert into pro_productbrand (productbrand_id,productbrand_name,create_by,create_datetime) "
				+ "values (?,?,?,now())";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productbrand_id);
			pStmt.setString(2, productbrand_name);
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

	public Boolean DeleteBrand(String productbrand_id) {
		String sqlQuery = "delete from pro_productbrand where productbrand_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productbrand_id);
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

	public int UpdateBrand(String productbrand_id, String productbrand_name, String update_by) {
		String sqlQuery = "update pro_productbrand set productbrand_id = ? , productbrand_name = ? , update_by = ? , update_datetime = now() "
				+ "where productbrand_id = ?";
		int rowsupdate = 0;
		try {

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, productbrand_id);
			pStmt.setString(2, productbrand_name);
			pStmt.setString(3, update_by);
			pStmt.setString(4, productbrand_id);
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

	public List<ProductBrandModel> Get_BrandList(String productbrand_id, String productbrand_name) throws IOException, Exception {
		String sqlQuery = "select * from pro_productbrand where ";

		if (new Validate().Check_String_notnull_notempty(productbrand_id))
			sqlQuery += "productbrand_id = '" + productbrand_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(productbrand_name))
			sqlQuery += "productbrand_name like '%" + productbrand_name + "%' and ";

		sqlQuery += "productbrand_id != '' ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<ProductBrandModel> ResultList = new ArrayList<ProductBrandModel>();
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.add(new ProductBrandModel(rs.getString("productbrand_id"), rs.getString("productbrand_name"),
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
