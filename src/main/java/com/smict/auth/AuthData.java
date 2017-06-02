package com.smict.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class AuthData {
	
	private DBConnect agent = new DBConnect();
	private Connection conn = null;
	private Statement Stmt = null;
	private ResultSet rs = null;
	
	/**
	 * Fetch user credentials conditions by ident number.
	 * @author anubissmile
	 * @param String ident | Identificatino code
	 * @return List<AuthModel> | List of user credentials.
	 */
	public List<AuthModel> viewUserDetail(String ident){
		String SQL = "SELECT employee.emp_username AS username, employee.emp_id AS id, "
				+ "pre_name.pre_name_th AS pre_name, employee.first_name_th AS `name`, "
				+ "employee.last_name_th AS lastname, employee.birth_date AS birth, "
				+ "employee.identification AS ident, employee.profile_pic AS picture, "
				+ "employee.is_asistant AS is_assistant, user_role.role_name_th AS role, "
				+ "tel_telephone.tel_number AS phone, employee.remark AS remark, "
				+ "branch.branch_name AS branch, brand.brand_name AS brand, "
				+ "employee.hired_date AS hire, address.addr_no AS `no`, "
				+ "address.addr_bloc AS block, address.addr_village AS village, "
				+ "address.addr_alley AS alley, address.addr_road AS road, "
				+ "districts.DISTRICT_NAME AS district, zipcodes.ZIPCODE AS zipcode, "
				+ "amphures.AMPHUR_NAME AS city, provinces.PROVINCE_NAME AS province "
				+ "FROM employee "
				+ "LEFT JOIN pre_name ON employee.pre_name_id = pre_name.pre_name_id "
				+ "LEFT JOIN user_role ON employee.user_role = user_role.role_level "
				+ "LEFT JOIN tel_telephone ON employee.tel_id = tel_telephone.tel_id "
				+ "LEFT JOIN branch ON employee.branch_id = branch.branch_id "
				+ "LEFT JOIN brand ON branch.brand_id = brand.brand_id "
				+ "LEFT JOIN address ON employee.addr_id = address.addr_id "
				+ "LEFT JOIN districts ON address.addr_districtid = districts.DISTRICT_ID "
				+ "LEFT JOIN zipcodes ON districts.DISTRICT_ID = zipcodes.DISTRICT_ID "
				+ "LEFT JOIN amphures ON districts.AMPHUR_ID = amphures.AMPHUR_ID "
				+ "LEFT JOIN provinces ON amphures.PROVINCE_ID = provinces.PROVINCE_ID "
				+ "WHERE employee.identification = '" + ident + "' "
				+ "GROUP BY employee.identification ";
		
		List<AuthModel> authList = new ArrayList<AuthModel>();

		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					rs = agent.getRs();
					AuthModel authModel = new AuthModel();
					authModel.setEmpUsr(rs.getString("username"));
					authModel.setEmpId(rs.getString("id"));
					authModel.setPrefixName(rs.getString("pre_name"));
					authModel.setfNameTH(rs.getString("name"));
					authModel.setlNameTH(rs.getString("lastname"));
					authModel.setBirth(rs.getString("birth"));
					authModel.setIdentification(rs.getString("ident"));
					authModel.setPicture(rs.getString("picture"));
					authModel.setIsAssistant(rs.getInt("is_assistant"));
					authModel.setRoleNameTH(rs.getString("role"));
					authModel.setPhone(rs.getString("phone"));
					authModel.setRemark(rs.getString("remark"));
					authModel.setBranchName(rs.getString("branch"));
					authModel.setBrandName(rs.getString("brand"));
					authModel.setHireDate(rs.getString("hire"));
					
					/**
					 * ADDRESS.
					 */
					String no = rs.getString("no"),
						block = rs.getString("block"),
						village = rs.getString("village"),
						alley = rs.getString("alley"),
						road = rs.getString("road"),
						district = rs.getString("district"),
						city = rs.getString("city"),
						province = rs.getString("province"),
						zipcode = rs.getString("zipcode");
					String addr;
					Validate v = new Validate();
					addr = (v.Check_String_notnull_notempty(no)) ? "เลขที่  " + no  + " ": " ";
					addr += (v.Check_String_notnull_notempty(block)) ? "หมู่ " + block  + " ": " ";
					addr += (v.Check_String_notnull_notempty(village)) ? "หมู่บ้าน " + village  + " ": " ";
					addr += (v.Check_String_notnull_notempty(alley)) ? "ซอย " + alley  + " ": " ";
					addr += (v.Check_String_notnull_notempty(road)) ? "ถนน " + road  + " ": " ";
					addr += (v.Check_String_notnull_notempty(district)) ? "ตำบล " + district  + " ": " ";
					addr += (v.Check_String_notnull_notempty(city)) ? "อำเภอ " + city  + " ": " ";
					addr += (v.Check_String_notnull_notempty(province)) ? "จังหวัด " + province  + " " : " ";
					addr += (v.Check_String_notnull_notempty(zipcode)) ? "รหัสไปรษณีย์  " + zipcode  + " " : " ";
					//end
					
					/**
					 * CALC AGE BY BIRTH DATE.
					 */
					DateUtil du = new DateUtil();
					int age = du.getMonthsDiff(
						rs.getString("birth") + " 00:00",
						du.CnvToYYYYMMDD(du.curDate(), '-') + " 00:00"
					);
					System.out.println(age);
					age = (age/12);
					//end
					
					authModel.setAge(age);
					authModel.setStrAddr(addr);
					authModel.setNo(rs.getString("no"));
					authModel.setBlock(rs.getString("block"));
					authModel.setVillage(rs.getString("village"));
					authModel.setAlley(rs.getString("alley"));
					authModel.setRoad(rs.getString("road"));
					authModel.setDistrict(rs.getString("district"));
					authModel.setZipcode(rs.getString("zipcode"));
					authModel.setCity(rs.getString("city"));
					authModel.setProvince(rs.getString("province"));
					authList.add(authModel);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return authList;
	}
	
	/**
	 * Attempting for authenticate.
	 * @author anubissmile
	 * @param String | usr
	 * @param String | pwd
	 * @return HashMap<String, AuthModel>
	 */
	public HashMap<String, AuthModel> attempt(String usr, String pwd){

		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			String sql = "SELECT employee.emp_username, "
					+ "employee.emp_id, employee.pre_name_id, "
					+ "employee.first_name_th, employee.last_name_th, "
					+ "employee.first_name_en, employee.last_name_en, employee.identification, "
					+ "employee.branch_id, branch.branch_code, "
					+ "pre_name.pre_name_id, pre_name.pre_name_th, "
					+ "pre_name.pre_name_en, user_role.role_id, "
					+ "user_role.role_name_th, user_role.role_name_en, "
					+ "user_role.role_level "
					+ "FROM pre_name "
					+ "INNER JOIN employee ON employee.pre_name_id = pre_name.pre_name_id "
					+ "LEFT JOIN branch ON employee.branch_id = branch.branch_id "
					+ "LEFT JOIN user_role ON employee.user_role = user_role.role_level "
					+ "WHERE emp_username = '" + usr + "' AND emp_password = '" + pwd + "'";
			
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
				authModel.setBranchCode(rs.getString("branch_code"));
				authModel.setRole(rs.getInt("role_level"));
				authModel.setIdentification(rs.getString("identification"));
				authModel.setRoleNameTH(rs.getString("role_name_th"));
				authModel.setRoleNameEN(rs.getString("role_name_en"));
				hmrs.put("userEmployee", authModel);
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
