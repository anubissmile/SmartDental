package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.person.model.DoctorModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class WorkHistoryData 
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	
	public int add_multi_work(List<DoctorModel> doctorModel){
		int work_history_id = 0;
		String sql = "select max(work_history_id)+1 as work_history_id from employee_work_history";
		try {
			conn = agent.getConnectMYSql();
		//	conn.setAutoCommit(false);
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				work_history_id = rs.getInt("work_history_id");
			}
			sql = "INSERT INTO employee_work_history (work_history_id,location,position,start_date,end_date,remark,work_type,salary) VALUES ";
			int i = 0;
			for (DoctorModel docMo : doctorModel) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+work_history_id+",'"+docMo.getLocation()+"','"+docMo.getPosition()+"','"+docMo.getStart_date()+"',"
						+ "'"+docMo.getEnd_date()+"','"+docMo.getRemark_message()+"','"+docMo.getWork_type()+"','"+docMo.getSalary()+"')";				
			}
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			work_history_id = 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			work_history_id = 0;
		}
		
		return work_history_id;
	}
	public int add_multi_work(List<DoctorModel> doctorModel,int work_history_id){
		
		try {
	
			String sql = "INSERT INTO employee_work_history (work_history_id,location,position,start_date,end_date,remark,work_type,salary) VALUES ";
			int i = 0;
			for (DoctorModel docMo : doctorModel) {
				
				if(i>0){
					sql += ",";
				}
				sql +="("+work_history_id+",'"+docMo.getLocation()+"','"+docMo.getPosition()+"','"+docMo.getStart_date()+"',"
						+ "'"+docMo.getEnd_date()+"','"+docMo.getRemark_message()+"','"+docMo.getWork_type()+"','"+docMo.getSalary()+"')";	
				i++;
			}
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return work_history_id;
	}
	public void del_multi_work(int work_history_id){
		try {
			conn = agent.getConnectMYSql();
			String sql = "DELETE FROM employee_work_history WHERE work_history_id="+work_history_id;
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	
	public List<DoctorModel> getMultiWork(int work_history_id){
		
		List<DoctorModel> WorkList = new ArrayList<DoctorModel>();
		String sql = "SELECT * FROM employee_work_history WHERE work_history_id = ?";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,work_history_id);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				DoctorModel docModel = new DoctorModel();
				docModel.setLocation(rs.getString("location"));
				docModel.setPosition(rs.getString("position"));
				docModel.setRemark_message(rs.getString("remark"));
				docModel.setWork_type(rs.getString("work_type"));
				docModel.setSalary(rs.getString("salary"));
				
				String sd  = rs.getString("start_date");
				String ed = rs.getString("end_date");
				
				if(new Validate().Check_String_notnull_notempty(sd)){
					String[] parts = sd.split("-");
					sd = parts[2]+"-"+parts[1]+"-"+parts[0];
					docModel.setBirth_date(sd);
				}
				if(new Validate().Check_String_notnull_notempty(ed)){
					String[] parts = ed.split("-");
					ed = parts[2]+"-"+parts[1]+"-"+parts[0];
					docModel.setBirth_date(ed);
				}
				docModel.setStart_date(sd);
				docModel.setEnd_date(ed);
				
				
				
				WorkList.add(docModel);
			}
			if (!rs.isClosed())
				rs.close();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return WorkList;
	}
	
	
}
