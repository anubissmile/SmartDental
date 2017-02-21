package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.Session;

import com.smict.person.model.AddressModel;
import com.smict.person.model.BranchModel;
import com.smict.person.model.BrandModel;
import com.smict.person.model.TelephoneModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class BranchData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	
	
	/**
	 * chunkBranch call branch table as a chunk and put into BranchModel.BranchModel
	 * @author anubissmile
	 * @return HashMap<String, String>
	 * @throws IOException
	 * @throws Exceptions
	 */
	public HashMap<String, String> chunkBranch() throws IOException, Exception{
		String sql = "SELECT * FROM `branch` ORDER BY branch_code ASC ";
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		
		HashMap<String, String> resultList = new HashMap<String, String>();
		while(rs.next()){
			BranchModel bm = new BranchModel();
			bm.setBranch_id(rs.getString("branch_id"));
			bm.setBranch_code(rs.getString("branch_code") + "-" + String.valueOf(rs.getInt("next_number")) + "-" + rs.getString("branch_id"));
			bm.setNext_number(rs.getInt("next_number"));
			bm.setBranch_name(rs.getString("branch_name"));
//			System.out.println(bm.getBranch_code() + " " + bm.getBranch_name());
			resultList.put(bm.getBranch_code(), bm.getBranch_name());
		}
		
		/**
		 * CLOSE CONNECTION.
		 */
		if(!rs.isClosed()) rs.close();
		if(!Stmt.isClosed()) Stmt.close();
		if(!conn.isClosed()) conn.close();
		
		return resultList;
	}
	
	

	/**
	 * @author anubissmile
	 * @return boolean true if return success or false if return fails.
	 * @throws IOException
	 * @throws Exception
	 */
	public boolean updateBranchHN(String nextNumber, String branch_code, String patHN, String branchHN, String branchID) throws IOException, Exception{
		
		int rsUpdate, rsInsert;
		DBConnect agent = new DBConnect();
		Connection conn = null;
		PreparedStatement pStmtUpdate, pStmtInsert = null;
		String sqlUpdate = "UPDATE `branch` SET `next_number`='" + nextNumber + "' WHERE (`branch_code`='" + branch_code + "')";
		String sqlInsert = "INSERT INTO `patient_file_id` (`hn`, `branch_hn`, `branch_id`) VALUES ("
				+ "'" + patHN + "', "
				+ "'" + branchHN + "', "
				+ "'" + branchID + "')";
		
		conn = agent.getConnectMYSql();
		pStmtUpdate = conn.prepareStatement(sqlUpdate);
		pStmtInsert = conn.prepareStatement(sqlInsert);
		
		rsUpdate = pStmtUpdate.executeUpdate();
		rsInsert = pStmtInsert.executeUpdate();
		
		if(conn.isClosed()) conn.close();
		if(pStmtInsert.isClosed()) pStmtInsert.close();
		if(pStmtUpdate.isClosed()) pStmtUpdate.close();
		
		return (rsUpdate > 0 && rsInsert > 0) ? true : false;
	}
	
	
	
	public List<BranchModel> select_branch(String brand_name, String branch_id, String branch_name, String doctor_name) throws IOException, Exception
	{
		int brand_id = 0; String tel_id = "", tels_id = "";
		String branch_code;
		
		String sqlQuery = "SELECT a.brand_id, b.brand_name, a.branch_code, a.branch_id, a.branch_name, CONCAT(c.first_name_th,' ',c.last_name_th) as first_name_th, e.tel_number, " 
				+ "(select tel_number from tel_telephone ee where ee.tel_id = e.tel_id and ee.tel_typeid = '2' ) AS tel_smartphone "
				+ "FROM branch a "
				+ "inner join brand b on(b.brand_id = a.brand_id) "
				+ "left join doctor c on(c.doctor_id = a.doctor_id) "
				+ "left join tel_telephone e on(e.tel_id = a.tel_id) "
				+ "where ";
		
		
		if (new Validate().Check_String_notnull_notempty(brand_name)){
			sqlQuery += "b.brand_name like '%" + brand_name + "%' and ";
		}

		if (branch_id !=""){
			sqlQuery += "a.branch_id = '"+ branch_id +"' and ";
		}
		
		if (new Validate().Check_String_notnull_notempty(branch_name)){
			sqlQuery += "a.branch_name like '%" + branch_name + "%' and ";
		}
		
		if (new Validate().Check_String_notnull_notempty(doctor_name)){
			sqlQuery += "c.first_name_th like '%" + doctor_name + "%' and ";
		}
		
		sqlQuery += "e.tel_typeid in ('1') and a.branch_id <> '' ";
		
		//System.out.println("-----------");
//		System.out.println(sqlQuery);
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<BranchModel> ResultList = new ArrayList<BranchModel>();
		while (rs.next())
		{
			brand_id 	= rs.getInt("brand_id");
			brand_name 	= rs.getString("brand_name");
			branch_id	= rs.getString("branch_id");
			branch_code = rs.getString("branch_code");
			branch_name = rs.getString("branch_name");
			doctor_name = rs.getString("first_name_th");
			tel_id		= rs.getString("tel_number");
			tels_id		= rs.getString("tel_smartphone"); 
			
			ResultList.add(new BranchModel(brand_id, brand_name, branch_id, branch_name, doctor_name, tel_id, tels_id, branch_code));

		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	public List<BranchModel> set_branchdetail(int brand_id, String branch_id) throws IOException, Exception
	{
		String brand_name = "", branch_name = "", branch_code = "", doctor_name = "";
		String addr_no = "", addr_bloc = "", addr_village = "", addr_alley = "", addr_road = "", addr_provinceid = "", addr_aumphurid = "", addr_districtid = "", addr_zipcode = "";
		String tel_id = "", tels_id = "";
		int doctor_id = 0,  price_doctor = 0;
		
		String sqlQuery = "SELECT a.brand_id, b.brand_name, a.branch_id, a.branch_code, a.branch_name, a.price_doctor, c.doctor_id, CONCAT(c.first_name_th,' ',c.last_name_th) as first_name_th, "
				+ "e.tel_number, (select tel_number from tel_telephone ee where ee.tel_id = a.tel_id and ee.tel_typeid = '2') as tel_smartphone, "
				+ "g.addr_id, g.addr_no, g.addr_bloc, g.addr_village, g.addr_alley, g.addr_road, g.addr_provinceid, g.addr_aumphurid, g.addr_districtid, g.addr_zipcode "
				+ "FROM branch a inner join brand b on(b.brand_id = a.brand_id) "
				+ "left join doctor c on(c.doctor_id = a.doctor_id) "
				+ "left join tel_telephone e on(e.tel_id = a.tel_id) " 
				+ "left join address g on(g.addr_id = a.addr_id) " 
				+ "where ";
  
		if (brand_id !=0)
			sqlQuery += "a.brand_id = " + brand_id + " and ";
		if (branch_id != "")
			sqlQuery += "a.branch_code = '" + branch_id + "' OR a.branch_id = '" + branch_id + "'and ";
		
		sqlQuery += "e.tel_typeid in ('1') and a.branch_id <> '' GROUP BY a.brand_id, a.branch_id ";
		//System.out.println("-----------");
		System.out.println(sqlQuery);
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<BranchModel> ResultList = new ArrayList<BranchModel>();
		while (rs.next())
		{
			brand_id 	= rs.getInt("brand_id");
			brand_name 	= rs.getString("brand_name");
			branch_id	= rs.getString("branch_id");
			branch_code = rs.getString("branch_code");
			branch_name = rs.getString("branch_name");
			price_doctor= rs.getInt("price_doctor");
			doctor_id	= rs.getInt("doctor_id"); 
			doctor_name = rs.getString("first_name_th");
			tel_id		= rs.getString("tel_number");
			tels_id		= rs.getString("tel_smartphone"); 
			 
			addr_no			= rs.getString("addr_no");
			addr_bloc 		= rs.getString("addr_bloc");
			addr_village 	= rs.getString("addr_village");
			addr_alley		= rs.getString("addr_alley");
			addr_road		= rs.getString("addr_road"); 
			addr_provinceid = rs.getString("addr_provinceid");
			addr_aumphurid 	= rs.getString("addr_aumphurid");
			addr_districtid	= rs.getString("addr_districtid");
			addr_zipcode	= rs.getString("addr_zipcode"); 
			
			/**
			 * RETURN BRANCH DETAIL.
			 */
			BranchModel bm = new BranchModel();
			bm.setBranchDetail(
				brand_id, 
				brand_name, 
				branch_id, 
				branch_code,
				branch_name, 
				price_doctor, 
				doctor_id, 
				doctor_name, 
				tel_id, 
				tels_id,
				addr_no, 
				addr_bloc, 
				addr_village, 
				addr_alley, 
				addr_road, 
				addr_provinceid, 
				addr_aumphurid, 
				addr_districtid, 
				addr_zipcode
			);
			ResultList.add(bm);
			
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		return ResultList;
	}
	public void add_branch(BranchModel class_BranchModel)
	{ 
		String[] tel_no = new String[2];
		tel_no[0] = class_BranchModel.getTel_id();
		tel_no[1] = class_BranchModel.getTels_id();
		
		String sql = "insert into branch(brand_id, branch_id, branch_name, doctor_id, price_doctor, addr_id, tel_id, "
				+ "branch_code, next_number) "
				+ "values(?,?,?,?,?,?,?,?,?) ";
	/*	String sql2 = "insert into address(addr_id, addr_no, addr_bloc, addr_village, addr_alley, addr_road, addr_provinceid "
				+ ", addr_aumphurid, addr_districtid, addr_zipcode, addr_typeid) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?) ";   
		String sql3 = "insert into tel_telephone(tel_number, tel_typeid) "
				+ "values(?,?) ";
		String sql4 = "insert into multiple_address(owners, addr_id, addr_groupid) "
				+ "values(?,?,?) ";
		String sql5 = "insert into multiple_telephone(owners, tel_id, tel_groupid) "
				+ "values(?,?,?) ";
	*/	
		try 
		{
			conn = agent.getConnectMYSql(); 
			
		/*	pStmt = conn.prepareStatement(sql2);
			 
			String n1 = class_BranchModel.getAddr_no();
			String n2 = class_BranchModel.getAddr_bloc();
			String n3 = class_BranchModel.getAddr_village();
			String n4 = class_BranchModel.getAddr_alley();
			String n5 = class_BranchModel.getAddr_road();
			String n6 = class_BranchModel.getAddr_provinceid();
			String n7 = class_BranchModel.getAddr_aumphurid();
			String n8 = class_BranchModel.getAddr_districtid();
			String n9 = class_BranchModel.getAddr_zipcode();	
			
			pStmt.setString(1,class_BranchModel.getAddr_no());	
			pStmt.setString(2,class_BranchModel.getAddr_bloc());
			pStmt.setString(3,class_BranchModel.getAddr_village());
			pStmt.setString(4,class_BranchModel.getAddr_alley());
			pStmt.setString(5,class_BranchModel.getAddr_road());
			pStmt.setString(6,class_BranchModel.getAddr_provinceid());
			pStmt.setString(7,class_BranchModel.getAddr_aumphurid());
			pStmt.setString(8,class_BranchModel.getAddr_districtid());
			pStmt.setString(9,class_BranchModel.getAddr_zipcode());
			pStmt.setString(10,"1");
		*/	 
			
			AddressData ad = new AddressData();
			TelephoneData telData = new TelephoneData();
			
			AddressModel addmo = new AddressModel(); 
			
			List <AddressModel>addrlist = new ArrayList<AddressModel>(); 
			List <TelephoneModel> tellist = new ArrayList<TelephoneModel>();
			  
			addmo.setAddr_no(class_BranchModel.getAddr_no());
			addmo.setAddr_bloc(class_BranchModel.getAddr_bloc());
			addmo.setAddr_village(class_BranchModel.getAddr_village());
			addmo.setAddr_alley(class_BranchModel.getAddr_alley());
			addmo.setAddr_road(class_BranchModel.getAddr_road());
			addmo.setAddr_provinceid(class_BranchModel.getAddr_provinceid());
			addmo.setAddr_aumphurid(class_BranchModel.getAddr_aumphurid());
			addmo.setAddr_districtid(class_BranchModel.getAddr_districtid());
			addmo.setAddr_zipcode(class_BranchModel.getAddr_zipcode());
			addrlist.add(addmo);  
			
			int j = 0;
			for(String tel_list : tel_no){ 
				TelephoneModel telModel = new TelephoneModel();
				telModel.setTel_number(tel_list);
				telModel.setTel_typeid(j+1);
				tellist.add(telModel);
				j++;
			}
			
			
			int addr_id = ad.add_multi_address(addrlist); 
			int telid = telData.add_multi_telephone(tellist); 
			
			pStmt = conn.prepareStatement(sql); 
			
			pStmt.setInt(1,class_BranchModel.getBrand_id()); 
			pStmt.setString(2,class_BranchModel.getBranch_id().toUpperCase());
			pStmt.setString(3,class_BranchModel.getBranch_name());    
			pStmt.setInt(4,class_BranchModel.getDoctor_id());
			pStmt.setInt(5,class_BranchModel.getPrice_doctor()); 
			pStmt.setInt(6,addr_id);
			pStmt.setInt(7,telid);
			pStmt.setString(8, class_BranchModel.getBranch_code());
			pStmt.setInt(9, class_BranchModel.getNext_number());
			  
			pStmt.executeUpdate();
			if(pStmt.isClosed()) pStmt.close(); 
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public void update_branch(BranchModel class_BranchModel, String hdbrand_id, String hdbranch_id)
	{ 
		String[] tel_no = new String[2];
		tel_no[0] = class_BranchModel.getTel_id();
		tel_no[1] = class_BranchModel.getTels_id();
		String sql2 = "";
		String sql = "update branch a " 
				+ "set a.brand_id = ?, a.branch_id = ?, a.branch_name = ?, a.doctor_id = ?, a.price_doctor = ? "
				+ "WHERE a.brand_id = ? and a.branch_id = ? ";
		if(class_BranchModel.getAddr_districtid().equals("")&&class_BranchModel.getAddr_aumphurid().equals("")&&class_BranchModel.getAddr_provinceid().equals("")){ 
			sql2 = "update address a INNER JOIN branch b on(b.addr_id = a.addr_id) set "
					+ "a.addr_no = ?, a.addr_bloc = ?, a.addr_village = ?, a.addr_alley = ?, a.addr_road = ?, a.addr_zipcode = ?, a.addr_typeid = ? "
					+ "WHERE b.brand_id = ? and b.branch_id = ? ";
		}else{
			
			sql2 = "update address a INNER JOIN branch b on(b.addr_id = a.addr_id) set "
					+ "a.addr_no = ?, a.addr_bloc = ?, a.addr_village = ?, a.addr_alley = ?, a.addr_road = ?, a.addr_provinceid = ? "
					+ ", a.addr_aumphurid = ?, a.addr_districtid = ?, a.addr_zipcode = ?, a.addr_typeid = ? "
					+ "WHERE b.brand_id = ? and b.branch_id = ? ";
		}
		
		String sql3 = "update tel_telephone a INNER JOIN branch b on(b.tel_id = a.tel_id) set "
				+ "a.tel_number = ? "
				+ "WHERE a.tel_typeid = ? and b.brand_id = ? and b.branch_id = ? "; 
		
		try 
		{
			conn = agent.getConnectMYSql();  
			
			pStmt = conn.prepareStatement(sql2); 
			if(class_BranchModel.getAddr_districtid().equals("")&&class_BranchModel.getAddr_aumphurid().equals("")&&class_BranchModel.getAddr_provinceid().equals("")){
				pStmt.setString(1,class_BranchModel.getAddr_no());	
				pStmt.setString(2,class_BranchModel.getAddr_bloc());
				pStmt.setString(3,class_BranchModel.getAddr_village());
				pStmt.setString(4,class_BranchModel.getAddr_alley());
				pStmt.setString(5,class_BranchModel.getAddr_road()); 
				pStmt.setString(6,class_BranchModel.getAddr_zipcode());
				pStmt.setString(7,"1"); 
				pStmt.setInt(8,class_BranchModel.getBrand_id()); 
				pStmt.setString(9,class_BranchModel.getBranch_id());
			}else{ 
				pStmt.setString(1,class_BranchModel.getAddr_no());	
				pStmt.setString(2,class_BranchModel.getAddr_bloc());
				pStmt.setString(3,class_BranchModel.getAddr_village());
				pStmt.setString(4,class_BranchModel.getAddr_alley());
				pStmt.setString(5,class_BranchModel.getAddr_road());
				pStmt.setString(6,class_BranchModel.getAddr_provinceid());
				pStmt.setString(7,class_BranchModel.getAddr_aumphurid());
				pStmt.setString(8,class_BranchModel.getAddr_districtid());
				pStmt.setString(9,class_BranchModel.getAddr_zipcode());
				pStmt.setString(10,"1"); 
				pStmt.setInt(11,class_BranchModel.getBrand_id()); 
				pStmt.setString(12,class_BranchModel.getBranch_id()); 
			}
		 
			pStmt.executeUpdate(); 
			if(pStmt.isClosed()) pStmt.close();
			 
			 
			for(int i=0,j=0; i<tel_no.length; i++,j++){ 
				
				pStmt = conn.prepareStatement(sql3);
				pStmt.setString(1, tel_no[j]);	
				pStmt.setInt(2, i+1); 
				pStmt.setInt(3,class_BranchModel.getBrand_id());
				pStmt.setString(4,class_BranchModel.getBranch_id());
				pStmt.executeUpdate();
				if(pStmt.isClosed()) pStmt.close();
			
			}	    
			 
			pStmt = conn.prepareStatement(sql); 
			
			pStmt.setInt(1,class_BranchModel.getBrand_id());  
			pStmt.setString(2,class_BranchModel.getBranch_id());
			pStmt.setString(3,class_BranchModel.getBranch_name());    
			pStmt.setInt(4,class_BranchModel.getDoctor_id());
			pStmt.setInt(5,class_BranchModel.getPrice_doctor());   
			pStmt.setString(6,hdbrand_id);
			pStmt.setString(7,hdbranch_id);
			
			pStmt.executeUpdate(); 
			if(pStmt.isClosed()) pStmt.close();
			
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public Boolean DeleteBranch(int brand_id, String branch_id)
	{
		String sqlQuery = "delete from branch where brand_id = ? and branch_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1, brand_id);
			pStmt.setString(2, branch_id);
			int rowsupdate = pStmt.executeUpdate(); 

			if (rowsupdate > 0)
				delete_success = true; 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (!pStmt.isClosed())
					pStmt.close();
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return delete_success;
	}
		
	public List<BrandModel> select_brand(int brand_id, String brand_name) throws IOException, Exception
	{
		String sqlQuery = "select * from brand where ";

		if (brand_id !=0)
			sqlQuery += "brand_id = " + brand_id + " and ";

		if (new Validate().Check_String_notnull_notempty(brand_name))
			sqlQuery += "brand_name like '%" + brand_name + "%' and ";
		
		sqlQuery += "brand_id != '' ";
		//System.out.println("-----------");
		//System.out.println(sqlQuery);
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<BrandModel> ResultList = new ArrayList<BrandModel>();
		while (rs.next())
		{
			
			ResultList.add(new BrandModel(rs.getInt("brand_id"), rs.getString("brand_name")));

		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	
	
	public int GetHighest_add_branch()
	
	{
		String sqlQuery = "select MAX(branch_id) as branch_id from branch";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultInt = rs.getInt("branch_id");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResultInt;
	}
	public int GetHighest_add_addr()
	
	{
		String sqlQuery = "select MAX(addr_id) as addr_id from address";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultInt = rs.getInt("addr_id");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResultInt;
	}
public int GetHighest_add_tel() {
		String sqlQuery = "select MAX(tel_id) as tel_id from tel_telephone";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultInt = rs.getInt("tel_id");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResultInt;
	} 
	
	public int PlusOneID_FormatID(int addr_id)
	{
		if(addr_id == 0){
			addr_id = 1;
		}else {
			addr_id = addr_id+1;
		}
		
		if (String.valueOf(addr_id).length() == 6) addr_id = Integer.valueOf("0"+String.valueOf(addr_id));
		else if (String.valueOf(addr_id).length() == 5) addr_id = Integer.valueOf("00"+String.valueOf(addr_id));
		else if (String.valueOf(addr_id).length() == 4) addr_id = Integer.valueOf("000"+String.valueOf(addr_id));
		else if (String.valueOf(addr_id).length() == 3) addr_id = Integer.valueOf("0000"+String.valueOf(addr_id));
		else if (String.valueOf(addr_id).length() == 2) addr_id = Integer.valueOf("00000"+String.valueOf(addr_id));
		else if (String.valueOf(addr_id).length() == 1) addr_id = Integer.valueOf("000000"+String.valueOf(addr_id)); 
		
		return addr_id;
	}
	
	
	
	
	public Boolean DeleteBrand(int brand_id)
	{
		String sqlQuery = "delete from brand where brand_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1, brand_id);
			int rowsupdate = pStmt.executeUpdate();
			

			if (rowsupdate > 0)
				delete_success = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (!pStmt.isClosed())
					pStmt.close();
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return delete_success;
	} 
	
	public int UpdateBrand(int brand_id, String brand_name)
	{
		
		String sqlQuery = "update brand set brand_id = ? , brand_name = ? where brand_id = ?";
		
		//System.out.println(sqlQuery);
		
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1, brand_id);
			pStmt.setString(2, brand_name);
			pStmt.setInt(3, brand_id);

			
			rowsupdate = pStmt.executeUpdate();
			conn.commit();

		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally 
			{
				try 
				{
					if (!pStmt.isClosed())
						pStmt.close();
					if (!conn.isClosed())
						conn.close();
				} 
			
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return rowsupdate;
	} 
	public List<BranchModel> get_doctor_branch_detail(int doctor_id){
		List<BranchModel> branchList = new ArrayList<BranchModel>();
		
		String sql = "SELECT doctor_branch.branch_id,branch.branch_name "
				+ "FROM branch "
				+ "INNER JOIN doctor_branch ON doctor_branch.branch_id = branch.branch_id "
				+ "INNER JOIN doctor ON doctor.doc_branch_id = doctor_branch.doc_branch_id "
				+ "WHERE doctor.doctor_id ="+doctor_id;
		try {
			conn = agent.getConnectMYSql();	
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				BranchModel branchModel = new BranchModel();
				branchModel.setBranch_id(rs.getString("branch_id"));
				branchModel.setBranch_name(rs.getString("branch_name"));
				branchList.add(branchModel);
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return branchList;
	}
	public List<BranchModel> get_mgr_branch_detail(int doctor_id){
		List<BranchModel> branchList = new ArrayList<BranchModel>();
		
		String sql = "SELECT branch_id,branch_name "
				+ "FROM branch "
				+ "WHERE doctor_id ="+doctor_id;
		try {
			conn = agent.getConnectMYSql();	
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				BranchModel branchModel = new BranchModel();
				branchModel.setBranch_id(rs.getString("branch_id"));
				branchModel.setBranch_name(rs.getString("branch_name"));
				branchList.add(branchModel);
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return branchList;
	}
	
	public int getHighestBranchFileNumber(String branch_id){
		int fileno = 0;
		Validate classValid = new Validate();
		String sql = "SELECT * FROM `running_file` where ";
		if(classValid.Check_String_notnull_notempty(branch_id)) sql += "branch_id = '"+branch_id+"' and ";
		
		sql += "branch_id != ''";
			
		try {
			conn = agent.getConnectMYSql();	
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				fileno = rs.getInt("file_id");
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return fileno;
	}
	
	public void updateBranchFileNo(int highestFileId){
		
	}
}
