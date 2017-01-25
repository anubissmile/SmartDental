package com.smict.product.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.smict.product.model.ProductModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class ProductData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	public List<JSONObject> getProduct_Profile(ProductModel product_Model){
		
		Validate cValidate = new Validate();
		String product_name =  product_Model.getProduct_name(), product_name_en = product_Model.getProduct_name_en(),
		productgroup_id = product_Model.getProductgroup_id(), productgroup_name = product_Model.getProductgroup_name(), 
		producttype_Id = product_Model.getProducttype_Id(), producttype_name = product_Model.getProducttype_name(),
		productbrand_id = product_Model.getProductbrand_id(), productbrand_name = product_Model.getProductbrand_name(), 
		productunit_id = product_Model.getProductunit_id(), productunit_name = product_Model.getProductunit_name();
		
		int product_id = 0;
		
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
				+ "where ";
		
		if(cValidate.checkIntegerNotZero(product_id)) sql += "product_id = "+product_id+ " and ";
		
		if(cValidate.Check_String_notnull_notempty(product_name)) sql += "product_name like '%"+product_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(product_name_en)) sql += "product_name_en like '%"+product_name_en+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(productgroup_id)) sql += "productgroup_id = '"+productgroup_id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(productgroup_name)) sql += "productgroup_name like '%"+productgroup_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(producttype_Id)) sql += "producttype_Id = '"+producttype_Id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(producttype_name)) sql += "producttype_name like '%"+producttype_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(productbrand_id)) sql += "productbrand_id = '"+productbrand_id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(productbrand_name)) sql += "productbrand_name like '%"+productbrand_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(productunit_id)) sql += "productunit_id = '"+productunit_id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(productunit_name)) sql += "productunit_name like '%"+productunit_name+"%' and ";
		
		sql += "product_id > 0 ";
		
		List<JSONObject> productList = new LinkedList<JSONObject>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("product_id", rs.getString("product_id"));
				jsonobj.put("product_name", rs.getString("product_name"));
				jsonobj.put("product_name_en", rs.getString("product_name_en"));
				jsonobj.put("productgroup_id", rs.getString("productgroup_id"));
				jsonobj.put("productgroup_name", rs.getString("productgroup_name"));
				jsonobj.put("producttype_Id", rs.getString("producttype_Id"));
				jsonobj.put("producttype_name", rs.getString("producttype_name"));
				jsonobj.put("productbrand_id", rs.getString("productbrand_id"));
				jsonobj.put("productbrand_name", rs.getString("productbrand_name"));
				jsonobj.put("productunit_id", rs.getString("productunit_id"));
				jsonobj.put("productunit_name", rs.getString("productunit_name"));
				jsonobj.put("product_id", rs.getString("product_id"));
				
				productList.add(jsonobj);
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
	
	public List<ProductModel> getListProductModel(ProductModel product_Model){
		
		Validate cValidate = new Validate();
		String product_name =  product_Model.getProduct_name(), product_name_en = product_Model.getProduct_name_en(),
		productgroup_id = product_Model.getProductgroup_id(), productgroup_name = product_Model.getProductgroup_name(), 
		producttype_Id = product_Model.getProducttype_Id(), producttype_name = product_Model.getProducttype_name(),
		productbrand_id = product_Model.getProductbrand_id(), productbrand_name = product_Model.getProductbrand_name(), 
		productunit_id = product_Model.getProductunit_id(), productunit_name = product_Model.getProductunit_name();
		
		int product_id = 0;
		
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
				+ "where ";
		
		if(cValidate.checkIntegerNotZero(product_id)) sql += "product_id = "+product_id+ " and ";
		
		if(cValidate.Check_String_notnull_notempty(product_name)) sql += "product_name like '%"+product_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(product_name_en)) sql += "product_name_en like '%"+product_name_en+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(productgroup_id)) sql += "productgroup_id = '"+productgroup_id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(productgroup_name)) sql += "productgroup_name like '%"+productgroup_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(producttype_Id)) sql += "producttype_Id = '"+producttype_Id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(producttype_name)) sql += "producttype_name like '%"+producttype_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(productbrand_id)) sql += "productbrand_id = '"+productbrand_id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(productbrand_name)) sql += "productbrand_name like '%"+productbrand_name+"%' and ";
		
		if(cValidate.Check_String_notnull_notempty(productunit_id)) sql += "productunit_id = '"+productunit_id+"' and ";
		
		if(cValidate.Check_String_notnull_notempty(productunit_name)) sql += "productunit_name like '%"+productunit_name+"%' and ";
		
		sql += "product_id > 0 ";
		
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
