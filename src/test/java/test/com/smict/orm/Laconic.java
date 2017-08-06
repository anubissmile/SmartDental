package test.com.smict.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;

import ldc.util.DBConnect;

public class Laconic  {

	/**
	 * Declare instance
	 */
	private static Laconic instance;

	/**
	 * DB manager lib.
	 */
	private static DBAccessModel dbModel = new DBAccessModel();
	private static DBAccessController agent = new DBAccessController();
	
	/**
	 * String manager lib.
	 */
	private static StringBuilder sb = new StringBuilder();
	
	/**
	 * Constructor
	 */
	public Laconic(String table){
		try {
			/**
			 * Set table.
			 */
			dbModel.setTable(table);
			
			/**
			 * Make connection.
			 */
			dbModel.setConn(agent.connect(true));
			
			/**
			 * Find index field.
			 */
			dbModel.setRs(
				agent.executeQuery(
					agent.makeStatementInstance(dbModel.getConn()),
					sb.append("SHOW INDEX FROM ").append(table).toString()
				)
			);
			dbModel.getRs().next();
			dbModel.setFieldIndex(dbModel.getRs().getString("Column_name"));
			System.out.println(dbModel.getFieldIndex());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * PREFERENCE METHOD.
	 */
	
	/**
	 * Find row by id.
	 * @author anubi
	 * @param int id | index id.
	 * @return ResultSet rs | result 
	 */
	public List<HashMap<String, String>> find(String id){
		if(!StringUtils.isEmpty(id)){
			where(dbModel.getFieldIndex(), id);
			return dbModel.getResultList();
		}
		return null;
	}
	
	public static Laconic where(String field, String val){
		if(!StringUtils.isEmpty(field) && !StringUtils.isEmpty(val)){
			List<String[]> whereList = new ArrayList<String[]>();
			String[] whereStr = {field, val};
			whereList.add(whereStr);
			dbModel.setWhere(whereList);
			get();
			return instance;
		}
		return null;
	}
	
	private static void castToList(){
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		ResultSet rs = dbModel.getRs();
		if(agent.count(rs) > 0){
			try {
				while(rs.next()){
					HashMap<String, String> rsMap  = new HashMap<String, String>();
					for(String fName : dbModel.getFieldList()){
						rsMap.put(fName, rs.getString(fName));
					}
					rsList.add(rsMap);
				}
				dbModel.setResultList(rsList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			dbModel.setResultList(null);
		}
	}
	
	/**
	 * Set count of field.
	 */
	private static void setCountOfField(){
		ResultSetMetaData rsmd;
		try {
			rsmd = dbModel.getRs().getMetaData();
			dbModel.setFieldCount(rsmd.getColumnCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rsmd = null;
		}
	}
	
	
	/**
	 * Set field's name.
	 */
	private static void setFieldName(){
		ResultSetMetaData rsmd;
		try {
			rsmd = dbModel.getRs().getMetaData();
			int count = rsmd.getColumnCount();
			int iterate = 1;
			List<String> fList = new ArrayList<String>();
			while(iterate <= count){
				fList.add(rsmd.getColumnName(iterate));
				iterate++;
			}
			dbModel.setFieldList(fList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rsmd = null;
		}
	}
	
	
	/**
	 * Get field meta data.
	 */
	private static void getFieldMetaData(){
		/**
		 * Get count of field.  
		 */
		setCountOfField();
		
		/**
		 * Get field's name.
		 */
		setFieldName();
	}
	
	
	/**
	 * TRIGGER METHOD.
	 */
	public static List<HashMap<String, String>> get(){
		dbModel.setState("SELECT");
		queryBuilder();
		dbModel.setRs(
			agent.executeQuery(
				agent.makeStatementInstance(dbModel.getConn()), 
				dbModel.getSQLString()
			)
		);
		
		/**
		 * Get field meta data.
		 */
		getFieldMetaData();
		
		/**
		 * Case to List<HashMap<String, String>>
		 */
		castToList();
		if(agent.count(dbModel.getRs()) > 0){
//			agent.close(dbModel.getConn(), dbModel.getpStmt(), dbModel.getStmt());
			return dbModel.getResultList();
		}
		return null;
	}
	
	private static void queryBuilder(){
		/**
		 * Build state
		 */
		sb.setLength(0);
		sb.append(dbModel.getState());
		sb.append(" ").toString();
		
		
		/**
		 * Build field list.
		 */
		if(dbModel.getSelectedField() != null){
			if(dbModel.getSelectedField().size() > 0){
				String[] fList = null;
				int i = 0;
				for(String[] str : dbModel.getSelectedField()){
					if(str.length > 0){
						if(str.length == 1){
							fList[i] = " " + str[0] + " ";
						}else if(str.length == 2){
							fList[i] = " " + str[0] + " as " + str[1] + " ";
						}
					}
				}
				sb.append(StringUtils.join(fList, ", "));
			}else{
				sb.append(" * ").toString();
			}
		}else{
			sb.append(" * " ).toString();
		}
		
		
		sb.append(" FROM").toString();
		
		/**
		 * Build table name.
		 */
		sb.append(" `" + dbModel.getTable() + "`").toString();
		
		/**
		 * Build where clause condition.
		 */
		sb.append(" WHERE").toString();
		if(dbModel.getWhere().size() > 0){
			List<String> strList = new ArrayList<String>();
			for(String[] val : dbModel.getWhere()){
				String concat = new String();
				if(val.length > 0){
					if(val.length == 1){
						/**
						 * OR, AND conditions
						 */
						sb.append(" " + val[0] + " ");
					}else if(val.length == 2){
						/**
						 * Left equals to right.
						 */
						strList.add(" " + val[0] + " = '" + val[1] + "' ");
					}else{
						/**
						 * Custom the operator 
						 */
						for(String v : val){
							concat += " " + v + " ";
						}
						strList.add(concat);
					}
				}
			}
			StringUtils.join(strList, " AND ");
		}
		
		
		/**
		 * Set Query String to model
		 */
		dbModel.setSQLString(sb.toString());
		System.out.println(dbModel.getSQLString());
		sb.setLength(0);
	}
	
	
}
