package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.mysql.jdbc.PreparedStatement;
import com.smict.person.model.TreatmentRoomModel;

import ldc.util.DBConnect;

public class TreatmentRoomData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement Pstmt = null;
	ResultSet rs = null;
	
	public List<TreatmentRoomModel> findRoomByBranchCode(String branch_code){

		List<TreatmentRoomModel> treatRoomList = new ArrayList<TreatmentRoomModel>();
		if(!branch_code.isEmpty()){
			String SQL = "SELECT "
					+ "r.room_id, "
					+ "r.room_name, "
					+ "r.room_branch_code "
					+ "FROM room_id r "
					+ "WHERE r.room_branch_code = '" + branch_code + "' ";
			System.out.println(SQL);
			
			try {
				conn = agent.getConnectMYSql();
				Stmt = conn.createStatement();
				rs = Stmt.executeQuery(SQL);
				while(rs.next()){
					treatRoomList.add(new TreatmentRoomModel(
						rs.getInt("room_id"),
						rs.getString("room_name"),
						rs.getString("room_branch_code")
					));
				}	
				
				if(!conn.isClosed()) conn.close();
				if(!Stmt.isClosed()) Stmt.close();
				if(!rs.isClosed()) rs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		return treatRoomList;		
	}
	
}
