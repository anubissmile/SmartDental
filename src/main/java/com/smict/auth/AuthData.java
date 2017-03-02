package com.smict.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import ldc.util.DBConnect;

public class AuthData {
	
	private DBConnect agent = new DBConnect();
	private Connection conn = null;
	private Statement Stmt = null;
	private ResultSet rs = null;
	
	public HashMap<String, AuthModel> attempt(String usr, String pwd){
		
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			String sql = "SELECT employee.emp_username, "
					+ "employee.emp_id, "
					+ "employee.pre_name_id, "
					+ "employee.first_name_th, "
					+ "employee.last_name_th, "
					+ "employee.first_name_en, "
					+ "employee.last_name_en, "
					+ "employee.branch_id, "
					+ "pre_name.pre_name_id, "
					+ "pre_name.pre_name_th, "
					+ "pre_name.pre_name_en "
					+ "FROM pre_name INNER JOIN employee ON employee.pre_name_id = pre_name.pre_name_id "
					+ "WHERE emp_username = '" + usr + "' AND emp_password = '" + pwd + "' ";
			
			rs = Stmt.executeQuery(sql);
			System.out.println(sql);

			HashMap<String, AuthModel> hmrs = new HashMap<String, AuthModel>();
			
			while(rs.next()){
				AuthModel authModel = new AuthModel();
				authModel.setEmpPWD(rs.getString("emp_id"));
				authModel.setPrefixName(rs.getString("pre_name_th"));
				authModel.setfNameEN(rs.getString("first_name_en"));
				authModel.setlNameEN(rs.getString("last_name_en"));
				authModel.setfNameTH(rs.getString("first_name_th"));
				authModel.setlNameTH(rs.getString("last_name_th"));
				authModel.setBranchID(rs.getString("branch_id"));
				hmrs.put(rs.getString("emp_id"), authModel);
			}
			
			if(!conn.isClosed()) conn.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!rs.isClosed()) rs.close();
			
			return hmrs;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
