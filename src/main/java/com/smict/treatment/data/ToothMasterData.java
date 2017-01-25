package com.smict.treatment.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.smict.all.model.ToothModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class ToothMasterData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	public List<ToothModel> select_tooth_pic() {
		
		String sqlQuery = "SELECT tooth_pic_code,tooth_pic_name FROM tooth_pic";
	
		try {
			conn = agent.getConnectMYSql();
		
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List <ToothModel> resultList = new ArrayList<ToothModel>();
		
		while (rs.next()){   
			ToothModel toothModel = new ToothModel();
			toothModel.setTooth_pic_code(rs.getString("tooth_pic_code"));
			toothModel.setTooth_pic_name(rs.getString("tooth_pic_name"));
			resultList.add(toothModel);
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		return resultList;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ToothModel> select_tooth_list_arch(String arch ) {
		
		String sqlQuery = "SELECT tooth_id,tooth_num,arch,B,D,L,Li,La,M,O,P,i "
				+ "FROM tooth "
				+ "WHERE arch='"+arch+"' "
				+ "ORDER BY tooth_id";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
		
		List <ToothModel> resultList = new ArrayList<ToothModel>();
		
		while (rs.next()){   
			ToothModel toothModel = new ToothModel();
			
			toothModel.setTooth_id(rs.getInt("tooth_id"));
			toothModel.setTooth_num(rs.getInt("tooth_num"));
			toothModel.setArch(rs.getString("arch"));
			toothModel.setB(rs.getBoolean("B"));
			toothModel.setD(rs.getBoolean("D"));
			toothModel.setL(rs.getBoolean("L"));
			toothModel.setLi(rs.getBoolean("Li"));
			toothModel.setLa(rs.getBoolean("La"));
			toothModel.setM(rs.getBoolean("M"));
			toothModel.setO(rs.getBoolean("O"));
			toothModel.setP(rs.getBoolean("P"));
			toothModel.setI(rs.getBoolean("i"));
			
			resultList.add(toothModel);
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		return resultList;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<ToothModel> get_tooth_history(String HN){
		List <ToothModel> resultList = new ArrayList<ToothModel>();
		String sqlQuery = "SELECT ht.tooth,ht.surf,tm.tooth_pic_code "
				+ "FROM history_treatment ht "
				+ "INNER JOIN treatment_master tm ON ht.treatment_code = tm.treatment_code "
				+ "WHERE hn='"+HN+"' ";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			
			
		while (rs.next()){  
			
			String surf = rs.getString("surf");
			String toothNum = rs.getString("tooth");
			 
			List toothNum1 = new ArrayList<List>();
			
			if (toothNum.contains(",")) { 
				int j = 0;
				String[] parts = toothNum.split(",");
				for(String t :parts){
					toothNum1.add(t);
					List surface1 = new ArrayList<List>(); 
					if (surf.contains(",")) {
						String[] sur = surf.split(",");
						int i =0;
						for(String s :sur){
							surface1.add(s);
							ToothModel toothModel = new ToothModel();
							toothModel.setTooth_num(Integer.parseInt(toothNum1.get(j).toString()));
							toothModel.setSurface(surface1.get(i).toString());
							toothModel.setTooth_pic_code(rs.getString("tooth_pic_code"));
							resultList.add(toothModel);
							i++;
						}
					}else{
						ToothModel toothModel = new ToothModel();
						toothModel.setTooth_num(Integer.parseInt(toothNum1.get(j).toString()));
						toothModel.setSurface(surf);
						toothModel.setTooth_pic_code(rs.getString("tooth_pic_code"));
						resultList.add(toothModel);
					}
					j++;
				} 
			}else{ 
				int j = 0;
				String[] parts = toothNum.split(",");
				for(String t :parts){
					toothNum1.add(t);
					List surface1 = new ArrayList<List>(); 
					if (surf.contains(",")) {
						String[] sur = surf.split(",");
						int i =0;
						for(String s :sur){
							surface1.add(s); 
							ToothModel toothModel = new ToothModel();
							toothModel.setTooth_num(Integer.parseInt(toothNum1.get(j).toString()));
							toothModel.setSurface(surface1.get(i).toString());
							toothModel.setTooth_pic_code(rs.getString("tooth_pic_code"));
							resultList.add(toothModel);
							i++;
						}
					}else{
						ToothModel toothModel = new ToothModel();
						toothModel.setTooth_num(Integer.parseInt(toothNum1.get(j).toString()));
						toothModel.setSurface(surf);
						toothModel.setTooth_pic_code(rs.getString("tooth_pic_code"));
						resultList.add(toothModel);
					}
					j++;
				} 
			}
			
		
			
			
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		return resultList;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultList;
	}
}
