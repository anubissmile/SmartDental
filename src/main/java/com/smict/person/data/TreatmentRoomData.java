package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
	
	/**
	 * @author anubissmile
	 * (DataClass) add new treatment room function.
	 * @param branch_code
	 * @param room_name
	 * @return void return nothing. 
	 */
	public void addRoom(String branch_code, String room_name){
		
		String SQL = "INSERT INTO `room_id` (`room_name`, `room_branch_code`) "
					+ "VALUES ('" + room_name + "', '" + branch_code + "')";
		
		try {
			conn = agent.getConnectMYSql();
			Pstmt = conn.prepareStatement(SQL);
			Pstmt.executeUpdate();
			
			if(!conn.isClosed()) conn.close();
			if(!Pstmt.isClosed()) Pstmt.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
