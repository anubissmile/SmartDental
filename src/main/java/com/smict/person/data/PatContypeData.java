package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smict.all.model.ContypeModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class PatContypeData {
	DBConnect agent = new DBConnect();
	DateUtil dateUtil = new DateUtil();

	public boolean addPatContype(String patHn, int subContractId){
		Connection conn = null;
		Statement Stmt = null;
		DateUtil aUtils = new DateUtil();
		Date createDate = new Date();
		Date expireDate = aUtils.getDateAfterPlusYears(2);
		SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(aSimpleDateFormat.format(createDate));
		System.out.println(aSimpleDateFormat.format(expireDate));
		
		String sql = "insert into patient_contype (hn, sub_contact_id, create_datetime, expire_datetime) "
						+ "values "
					+ "('"+patHn+"','"+subContractId+"','"+aSimpleDateFormat.format(createDate)+"','"+aSimpleDateFormat.format(expireDate)+"') ";
		boolean hasAddContype = false;
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				hasAddContype = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hasAddContype;
		
	}
	
	public boolean updateStatusPatContype(int subContractId,String status){
		Connection conn = null;
		Statement Stmt = null;
		
		String sql = "UPDATE  patient_contype "
				+ "SET ";
				if(status.equals("1")){
					sql+="status = 0 ";
				}else{
					sql+= "status = 1 ";
				}				
				sql+= "where  patient_contypeid = "+subContractId;
		boolean hasAddContype = false;
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				hasAddContype = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hasAddContype;
		
	}
	
	public List<ContypeModel> getListContype(String patHn, int subContactId){
		Connection conn = null;
		Statement Stmt = null;
		Validate aValidate = new Validate();
		String sql = "select patCon.patient_contypeid, patCon.hn, patCon.create_datetime, patCon.expire_datetime,"
				+ "proSub.sub_contact_id, proSub.sub_contact_name, proSub.sms_piority, proSub.contact_id,"
				+ "proCon.contact_id, proCon.contact_name,patCon.status "
				+ "from patient_contype patCon "
				+ "INNER JOIN promotion_sub_contact proSub on (patCon.sub_contact_id = proSub.sub_contact_id) "
				+ "INNER JOIN promotion_contact proCon on (proSub.contact_id = proCon.contact_id ) "
				+ "where ";
			 
		if(aValidate.Check_String_notnull_notempty(patHn)) 
			sql += "patCon.hn = '"+patHn+"' and ";
		
/*		if(aValidate.checkIntegerNotZero(subContactId)) 
			sql += "patCon.sub_contact_id = "+subContactId+" and ";*/
 
			sql += "patCon.patient_contypeid != 0  ";
	/*				+ "proSub.status = 1 ";*/
			
		List<ContypeModel> listContype = new ArrayList<ContypeModel>();
		
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				ContypeModel aContypeModel = new ContypeModel();
				aContypeModel.setPatient_contypeid(rs.getInt("patient_contypeid"));
				aContypeModel.setHn(rs.getString("hn"));
				aContypeModel.setSub_contact_id(rs.getInt("sub_contact_id"));
				aContypeModel.setSub_contact_name(rs.getString("sub_contact_name"));
				aContypeModel.setContact_id(rs.getInt("contact_id"));
				aContypeModel.setContact_name(rs.getString("contact_name"));
				aContypeModel.setCreate_datetime(rs.getDate("create_datetime"));
				aContypeModel.setExpire_datetime(rs.getDate("expire_datetime"));
				aContypeModel.setPat_con_status(rs.getString("patCon.status"));
				aContypeModel.setDayOutBalance(dateUtil.calDayOutBalance(new Date(), aContypeModel.getExpire_datetime()));
				listContype.add(aContypeModel);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return listContype;
	}
	
	public boolean renewalMember(ContypeModel patContypeModel){
		
		Connection conn = null;
		Statement Stmt = null;
		ResultSet rs = null;
		
		String sql = "update patient_contype set expire_datetime = DATE_ADD(expire_datetime,INTERVAL "+patContypeModel.getRenewalYear()+" YEAR) "
				+ "where patient_contypeid = "+patContypeModel.getPatient_contypeid();
		
		boolean renewalStatus = false;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				
				renewalStatus = true;
				
			}
			
			if(Stmt.isClosed()) Stmt.close();
			if(conn.isClosed()) conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return renewalStatus;
	}
}
