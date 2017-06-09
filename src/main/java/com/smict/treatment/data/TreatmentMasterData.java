package com.smict.treatment.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.sqlloader.SqlLoaderControlParserImpl;

import com.smict.all.model.ToothModel;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.person.model.BrandModel;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class TreatmentMasterData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();

	
	/**
	 * Add new treatment price list.
	 * @param TreatmentModel tModel | 
	 * @param BrandModel bModel | 
	 * @return int rec | Count of records that get affected.
	 */
	public int addTreatmentPriceList(TreatmentModel tModel, BrandModel bModel){
		int rec = 0;
		List<String> sqlList = new ArrayList<String>();
		String SQL = "INSERT INTO `treatment_pricelist` "
				+ "(`treatment_id`, "
				+ "`brand_id`, "
				+ "`amount`, "
				+ "`price_typeid`) ";
		
		
		if(bModel.getBrandIDArr().length > 0){
			int i = 0;
			double price = 0;
			int type = 0;
			SQL += " VALUES ";
			for(int brandID : bModel.getBrandIDArr()){
				price = type = 0;
				if(tModel.getAmountPrice()[i] >= 0){
					/**
					 * - If amount price was not equal to 0
					 * - Then insert amount price.
					 */
					price = tModel.getAmountPrice()[i];
					type = tModel.getAmountPriceType()[i];
					sqlList.add(" ('" + tModel.getTreatmentID() + "', '" + brandID + "', '" + price + "', '" + type + "') ");
				}
				
				if(tModel.getWelfarePrice()[i] >= 0){
					/**
					 * - If welfare price was not equal to 0
					 * - Then insert amount price.
					 */
					price = tModel.getWelfarePrice()[i];
					type = tModel.getWelfarePriceType()[i];
					sqlList.add(" ('" + tModel.getTreatmentID() + "', '" + brandID + "', '" + price + "', '" + type + "') ");
				}
				
				++i;
			}
		}
		
		SQL += String.join(" , ", sqlList);
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		
		return rec;
	}
	
	/**
	 * Add new treatment master.
	 * @author anubissmile
	 * @param TreatmentModel tModel | Model of tretment.
	 * @return int rec | Count of records that get affected.
	 */
	public int[] addTreatmentMaster(TreatmentModel tModel, BrandModel bModel){
		/**
		 * - int[] rec = {count of row treatment_master, count of row treatment_tooth_type, treatment_master_id, count of row treatment_pricelist}
		 */
		int[] rec = new int[4];
		String[] SQL = {null, null, null};
		int insertID = 0;
		
		/**
		 * Insert treatment_master table command.
		 */
		SQL[0] = "INSERT INTO `treatment_master` (`code`, `nameth`, "
				+ "`nameen`, `auto_homecall`, "
				+ "`recall_typeid`, `is_continue`, `is_repeat`"
				+ "`treatment_mode`, `category_id`, "
				+ "`tooth_pic_code`) "
				+ "VALUES ('" + tModel.getTreatmentCode() + "', '" + tModel.getTreatmentNameTH() + "', "
				+ "'" + tModel.getTreatmentNameEN() + "', '" + tModel.getAutoHomeCall() + "', "
				+ "'" + tModel.getRecall() + "', '" + tModel.getIsContinue() + "', '" + tModel.getIsRepeat() +"', "
				+ "'" + tModel.getTreatmentMode() + "', '" + tModel.getTreatmentCategoryID() + "', "
				+ "'" + tModel.getToothPicCode() + "') ";
		
		/**
		 * Get latest insert id
		 */
		SQL[1] = " SELECT LAST_INSERT_ID() as `last_insert_id` ";
		
		/**
		 * Insert treatment_type table command.
		 */
		SQL[2]= "INSERT INTO `treatment_type` (`treatment_id`, `tooth_type_id`) ";
		
		agent.connectMySQL();
		agent.begin();
		/**
		 * Execute insert treatment_master
		 */
		rec[0] = agent.exeUpdate(SQL[0]);
		
		/**
		 * Get last insert id.
		 */
		agent.exeQuery(SQL[1]);
		try {
			agent.getRs().next();
			rec[2] = insertID = agent.getRs().getInt("last_insert_id");
		} catch (SQLException e) {
			agent.rollback();
			agent.disconnectMySQL();
			e.printStackTrace();
		}
		
		/**
		 * Execute insert treatment_type
		 */
		if(insertID > 0){
			List<String> val = new ArrayList<String>();
			for(int toothType : tModel.getToothTypeIDArr()){
				val.add(" ('" + insertID + "', '" + toothType + "') ");
			}
			SQL[2] += " VALUES " + String.join(" , ", val);
			System.out.println(SQL[2]);
			rec[1] = agent.exeUpdate(SQL[2]);
		}else{
			rec[1] = 0;
		}
		
		/**
		 * Insert treatment price list.
		 */
		tModel.setTreatmentID(rec[2]);
		rec[3] = 0;
		if(rec[2] > 0){
			rec[3] = addTreatmentPriceList(tModel, bModel);
		}
		
		
		
		/**
		 * Commit or rollback.
		 */
		if(rec[0] > 0 && rec[3] > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
	
public List<TreatmentMasterModel> select_treatment_master(TreatmentMasterModel treatmentMasterModel) throws IOException, Exception {
		
		//String doctor_name = "", room_name = "";
		
		String sqlQuery = "select a.treatment_code, a.treatment_nameth, a.treatment_nameen, a.brand_id, a.doctor_revenue_sharing, "
				+ "a.lab_percent, a.autohomecall, a.recall_typeid, a.treatment_type, a.price_standard, a.price_benefit, a.treatment_mode "
				+ "FROM treatment_master a "
				+ "WHERE a.treatment_code <> '' ORDER BY a.treatment_code";
 
		/*if (servicePatientModel.getDoctor_id() != 0)
			sqlQuery += "and a.doctor_id = '"+servicePatientModel.getDoctor_id()+"' "; 
		if(servicePatientModel.getRoom_id() != 0)
			sqlQuery += "and a.room_id = "+servicePatientModel.getRoom_id()+" ";*/
		
		//System.out.println(sqlQuery);
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List <TreatmentMasterModel> resultList = new ArrayList<TreatmentMasterModel>();
		TreatmentMasterModel smModel = null;   
		  
		String treatment_mode = "";
		 
		while (rs.next()){   
			treatment_mode = rs.getString("treatment_mode");
			if(treatment_mode.equals("1")) treatment_mode = "การรักษาแบบธรรมดา";
			else if (treatment_mode.equals("2")) treatment_mode = "การรักษาแบบต่อเนื่อง";
			
			resultList.add(new TreatmentMasterModel(rs.getString("treatment_code"), rs.getString("treatment_nameth"), rs.getString("treatment_nameen"), 
					rs.getInt("brand_id"), rs.getString("doctor_revenue_sharing"), rs.getInt("lab_percent"), rs.getString("autohomecall"), 
					rs.getString("recall_typeid"), rs.getString("treatment_type"), rs.getString("price_standard"), rs.getString("price_benefit")
					, treatment_mode)); 
		//	smModel.setTel_number(rs.getString("tel_number")); 
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return resultList;
		
	}
public List<TreatmentMasterModel> select_treatment_master_history(String hn,int treatment_id) throws IOException, Exception {
	
	//String doctor_name = "", room_name = "";
	
	String sqlQuery = "select a.treatment_code, a.treatment_nameth, a.treatment_nameen, a.brand_id, a.doctor_revenue_sharing "
			+ ",a.lab_percent, a.autohomecall, a.recall_typeid, a.treatment_type, a.price_standard, a.price_benefit, a.treatment_mode "
			+ ",a.type_tooth, a.type_surface, a.type_mouth, a.type_quadrant, a.type_sextant, a.type_arch, a.type_tooth_range "
			+ "FROM treatment_master a "
			+ "WHERE a.treatment_code <> '' "
			+ "and a.treatment_code not in (SELECT treatment_code FROM history_treatment WHERE treatment_id = "+treatment_id+") "
			+ "and a.treatment_code not in (SELECT treatment_code FROM treatcontinue_setup WHERE treatment_code = a.treatment_code and hn = '"+hn+"') "
			+ "ORDER BY a.treatment_code";

	/*if (servicePatientModel.getDoctor_id() != 0)
		sqlQuery += "and a.doctor_id = '"+servicePatientModel.getDoctor_id()+"' "; 
	if(servicePatientModel.getRoom_id() != 0)
		sqlQuery += "and a.room_id = "+servicePatientModel.getRoom_id()+" ";*/
	
	//System.out.println(sqlQuery);
	conn = agent.getConnectMYSql();
	Stmt = conn.createStatement();
	rs = Stmt.executeQuery(sqlQuery);
	
	List <TreatmentMasterModel> resultList = new ArrayList<TreatmentMasterModel>();
	   
	  
	String treatment_mode = "";
	 
	while (rs.next()){   
		treatment_mode = rs.getString("treatment_mode");
		if(treatment_mode.equals("1")) treatment_mode = "การรักษาแบบธรรมดา";
		else if (treatment_mode.equals("2")) treatment_mode = "การรักษาแบบต่อเนื่อง";
		 
		TreatmentMasterModel smModel = new TreatmentMasterModel();
		
		smModel.setTreatment_code(rs.getString("treatment_code"));
		smModel.setTreatment_nameth(rs.getString("treatment_nameth"));
		smModel.setTreatment_nameen(rs.getString("treatment_nameen"));
		smModel.setBrand_id(rs.getInt("brand_id"));
		smModel.setDoctor_revenue_sharing(rs.getString("doctor_revenue_sharing"));
		smModel.setLab_percent(rs.getInt("lab_percent"));
		smModel.setAutohomecall(rs.getString("autohomecall"));
		smModel.setRecall_typeid(rs.getString("recall_typeid"));
		smModel.setTreatment_type(rs.getString("treatment_type"));
		smModel.setPrice_standard(rs.getString("price_standard"));
		smModel.setPrice_benefit(rs.getString("price_benefit"));
		smModel.setTreatment_mode(treatment_mode);
		
		smModel.setType_tooth(rs.getString("type_tooth"));
		smModel.setType_surface(rs.getString("type_surface"));
		smModel.setType_mouth(rs.getString("type_mouth"));
		smModel.setType_quadrant(rs.getString("type_quadrant"));
		smModel.setType_sextant(rs.getString("type_sextant"));
		smModel.setType_arch(rs.getString("type_arch"));
		smModel.setType_tooth_range(rs.getString("type_tooth_range"));
		resultList.add(smModel); 
		  
	}
	
	if (!rs.isClosed())
		rs.close();
	if (!Stmt.isClosed())
		Stmt.close();
	if (!conn.isClosed())
		conn.close();
	
	return resultList;
	
}
	public List<TreatmentMasterModel> select_drug(TreatmentMasterModel treatmentMasterModel) throws IOException, Exception {
	
	//String doctor_name = "", room_name = "";
	
	String sqlQuery = "select a.treatment_code, a.product_id, b.product_name, a.product_transfer, a.product_free, b.price " 
			+ "FROM treatment_product a inner join pro_product b on(b.product_id = a.product_id) "
			+ "WHERE a.product_id <> ''";

	/*if (servicePatientModel.getDoctor_id() != 0)
		sqlQuery += "and a.doctor_id = '"+servicePatientModel.getDoctor_id()+"' "; 
	if(servicePatientModel.getRoom_id() != 0)
		sqlQuery += "and a.room_id = "+servicePatientModel.getRoom_id()+" ";*/
	
	//System.out.println(sqlQuery);
	conn = agent.getConnectMYSql();
	Stmt = conn.createStatement();
	rs = Stmt.executeQuery(sqlQuery);
	
	List <TreatmentMasterModel> resultList = new ArrayList<TreatmentMasterModel>();
	TreatmentMasterModel smModel = null;   
	  
	String treatment_mode = "";
	 
	while (rs.next()){    
		
		resultList.add(new TreatmentMasterModel(rs.getString("product_id"), rs.getString("product_name"), rs.getString("price"), 
				rs.getString("product_transfer"), rs.getString("product_free"))); 
	//	smModel.setTel_number(rs.getString("tel_number")); 
	}
	
	if (!rs.isClosed())
		rs.close();
	if (!Stmt.isClosed())
		Stmt.close();
	if (!conn.isClosed())
		conn.close();
	
	return resultList;
	
}	

	public int AddTreatmentMaster(TreatmentMasterModel treMod,ToothModel tootModel){
		int rt=0;
		String sql = "INSERT INTO treatment_master "
				+ "(treatment_code, treatment_nameth, treatment_nameen, brand_id, doctor_revenue_sharing, lab_percent, autohomecall, "
				+ "recall_typeid, price_standard, price_benefit, tooth_pic_code,treatment_group_code,treatment_mode,type_tooth,type_surface,type_mouth,type_quadrant,type_sextant,type_arch,type_tooth_range) "
				+ "VALUES ('"+treMod.getTreatment_code()+"', '"+treMod.getTreatment_nameth()+"', '"+treMod.getTreatment_nameen()+"', "+treMod.getBrand_id()+", '"+treMod.getDoctor_revenue_sharing()+"', "
						+ ""+treMod.getLab_percent()+", '"+treMod.getAutohomecall()+"', '"+treMod.getRecall_typeid()+"', '"+treMod.getPrice_standard()+"', '"+treMod.getPrice_benefit()+"',"
								+ " '"+treMod.getTooth_pic_code()+"','"+treMod.getTreatment_group_code()+"','"+treMod.getSet_treatmant()+"','"+tootModel.getType_tooth()+"',"
										+ "'"+tootModel.getType_surface()+"','"+tootModel.getType_mouth()+"','"+tootModel.getType_quadrant()+"','"+tootModel.getType_sextant()+"',"
												+ "'"+tootModel.getType_arch()+"','"+tootModel.getType_tooth_rang()+"')" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rt= Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
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
		return rt;
	}
	public void AddTreatmentDoctor(String treatment_code, String doctorid){
  
		String sql = "INSERT INTO treatment_doctor "
				+ "(treatment_code, doctor_id) "
				+ "VALUES ('"+treatment_code+"', '"+doctorid+"')" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
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
	public int select_treatment_product_id(){
		String sqlQuery = "select max(treatment_product_id) as treatment_product_id "
				+ "from treatment_product ";
		int ResultInt = 0; 
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				ResultInt = rs.getInt("treatment_product_id"); 
				ResultInt += 1;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e)  { 
			e.printStackTrace();
		} 
		return ResultInt;
}
	public void AddTreatmentProductYa(int treatment_product_id, String treatment_code, String product_id, 
			int product_transfer, int product_free){
		  
		String sql = "INSERT INTO treatment_product "
				+ "(treatment_product_id, treatment_code, product_id, product_transfer, product_free) "
				+ "VALUES ("+treatment_product_id+", '"+treatment_code+"', '"+product_id+"', "+product_transfer+", "+product_free+")" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
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
	
	
	
	
	public int GetHighest_add_brand()
	
	{
		String sqlQuery = "select MAX(brand_id) as brand_id from brand";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultInt = rs.getInt("brand_id");
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
	
	
	
	
	public int PlusOneID_FormatID(int brand_id)
	{
		if(brand_id == 0)
		{
			brand_id = 1;
		}
		else
		{
			brand_id = brand_id+1;
		}
		
		return brand_id;
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
		
}
