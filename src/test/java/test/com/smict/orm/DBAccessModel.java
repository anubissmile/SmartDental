package test.com.smict.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class DBAccessModel {

	private Connection conn;
	private PreparedStatement pStmt;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 * Table name.
	 */
	private String table = "";
	
	/**
	 * Table's key value.
	 */
	private String key = "";
	
	/**
	 * Table's index name;
	 */
	private String fieldIndex = "";
	
	/**
	 * Field name in the List<>.
	 */
	private List<String> fieldList;
	
	/**
	 * Count of field.
	 */
	private int fieldCount;
	
	/**
	 * Selected field list.
	 */
	private List<String[]> selectedField;
	
	/**
	 * Where
	 */
	private List<String[]> where;
	
	/**
	 * Where clause.
	 */
	private String whereClause;
	
	/**
	 * Insert values
	 */
	private List<String[]> insertVal;
	
	/**
	 * Update values
	 */
	private List<String[]> updateVal;
	
	/**
	 * Raw is the SQL String that user can custom
	 */
	private String raw = "";
	
	/**
	 * Generated SQL query string.
	 */
	private String SQLString = "";
	
	/**
	 * State of operation.
	 * SELECT, INSERT, UPDATE, DELETE
	 */
	private String state = "";
	
	/**
	 * Count of row that select or get affected.
	 */
	private int rowCount = 0;
	
	/**
	 * Count of row that get select or get affected in case of multiple query.
	 */
	private List<Integer> rowCountList;
	
	/**
	 * Order by.
	 */
	private List<String[]> orderBy;
	
	/**
	 * Group by.
	 */
	private List<String> groupBy;
	
	
	/**
	 * Multiple Queries
	 */
	private HashMap<String, PreparedStatement> pStMap;
	private HashMap<String, Statement> stMap;
	
	/**
	 * Result set.
	 */
	private List<HashMap<String, String>> resultList;

	
	
	/**
	 * GETTER & SETTER ZONE.
	 */
	public Connection getConn() {
		return conn;
	}
	public PreparedStatement getpStmt() {
		return pStmt;
	}
	public Statement getStmt() {
		return stmt;
	}
	public ResultSet getRs() {
		return rs;
	}
	public HashMap<String, PreparedStatement> getpStMap() {
		return pStMap;
	}
	public HashMap<String, Statement> getStMap() {
		return stMap;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public void setpStmt(PreparedStatement pStmt) {
		this.pStmt = pStmt;
	}
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public void setpStMap(HashMap<String, PreparedStatement> pStMap) {
		this.pStMap = pStMap;
	}
	public void setStMap(HashMap<String, Statement> stMap) {
		this.stMap = stMap;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getKey() {
		return key;
	}
	public List<String> getFieldList() {
		return fieldList;
	}
	public String getFieldIndex() {
		return fieldIndex;
	}
	public List<String[]> getWhere() {
		return where;
	}
	public List<String[]> getInsertVal() {
		return insertVal;
	}
	public List<String[]> getUpdateVal() {
		return updateVal;
	}
	public String getRaw() {
		return raw;
	}
	public String getSQLString() {
		return SQLString;
	}
	public String getState() {
		return state;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}
	public void setFieldIndex(String fieldIndex) {
		this.fieldIndex = fieldIndex;
	}
	public void setWhere(List<String[]> where) {
		this.where = where;
	}
	public List<String[]> getSelectedField() {
		return selectedField;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public int getRowCount() {
		return rowCount;
	}
	public List<Integer> getRowCountList() {
		return rowCountList;
	}
	public List<String[]> getOrderBy() {
		return orderBy;
	}
	public List<String> getGroupBy() {
		return groupBy;
	}
	public void setSelectedField(List<String[]> selectedField) {
		this.selectedField = selectedField;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public void setRowCountList(List<Integer> rowCountList) {
		this.rowCountList = rowCountList;
	}
	public void setOrderBy(List<String[]> orderBy) {
		this.orderBy = orderBy;
	}
	public void setGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
	}
	public void setInsertVal(List<String[]> insertVal) {
		this.insertVal = insertVal;
	}
	public void setUpdateVal(List<String[]> updateVal) {
		this.updateVal = updateVal;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public void setSQLString(String sQLString) {
		SQLString = sQLString;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<HashMap<String, String>> getResultList() {
		return resultList;
	}
	public void setResultList(List<HashMap<String, String>> resultList) {
		this.resultList = resultList;
	}
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
	
	
	
}
