package com.smict.employee.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.smict.product.model.ProductModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class EmployeeData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
public List<ProductModel> getListEmployee(){
		
		String sql = "SELECT "
				+ "a.product_id, a.product_name, a.product_name_en, a.price, "
				+ "c.productgroup_id, c.productgroup_name, d.producttype_Id, d.producttype_name, "
				+ "b.productbrand_id, b.productbrand_name, e.productunit_id, e.productunit_name "
				+ "FROM "
				+ "pro_product AS a "
				+ "INNER JOIN pro_productbrand AS b ON b.productbrand_id = a.productbrand_id "
				+ "INNER JOIN pro_productgroup AS c ON c.productgroup_id = a.productgroup_id "
				+ "INNER JOIN pro_producttype AS d ON d.producttype_Id = a.producttype_id "
				+ "INNER JOIN pro_productunit AS e ON e.productunit_id = a.productunit_id "
				+ "WHERE d.producttype_name LIKE 'สินค้า'";
				
		List<ProductModel> productList = new LinkedList<ProductModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				ProductModel proModel = new ProductModel();
				
				proModel.setProduct_id(rs.getInt("product_id"));
				proModel.setProduct_name(rs.getString("product_name"));
				proModel.setProduct_name_en(rs.getString("product_name_en"));
				proModel.setProductgroup_id(rs.getString("productgroup_id"));
				proModel.setProductgroup_name(rs.getString("productgroup_name"));
				proModel.setProducttype_Id(rs.getString("producttype_Id"));
				proModel.setProducttype_name(rs.getString("producttype_name"));
				proModel.setProductbrand_id(rs.getString("productbrand_id"));
				proModel.setProductbrand_name(rs.getString("productbrand_name"));
				proModel.setProductunit_id(rs.getString("productunit_id"));
				proModel.setProductunit_name(rs.getString("productunit_name"));
				proModel.setPrice(rs.getDouble("price"));
				
				productList.add(proModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return productList;
	}


}


