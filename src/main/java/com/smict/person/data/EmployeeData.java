package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.smict.person.model.Person;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class EmployeeData {
	
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	/**
	 * Get dentist's assistant list.
	 * @author anubissmile
	 * @return List<Person>
	 */
	public List<Person> getAssistantList(){
		String SQL = "SELECT employee.emp_username, "
				+ "employee.emp_id, "
				+ "employee.pre_name_id, "
				+ "employee.first_name_th, "
				+ "employee.last_name_th, "
				+ "employee.first_name_en, "
				+ "employee.last_name_en, "
				+ "employee.birth_date, "
				+ "employee.identification, "
				+ "employee.identification_type, "
				+ "employee.addr_id, "
				+ "employee.family_id, "
				+ "employee.branch_id, "
				+ "employee.hired_date, "
				+ "employee.remark, "
				+ "employee.profile_pic, "
				+ "employee.work_status, "
				+ "employee.is_asistant, "
				+ "employee.tel_id "
				+ "FROM employee "
				+ "WHERE employee.work_status = '1' AND employee.is_asistant = '1'  AND employee.branch_id = '" + Auth.user().getBranchID() + "' ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size()>0){
			rs = agent.getRs();
			try {
				List<Person> personList = new ArrayList<Person>();
				while(rs.next()){
					Person personModel = new Person();
					personModel.setEmpuser(rs.getString("emp_username"));
					personModel.setEmp_id(rs.getString("emp_id"));
					personModel.setPre_name_id(rs.getString("pre_name_id"));
					personModel.setFirstname_th(rs.getString("first_name_th"));
					personModel.setLastname_th(rs.getString("last_name_th"));
					personModel.setFirstname_en(rs.getString("first_name_en"));
					personModel.setLastname_en(rs.getString("last_name_en"));
					personModel.setBirth_date(rs.getString("birth_date"));
					personModel.setIdentification(rs.getString("identification"));
					personModel.setIdentification_type(rs.getString("identification_type"));
					personModel.setAddr_id(rs.getInt("addr_id"));
					personModel.setFam_id(rs.getInt("family_id"));
					personModel.setBranch_id(rs.getString("branch_id"));
					personModel.setHired_date(rs.getString("hired_date"));
					personModel.setRemark(rs.getString("remark"));
					personModel.setWork_status(rs.getString("work_status"));
					personModel.setIs_asistant(rs.getString("is_asistant"));
					personModel.setTel_id(rs.getInt("tel_id"));
					personList.add(personModel);
				}
				return personList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		
		return null;
	}
	
	public boolean addemployeeinsert(Person employeemodel) throws IOException, Exception{
		
		String SQL = "INSERT into employee (emp_username,emp_password,emp_id,pre_name_id,first_name_th,last_name_th,"
				+ "first_name_en,last_name_en,birth_date,identification,identification_type,addr_id,family_id,branch_id,hired_date,"
				+ "remark,profile_pic,work_status,is_asistant,tel_id, line_id, email) "
				+ "Value "
				+ "('"+employeemodel.getEmpuser()
				+ "','"+employeemodel.getEmppassword()
				+ "','"+employeemodel.getEmp_id()
				+ "','"+employeemodel.getPre_name_id()
				+ "','"+employeemodel.getFirstname_th()
				+ "','"+employeemodel.getLastname_th()
				+ "','"+employeemodel.getFirstname_en()
				+ "','"+employeemodel.getLastname_en()
				+ "','"+employeemodel.getBirth_date()
				+ "','"+employeemodel.getIdentification()
				+ "','"+employeemodel.getIdentification_type()
				+ "',"+employeemodel.getAddr_id()
				+ ","+employeemodel.getFam_id()
				+ ",'"+employeemodel.getBranch_id()
				+ "','"+employeemodel.getHired_date()
				+ "','"+employeemodel.getRemark()
				+ "','"+employeemodel.getProfile_pic()
				+ "','1'"
				+ ",'"+employeemodel.getIs_asistant()
				+ "', '"+employeemodel.getTel_id() + "' " 
				+ ", '"+employeemodel.getLineId() + "' " 
				+ ", '"+employeemodel.getEmail() + "' " 
				+ ") ";
		
		System.out.println("EmployeeData.addemployeeinsert SQL : " + SQL);
		
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
		
		if(sStmt>0){
			return true;
		}
	
			return false;
	
	}
	public Map<String,String> Get_branchList() throws IOException, Exception {
		String sqlQuery = "select branch_id,branch_name from branch";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		Map <String,String>ResultList = new HashMap<String,String>();
		
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.put(rs.getString("branch_id"), rs.getString("branch_name"));
					
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	
	public List<Person> getListemployee(){
		
		String sql = "SELECT "
				+ "employee.emp_username, pre_name.pre_name_th, emp_id, employee.first_name_th, "
				+ "branch.branch_name,employee.tel_id,employee.last_name_th, "
				+ "CASE employee.work_status WHEN '1' THEN 'Active' WHEN '0' THEN 'Inactive' END AS 'Status' "
				+ "FROM "
				+ "employee "
				+ "INNER JOIN branch ON branch.branch_id = employee.branch_id "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = employee.pre_name_id "
				+ "ORDER BY employee.work_status DESC";
			//	+ "INNER JOIN tel_telephone ON tel_telephone.tel_id = employee.tel_id ";
	
		List<Person> employeelist = new LinkedList<Person>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				Person employeemodel = new Person();
				
				employeemodel.setEmp_id(rs.getString("emp_id"));
				employeemodel.setEmpuser(rs.getString("emp_username"));
				employeemodel.setPre_name_th(rs.getString("pre_name_th"));
				employeemodel.setFirstname_th(rs.getString("first_name_th"));
				employeemodel.setLastname_th(rs.getString("last_name_th"));
				employeemodel.setBranch_id(rs.getString("branch_name"));
				employeemodel.setWork_status(rs.getString("Status"));
				employeemodel.setTel_id(rs.getInt("tel_id"));
				TelephoneData tellist = new TelephoneData();
				employeemodel.setListTelModel(tellist.get_telList(employeemodel.getTel_id()));
				
				employeelist.add(employeemodel);
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
		
		return employeelist;
		}
	public Person editemployee(String emp_id){
		DateUtil dateinto = new DateUtil();
		Person returnempmodel = new Person();
		
		String sql = "SELECT "
				+ "emp_username, emp_password, emp_id, pre_name_id, "
				+ "first_name_th, last_name_th, first_name_en, last_name_en, birth_date, "
				+ "identification, identification_type, addr_id, family_id, branch_id, "
				+ "hired_date, remark, profile_pic, work_status, is_asistant, tel_id, line_id, email "
				+ "FROM "
				+ "employee "
				+ "where emp_id = '" + emp_id + "' ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				returnempmodel.setEmpuser(rs.getString("emp_username"));
				returnempmodel.setEmppassword(rs.getString("emp_password"));
				returnempmodel.setEmp_id(rs.getString("emp_id"));
				returnempmodel.setPre_name_id(rs.getString("pre_name_id"));
				returnempmodel.setFirstname_th(rs.getString("first_name_th"));
				returnempmodel.setLastname_th(rs.getString("last_name_th"));
				returnempmodel.setFirstname_en(rs.getString("first_name_en"));
				returnempmodel.setLastname_en(rs.getString("last_name_en"));	
				returnempmodel.setBirth_date(rs.getString("birth_date"));
				returnempmodel.setIdentification(rs.getString("identification"));
				returnempmodel.setIdentification_type(rs.getString("identification_type"));
				returnempmodel.setAddr_id(rs.getInt("addr_id"));	
				returnempmodel.setFam_id(rs.getInt("family_id"));
				returnempmodel.setBranch_id(rs.getString("branch_id"));
				returnempmodel.setHired_date(dateinto.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.SSS", "dd-MM-yyyy", rs.getString("hired_date"), true));
				returnempmodel.setRemark(rs.getString("remark"));	
				returnempmodel.setProfile_pic(rs.getString("profile_pic"));
				returnempmodel.setWork_status(rs.getString("work_status"));
				returnempmodel.setIs_asistant(rs.getString("is_asistant"));
				returnempmodel.setTel_id(rs.getInt("tel_id"));	
				returnempmodel.setLineId(rs.getString("line_id"));
				returnempmodel.setEmail(rs.getString("email"));
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnempmodel;
	}
	public List<Person> getListemployeeSearch(String work, String branch){
		
		String sql = "SELECT "
				+ "employee.emp_username, pre_name.pre_name_th, emp_id, employee.first_name_th, "
				+ "branch.branch_name, "
				+ "CASE employee.work_status WHEN '1' THEN 'Active' WHEN '0' THEN 'Inactive' END AS 'Status' "
				+ "FROM "
				+ "employee "
				+ "INNER JOIN branch ON branch.branch_id = employee.branch_id "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = employee.pre_name_id ";

				if(branch != null){
					sql += "Where employee.branch_id = '"+branch+"' and employee.work_status = '"+work+"'";					
				}else{
					sql += "Where employee.work_status = '"+work+"'";
				}
		List<Person> employeelist = new LinkedList<Person>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				Person employeemodel = new Person();
				
				employeemodel.setEmp_id(rs.getString("emp_id"));
				employeemodel.setEmpuser(rs.getString("emp_username"));
				employeemodel.setPre_name_th(rs.getString("pre_name_th"));
				employeemodel.setFirstname_th(rs.getString("first_name_th"));
				employeemodel.setBranch_id(rs.getString("branch_name"));
				employeemodel.setWork_status(rs.getString("Status"));
				TelephoneData tellist = new TelephoneData();
				employeemodel.setListTelModel(tellist.get_telList(employeemodel.getTel_id()));
				
				employeelist.add(employeemodel);
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
		
		return employeelist;
		}
	public boolean empsearchupdate(Person empmodel) throws IOException, Exception{
		
		String SQL = "UPDATE employee SET ";
				if(empmodel.getWork_status().equals("1")){
				SQL+= "work_status = '0' ";			
				}
				else{
					SQL += "work_status = '1' ";
				}
				SQL+= " where emp_id = '"+empmodel.getEmp_id()+"'";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	public boolean empupdate(Person empmodel) throws IOException, Exception{
		
		String SQL = "UPDATE employee SET "
				+ "emp_username ='"+empmodel.getEmpuser()
				+ "', emp_password ='"+empmodel.getEmppassword()
				+ "', pre_name_id ='"+empmodel.getPre_name_id()
				+ "', first_name_th ='"+empmodel.getFirstname_th()
				+ "', last_name_th ='"+empmodel.getLastname_th()
				+ "', first_name_en ='"+empmodel.getFirstname_en()
				+ "', last_name_en ='"+empmodel.getLastname_en()
				+ "', birth_date ='"+empmodel.getBirth_date()
				+ "', identification='"+empmodel.getIdentification()
				+ "', identification_type='"+empmodel.getIdentification_type()
				+ "', addr_id="+empmodel.getAddr_id()
				+ ", family_id="+empmodel.getFam_id()
				+ ", branch_id='"+empmodel.getBranch_id()
				+ "', hired_date='"+empmodel.getHired_date()
				+ "', remark='"+empmodel.getRemark()
				+ "', profile_pic='"+empmodel.getProfile_pic()
				+ "', is_asistant='"+empmodel.getIs_asistant()
				+ "', tel_id = '"+empmodel.getTel_id() + "'"
				+ ", line_id = '"+empmodel.getLineId() + "'"
				+ ", email = '"+empmodel.getEmail() + "'"
				+ " where emp_id = '"+empmodel.getEmp_id() + "'";

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}


}
