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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.smict.person.model.AddressModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class AddressData 
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	
	/**
	 * Find address set by district or by zipcode.
	 * @author anubissmile
	 */
	public List<String> findAddrForDropDown(String search){
		List<String> addrSet = new ArrayList<String>();
		String rs = "";
		
		/**
		 * SEARCHING BY DISTRICT NAME.
		 */
		String byDistrict = "SELECT district.DISTRICT_NAME, "
				+ "amphur.AMPHUR_NAME, "
				+ "province.PROVINCE_NAME, "
				+ "zipcode.ZIPCODE, "
				+ "geography.GEO_NAME "
				+ "FROM district "
				+ "LEFT JOIN amphur ON district.AMPHUR_ID = amphur.AMPHUR_ID "
				+ "LEFT JOIN province ON district.PROVINCE_ID = province.PROVINCE_ID "
				+ "LEFT JOIN zipcode ON district.DISTRICT_ID = zipcode.DISTRICT_ID "
				+ "LEFT JOIN geography ON province.GEO_ID = geography.GEO_ID "
				+ "WHERE district.DISTRICT_NAME LIKE '" + search + "%'";
		
		try {
			agent.connectMySQL();
			agent.exeQuery(byDistrict);
			if(agent.size() > 0){
				while(agent.getRs().next()){
					rs = "ตำบล " + agent.getRs().getString("DISTRICT_NAME") + 
							" >> อำเภอ " + agent.getRs().getString("AMPHUR_NAME") + 
							" >> จังหวัด " + agent.getRs().getString("PROVINCE_NAME") + 
							", " + agent.getRs().getString("ZIPCODE");
					addrSet.add(rs);
				}
			}
			agent.disconnectMySQL();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * SEARCHING BY POSTAL CODE.
		 */
		String byZipCode = "SELECT district.DISTRICT_NAME, "
				+ "amphur.AMPHUR_NAME, "
				+ "province.PROVINCE_NAME, "
				+ "zipcode.ZIPCODE, "
				+ "geography.GEO_NAME "
				+ "FROM zipcode "
				+ "LEFT JOIN district ON zipcode.DISTRICT_ID = district.DISTRICT_ID "
				+ "LEFT JOIN amphur ON zipcode.AMPHUR_ID = amphur.AMPHUR_ID "
				+ "LEFT JOIN province ON zipcode.PROVINCE_ID = province.PROVINCE_ID "
				+ "LEFT JOIN geography ON province.GEO_ID = geography.GEO_ID "
				+ "WHERE zipcode.ZIPCODE = '" + search + "' ";
		
		try {
			agent.connectMySQL();
			agent.exeQuery(byZipCode);
			if(agent.size() > 0){
				while(agent.getRs().next()){
					rs = "ตำบล " + agent.getRs().getString("DISTRICT_NAME") + 
							" >> อำเภอ " + agent.getRs().getString("AMPHUR_NAME") + 
							" >> จังหวัด " + agent.getRs().getString("PROVINCE_NAME") + 
							", " + agent.getRs().getString("ZIPCODE");
					addrSet.add(rs);
				}
			}
			agent.disconnectMySQL();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrSet;
	}
	
	/**
	 * Add new Address into database.
	 * @author anubissmile
	 * @param addressModel
	 * @return integer | The count of affected row.
	 */
	public int addNewAddress(AddressModel addressModel){
		String SQL = "INSERT INTO `address` (`addr_id`, `addr_no`, `addr_bloc`, `addr_village`, "
				+ "`addr_alley`, `addr_road`, `addr_provinceid`, `addr_aumphurid`, `addr_districtid`, "
				+ "`addr_zipcode`, `addr_typeid`) VALUES ("
				+ "'" + addressModel.getNew_addr_id() + "', "
				+ "'" + addressModel.getAddr_no() + "', "
				+ "'" + addressModel.getAddr_bloc() + "', "
				+ "'" + addressModel.getAddr_village() + "', "
				+ "'" + addressModel.getAddr_alley() + "', "
				+ "'" + addressModel.getAddr_road() + "', "
				+ "'" + addressModel.getAddr_provinceid() + "', "
				+ "'" + addressModel.getAddr_aumphurid() + "', "
				+ "'" + addressModel.getAddr_districtid() + "', "
				+ "'" + addressModel.getAddr_zipcode() + "', "
				+ "'1')";
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
//		return 0;
	}
	
	public int add_multi_address(List<AddressModel> addressList){
		int addr_id = 0;
		String sql = "select max(addr_id)+1 as addr_id from address";
		try {
			conn = agent.getConnectMYSql();
		//	conn.setAutoCommit(false);
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				addr_id = rs.getInt("addr_id");
			}
			sql = "INSERT INTO address (addr_id,addr_no,addr_bloc,addr_village,addr_alley,addr_road,addr_provinceid,addr_aumphurid,addr_districtid,addr_zipcode,addr_typeid) VALUES ";
			int i = 0;
			for (AddressModel addrModel : addressList) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+addr_id+",'"+addrModel.getAddr_no()+"','"+addrModel.getAddr_bloc()+"','"+addrModel.getAddr_village()+"','"+addrModel.getAddr_alley()
				+"','"+addrModel.getAddr_road()+"','"+addrModel.getAddr_provinceid()+"','"+addrModel.getAddr_aumphurid()+"','"+addrModel.getAddr_districtid()
				+"','"+addrModel.getAddr_zipcode()+"','"+addrModel.getAddr_typeid()+"')";				
			}
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addr_id = 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addr_id = 0;
		}
		
		return addr_id;
	}

	public int getHighestID(){
		int result = 0;
		
		String sql = "select max(addr_id) as addr_id from address";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				result = rs.getInt("addr_id");
			}
			
			if(!rs.isClosed()) rs.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int add_multi_address(List<AddressModel> addressList,int addr_id){
		
		try {
			conn = agent.getConnectMYSql();
		//
			String sql = "INSERT INTO address (addr_id,addr_no,addr_bloc,addr_village,addr_alley,addr_road,addr_provinceid,addr_aumphurid,addr_districtid,addr_zipcode,addr_typeid) VALUES ";
			int i = 0;
			for (AddressModel addrModel : addressList) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+addr_id+",'"+addrModel.getAddr_no()+"','"+addrModel.getAddr_bloc()+"','"+addrModel.getAddr_village()+"','"+addrModel.getAddr_alley()
				+"','"+addrModel.getAddr_road()+"','"+addrModel.getAddr_provinceid()+"','"+addrModel.getAddr_aumphurid()+"','"+addrModel.getAddr_districtid()
				+"','"+addrModel.getAddr_zipcode()+"','"+addrModel.getAddr_typeid()+"')";				
			}
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
		
		return addr_id;
	}
	
	public int add_multi_address(List<AddressModel> addressList, int addr_id, int startIndex){
		
		try {
			conn = agent.getConnectMYSql();

			String sql = "INSERT INTO address (addr_id,addr_no,addr_bloc,addr_village,addr_alley,addr_road,addr_provinceid,addr_aumphurid,addr_districtid,addr_zipcode,addr_typeid) VALUES ";
			
			for (int i = startIndex;i < addressList.size(); i++) {
				AddressModel addrModel = (AddressModel) addressList.get(i);
				if(i>startIndex){
					sql += ",";
				}
				sql +="("+addr_id+",'"+addrModel.getAddr_no()+"','"+addrModel.getAddr_bloc()+"','"+addrModel.getAddr_village()+"','"+addrModel.getAddr_alley()
				+"','"+addrModel.getAddr_road()+"','"+addrModel.getAddr_provinceid()+"','"+addrModel.getAddr_aumphurid()+"','"+addrModel.getAddr_districtid()
				+"','"+addrModel.getAddr_zipcode()+"','"+addrModel.getAddr_typeid()+"')";		
				
			}
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
		
		return addr_id;
	}
	public void del_multi_address(int addr_id){
		try {
			conn = agent.getConnectMYSql();
			String sql = "DELETE FROM address WHERE addr_id="+addr_id;
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

	public Boolean DeleteAddress(String addr_id)
	{
		String sqlQuery = "delete from address where addr_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, addr_id);
			int rowsupdate = pStmt.executeUpdate();
			

			if (rowsupdate > 0)
				delete_success = true;
			
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
	
	
	
	public int UpdateAddress(String addr_id, String addr_no, String addr_bloc, String addr_village, String addr_alley, 
			String addr_road, String addr_provinceid, String addr_aumphurid, String addr_districtid, 
			String addr_zipcode,String addr_typeid)
	{
		
		String sqlQuery = "update address set addr_id = ? , addr_no = ? , addr_bloc = ? , addr_village = ? , "
				+ "addr_alley = ? , addr_road = ? , addr_provinceid = ? , addr_aumphurid = ? , addr_districtid = ? , "
				+ "addr_zipcode = ? , addr_typeid = ? where addr_id = ?";
		
		//System.out.println(sqlQuery);
		
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, addr_id);
			pStmt.setString(2, addr_no);
			pStmt.setString(3, addr_bloc);
			pStmt.setString(4, addr_village);
			pStmt.setString(5, addr_alley);
			pStmt.setString(6, addr_road);
			pStmt.setString(7, addr_provinceid);
			pStmt.setString(8, addr_aumphurid);
			pStmt.setString(9, addr_districtid);
			pStmt.setString(10, addr_zipcode);
			pStmt.setString(11, addr_typeid);
			pStmt.setString(12, addr_id);
			
			
			
			rowsupdate = pStmt.executeUpdate();
			conn.commit();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
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
	
	public void addAddress_type(AddressModel addrModel){
		String sql = "insert into address_type (addr_typeid,addr_typename) values (?,?)";
		try {
			
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, addrModel.getAddr_typeid());
			pStmt.setString(2, addrModel.getAddr_typename());
			pStmt.executeUpdate();
			
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
	}
	public Boolean CheckAddressType(String addrTypeID){
		
		String sqlQuery = "select addr_typeid from address where addr_typeid = ? limit 1";
		Boolean checkPreName = false;
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, addrTypeID);
			rs = pStmt.executeQuery();
			
			while(rs.next()){
				checkPreName = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return checkPreName;
	}
	
	public Boolean DeletePre_name(String addr_typeid){
		
		String sqlQuery = "delete from address_type where addr_typeid = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setString(1, addr_typeid);
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
	
	public List<AddressModel> getAddress_type(AddressModel addrModel){
		String sql = "select * from address_type where ";
		
		if(new Validate().Check_String_notnull_notempty(addrModel.getAddr_typeid())){
			sql += "addr_typeid like '%"+addrModel.getAddr_typeid()+"%' and ";
		}
		
		if(new Validate().Check_String_notnull_notempty(addrModel.getAddr_typename())){
			sql += "addr_typename like '%"+addrModel.getAddr_typename()+"%' and ";
		}
		
		sql += "addr_typeid != '' ";
		
		List<AddressModel> listAddrType = new ArrayList<AddressModel>();
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.prepareStatement(sql);
			rs = Stmt.executeQuery(sql);
			listAddrType = makeList_AddressType(rs);
			
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
		return listAddrType;
	}
	
	public Map<String,String> get_MapAddrType(){
		String sql = "select * from address_type ";
		
		Map<String,String> mapAddrType = new HashMap<String, String>();
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.prepareStatement(sql);
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				mapAddrType.put(rs.getString("addr_typeid"), rs.getString("addr_typename"));
			}
			
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
		return mapAddrType;
	}
	public boolean updateAddrType(AddressModel addrModel){
		
		String sql = "update address_type set  addr_typename = ? where addr_typeid = ?";
		boolean hasUpdate = false;
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, addrModel.getAddr_typename());
			pStmt.setString(2, addrModel.getAddr_typeid());
			int rowsupdate = pStmt.executeUpdate();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
			if(rowsupdate > 0){
				hasUpdate = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasUpdate;
	}
	public List<AddressModel> makeList_AddressType(ResultSet rs){
		
		List<AddressModel> listAddrType = new ArrayList<AddressModel>();
		try {
			
			while (rs.next()) {
				AddressModel addrModel = new AddressModel();
				addrModel.setAddr_typeid(rs.getString("addr_typeid"));
				addrModel.setAddr_typename(rs.getString("addr_typename"));
				listAddrType.add(addrModel);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listAddrType;
	}
	public List<AddressModel> getMultiAddr(int AddressID){
		
		List<AddressModel> addrList = new ArrayList<AddressModel>();
		String sql = "SELECT a.addr_id,a.addr_no,a.addr_bloc,a.addr_village,a.addr_alley,a.addr_road,"
				+ "a.addr_provinceid,a.addr_aumphurid,a.addr_districtid,a.addr_zipcode,a.addr_typeid,"
				+ "ap.AMPHUR_NAME,d.DISTRICT_NAME,p.PROVINCE_NAME "
				+ "FROM address a "
				+ "LEFT JOIN provinces p ON p.PROVINCE_ID = a.addr_provinceid "
				+ "LEFT JOIN amphures ap ON ap.AMPHUR_ID = a.addr_aumphurid "
				+ "LEFT JOIN districts d ON d.DISTRICT_ID = a.addr_districtid WHERE addr_id = ?";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,AddressID);
			ResultSet rsgetMultiAddr = pStmt.executeQuery();
			while (rsgetMultiAddr.next()) {
				AddressModel addrModel = new AddressModel();
				addrModel.setNew_addr_id(rsgetMultiAddr.getInt("addr_id"));
				addrModel.setAddr_no(rsgetMultiAddr.getString("addr_no"));
				addrModel.setAddr_bloc(rsgetMultiAddr.getString("addr_bloc"));
				addrModel.setAddr_village(rsgetMultiAddr.getString("addr_village"));
				addrModel.setAddr_alley(rsgetMultiAddr.getString("addr_alley"));
				addrModel.setAddr_road(rsgetMultiAddr.getString("addr_road"));
				addrModel.setAddr_provinceid(rsgetMultiAddr.getString("addr_provinceid"));
				addrModel.setAddr_province_name(rsgetMultiAddr.getString("PROVINCE_NAME"));
				addrModel.setAddr_aumphurid(rsgetMultiAddr.getString("addr_aumphurid"));
				addrModel.setAddr_aumphur_name(rsgetMultiAddr.getString("AMPHUR_NAME"));
				addrModel.setAddr_districtid(rsgetMultiAddr.getString("addr_districtid"));
				addrModel.setAddr_district_name(rsgetMultiAddr.getString("DISTRICT_NAME"));
				addrModel.setAddr_zipcode(rsgetMultiAddr.getString("addr_zipcode"));
				addrModel.setAddr_typeid(rsgetMultiAddr.getString("addr_typeid"));
				
				addrList.add(addrModel);
			}
			if (!rsgetMultiAddr.isClosed())
				rsgetMultiAddr.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
			if(!conn.isClosed()) conn.close();
			if(!rsgetMultiAddr.isClosed()) rsgetMultiAddr.close();
			if(!pStmt.isClosed()) pStmt.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrList;
	}
	
	public List<AddressModel> buildListAddress(HttpServletRequest request){
		
		List <AddressModel> addrlist = new ArrayList<AddressModel>();
		
		String[] 	addr_no = request.getParameterValues("addrModel.addr_no"),
				addr_bloc = request.getParameterValues("addrModel.addr_bloc"),
				addr_village = request.getParameterValues("addrModel.addr_village"),
				addr_alley = request.getParameterValues("addrModel.addr_alley"),
				addr_road = request.getParameterValues("addrModel.addr_road"),
				addr_provinceid = request.getParameterValues("addrModel.addr_provinceid"),
				addr_aumphurid = request.getParameterValues("addrModel.addr_aumphurid"),
				addr_districtid = request.getParameterValues("addrModel.addr_districtid"),
				addr_typeid = request.getParameterValues("addrModel.addr_typeid"),
				addr_zipcode = request.getParameterValues("addrModel.addr_zipcode");
	
		int x = 0;
		
		for(String addr_list : addr_no){
			AddressModel addrModel = new AddressModel();
			addrModel.setAddr_no(addr_list);
			addrModel.setAddr_bloc(addr_bloc[x]);
			addrModel.setAddr_village(addr_village[x]);
			addrModel.setAddr_alley(addr_alley[x]);
			addrModel.setAddr_road(addr_road[x]);
			addrModel.setAddr_provinceid(addr_provinceid[x]);
			addrModel.setAddr_aumphurid(addr_aumphurid[x]);
			addrModel.setAddr_districtid(addr_districtid[x]);
			addrModel.setAddr_typeid(addr_typeid[x]);
			addrModel.setAddr_zipcode(addr_zipcode[x]);
			addrlist.add(addrModel);
			x++;
		}
		
		return addrlist;
	}
	/*public void add_address(AddressModel class_AddressModel)
	{
		String sql = "insert into address(addr_id, addr_no, addr_bloc, addr_village, addr_alley,"
				+ "addr_road, addr_provinceid, addr_aumphurid, addr_districtid, addr_zipcode,addr_typeid) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		try 
		{
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			
			//System.out.println(sql);		
			
			pStmt.setString(1,class_AddressModel.getAddr_id());
			pStmt.setString(2,class_AddressModel.getAddr_no());
			pStmt.setString(3,class_AddressModel.getAddr_bloc());
			pStmt.setString(4,class_AddressModel.getAddr_village());
			pStmt.setString(5,class_AddressModel.getAddr_alley());
			pStmt.setString(6,class_AddressModel.getAddr_road());
			pStmt.setString(7,class_AddressModel.getAddr_provinceid());
			pStmt.setString(8,class_AddressModel.getAddr_aumphurid());
			pStmt.setString(9,class_AddressModel.getAddr_districtid());
			pStmt.setString(10,class_AddressModel.getAddr_zipcode());
			pStmt.setString(11,class_AddressModel.getAddr_typeid());
			
			
			pStmt.executeUpdate();
			if(!conn.isClosed()) conn.close();
			if(pStmt.isClosed()) pStmt.close();
			
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
	}*/
	/*public List<AddressModel> getMultipleAddress(AddressModel addrModel) throws IOException, Exception
	{
		String sqlQuery = "SELECT "
				+ "a.owners, b.addr_id, b.addr_no, b.addr_bloc, "
				+ "b.addr_village, b.addr_alley, b.addr_road, b.addr_provinceid, "
				+ "b.addr_aumphurid, b.addr_districtid, b.addr_zipcode, c.addr_typeid, "
				+ "c.addr_typename, d.addr_groupid, d.addr_groupname "
				+ "FROM "
				+ "multiple_address AS a "
				+ "INNER JOIN address AS b ON b.addr_id = a.addr_id "
				+ "INNER JOIN address_type AS c ON c.addr_typeid = b.addr_typeid "
				+ "INNER JOIN address_group AS d ON d.addr_groupid = a.addr_groupid where ";

		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_id()))
			sqlQuery += "b.addr_id = '" + addrModel.getAddr_id() + "' and ";

		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_no()))
			sqlQuery += "addr_no like '%" + addrModel.getAddr_no() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_bloc()))
			sqlQuery += "addr_bloc like '%" + addrModel.getAddr_bloc() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_village()))
			sqlQuery += "addr_village like '%" + addrModel.getAddr_village() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_alley()))
			sqlQuery += "addr_alley like '%" + addrModel.getAddr_alley() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_road()))
			sqlQuery += "addr_road like '%" + addrModel.getAddr_road() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_provinceid()))
			sqlQuery += "addr_provinceid like '%" + addrModel.getAddr_provinceid() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_aumphurid()))
			sqlQuery += "addr_aumphurid like '%" + addrModel.getAddr_aumphurid() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_districtid()))
			sqlQuery += "addr_districtid like '%" + addrModel.getAddr_districtid() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_zipcode()))
			sqlQuery += "addr_zipcode like '%" + addrModel.getAddr_zipcode() + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_typeid()))
			sqlQuery += "c.addr_typeid = '" + addrModel.getAddr_typeid() + "' and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_typename()))
			sqlQuery += "addr_typename like '%" + addrModel.getAddr_typename() + "%' and ";
		
		if (new Validate().checkIntegerNotZero(addrModel.getAddr_groupid()))
			sqlQuery += "d.addr_groupid = " + addrModel.getAddr_groupid() + " and ";
		
		if (new Validate().Check_String_notnull_notempty(addrModel.getAddr_groupname()))
			sqlQuery += "addr_groupname like '%" + addrModel.getAddr_groupname() + "%' and ";

		sqlQuery += "b.addr_id != '' ";
		//System.out.println("-----------");
		//System.out.println(sqlQuery);
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<AddressModel> ResultList = new ArrayList<AddressModel>();
		while (rs.next())
		{
			
			ResultList.add(new AddressModel(rs.getString("addr_id"), rs.getString("addr_no"), rs.getString("addr_bloc"), rs.getString("addr_village"), 
					rs.getString("addr_alley"), rs.getString("addr_road"), rs.getString("addr_provinceid"), rs.getString("addr_aumphurid"), 
					rs.getString("addr_districtid"), rs.getString("addr_zipcode"),"",""));
		

		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	
	public void Delete_multi_Address(PatientModel patModel){
		for(AddressModel addrModel: patModel.getAddrModel()){
			String sql =" delete from multiple_address where addr_id = ? and owners = ?";
			
			try {
				
				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, addrModel.getAddr_id());
				pStmt.setString(2, patModel.getHn());
				pStmt.executeUpdate();
				
				if(!pStmt.isClosed()) pStmt.close();
				if(!conn.isClosed()) conn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}*/
	/*
	public List<AddressModel> select_address(String addr_id, String addr_no, String addr_bloc, String addr_village, String addr_alley, 
			String addr_road, String addr_provinceid, String addr_aumphurid, String addr_districtid, 
			String addr_zipcode,String addr_typeid,String addr_typename) throws IOException, Exception
	{
		String sqlQuery = "select * from address where ";

		if (new Validate().Check_String_notnull_notempty(addr_id))
			sqlQuery += "addr_id = '" + addr_id + "' and ";

		if (new Validate().Check_String_notnull_notempty(addr_no))
			sqlQuery += "addr_no like '%" + addr_no + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_bloc))
			sqlQuery += "addr_bloc like '%" + addr_bloc + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_village))
			sqlQuery += "addr_village like '%" + addr_village + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_alley))
			sqlQuery += "addr_alley like '%" + addr_alley + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_road))
			sqlQuery += "addr_road like '%" + addr_road + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_provinceid))
			sqlQuery += "addr_provinceid like '%" + addr_provinceid + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_aumphurid))
			sqlQuery += "addr_aumphurid like '%" + addr_aumphurid + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_districtid))
			sqlQuery += "addr_districtid like '%" + addr_districtid + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_zipcode))
			sqlQuery += "addr_zipcode like '%" + addr_zipcode + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_typeid))
			sqlQuery += "addr_typeid like '%" + addr_typeid + "%' and ";
		
		if (new Validate().Check_String_notnull_notempty(addr_typename))
			sqlQuery += "addr_typename like '%" + addr_typename + "%' and ";

		sqlQuery += "addr_id != '' ";
		//System.out.println("-----------");
		//System.out.println(sqlQuery);
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<AddressModel> ResultList = new ArrayList<AddressModel>();
		while (rs.next())
		{
			
			ResultList.add(new AddressModel(rs.getString("addr_id"), rs.getString("addr_no"), rs.getString("addr_bloc"), rs.getString("addr_village"), 
					rs.getString("addr_alley"), rs.getString("addr_road"), rs.getString("addr_provinceid"), rs.getString("addr_aumphurid"), 
					rs.getString("addr_districtid"), rs.getString("addr_zipcode"),"",""));
		

		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}*/
	
	
	/*public String GetHighest_add_address()
	
	{
		String sqlQuery = "select MAX(addr_id) as addr_id from address";
		String ResultString = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultString = rs.getString("addr_id");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			
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

		return ResultString;
	}
	
	
	public String PlusOneID_FormatID(String addr_id)
	{
		if(addr_id == null)
		{
			addr_id = "0000000001";
		}
		else
		{
			addr_id = String.valueOf((Integer.parseInt(addr_id)+1));
			
			//�ٻ�����ӹǹ addr_id
			int length = addr_id.length();
			for(;length < 10; length++)
			{
				addr_id = "0"+addr_id;
			}
			
			switch(ResultString_plusone.length())
			{
			case 1:addr_id="0"+ResultString_plusone; break;
			default :addr_id=ResultString_plusone; break;
			
		}
		
		return addr_id;
	}
	*/
	
	
	/*
	
	
	
	public String GetHighest_AddrTypeID()
	
	{
		String sqlQuery = "select MAX(addr_typeid) as addr_typeid from address_type";
		String ResultString = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultString = rs.getString("addr_typeid");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			
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

		return ResultString;
	}
	
	public void Add_multi_Address(PatientModel patModel){
		
		for(AddressModel addrModel : patModel.getAddrModel()){
			String sql ="insert into multiple_address values (?,?,?)";
			
			try {
				
				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(2, addrModel.getAddr_id());
				pStmt.setString(1, patModel.getHn());
				pStmt.setString(3, "5");
				pStmt.executeUpdate();
				
				if(!pStmt.isClosed()) pStmt.close();
				if(!conn.isClosed()) conn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean hasMultiAddress(PatientModel patModel){
		
		boolean hasMultiple_address = false;
		
		for(AddressModel addrModel : patModel.getAddrModel()){
			String sql = "select * from multiple_address where owners = ? and addr_id = ? and addr_groupid = ?";
			try {
				
				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, patModel.getHn());
				pStmt.setString(2, addrModel.getAddr_id());
				pStmt.setInt(3, addrModel.getAddr_groupid());
				rs = pStmt.executeQuery();
				
				while (rs.next()) {
					hasMultiple_address = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return hasMultiple_address;
	}*/
	
	
}
