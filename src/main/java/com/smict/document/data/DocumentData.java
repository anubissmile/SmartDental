package com.smict.document.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.smict.document.model.DocumentModel;
import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.person.model.PatientModel;
import com.smict.product.model.ProductModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class DocumentData {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null,Stmt2=null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	/**
	 * Change document status key.
	 * @author anubissmile
	 * @param String | docId
	 * @param int | statusKey
	 * @param String | reason
	 * @return int | Count of row that get affected.
	 */
	public int updateDocStatus(DocumentModel docModel){
		String SQL = "UPDATE `document_upload` SET `doc_status`='" + docModel.getStatusKey() + "', "
				+ "`delete_reason`='" + docModel.getReason() + "' WHERE (`document_id`='" + docModel.getDocument_id() + "')";
		
		int rec = 0;
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		agent.commit();
		agent.disconnectMySQL();
		return rec;
	}
	
	/**
	 * Get document status only.
	 * @author anubissmile
	 * @param String | docId
	 * @return int | status code.
	 */
	public List<DocumentModel> getDocStatus(String docId){
		String SQL = "SELECT document_status.ds_id, document_status.ds_description, "
				+ "document_status.ds_key "
				+ "FROM document_status "
				+ "LEFT JOIN document_upload ON document_status.ds_id = document_upload.doc_status "
				+ "WHERE document_upload.document_id = '" + docId + "' ";
		
		List<DocumentModel> docList = new ArrayList<DocumentModel>();
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				while(agent.getRs().next()){
					DocumentModel docModel = new DocumentModel();
					docModel.setStatusKey(agent.getRs().getInt("ds_key"));
					docModel.setStatusDescription(agent.getRs().getString("ds_description"));
					docList.add(docModel);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				agent.disconnectMySQL();
			}
		}
		return docList;
	}
	
	public List<DocumentModel> getDocument(String hn,String doc_type){
		List<DocumentModel> docModelList = new ArrayList<DocumentModel>();
		String sql = "SELECT * FROM document_upload WHERE ";
			sql += (!hn.equals(""))? " hn='"+hn+"' and " : " ";
			sql += (!doc_type.equals(""))? " document_folder='"+doc_type+"' and " : " ";
			sql += " hn != ''";
			sql += " ORDER BY document_date DESC ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				DocumentModel docModel = new DocumentModel();
				docModel.setDocument_id(rs.getInt("document_id"));
				docModel.setHn(hn);
				docModel.setPath(rs.getString("path"));
				docModel.setDoc_type(rs.getString("document_type"));
				String docName = rs.getString("path").substring(rs.getString("path").lastIndexOf("/"));
				docModel.setDoc_name(docName);
				docModel.setDocDate(rs.getString("document_date"));
				docModel.setUpload_date(rs.getString("upload_date"));
				docModel.setDocument_folder(rs.getString("document_folder"));
				docModel.setClass_icon(rs.getString("class_icon"));
				if(rs.getString("delete_reason") != null){
					docModel.setReason(rs.getString("delete_reason"));
				}else{
					docModel.setReason("N/A");
				}
				docModelList.add(docModel);
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		return docModelList;
	}
	public int addDocument(String hn,String path,String doc_type,String folderName,String class_icon,String doc_date){
		int rt = 0;
		String inSQL = "INSERT INTO document_upload (hn,path,document_type,upload_date,document_folder,class_icon,document_date) VALUES("
				+ "'"+hn+"','"+path+"','"+doc_type+"',NOW(),'"+folderName+"','"+class_icon+"','"+doc_date+"')";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rt = Stmt.executeUpdate(inSQL);
			
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rt;
	}
	public int delDocument(String document_id,String hn,String destPath){
		int rt = 0;
		String sql = "SELECT * FROM document_upload WHERE document_id="+document_id+"";
		String inSQL = "DELETE FROM document_upload WHERE document_id="+document_id+" AND hn ='"+hn+"'";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				File file = new File(destPath+rs.getString("path"));
				file.delete();
				Stmt2 = conn.createStatement();
				rt = Stmt2.executeUpdate(inSQL);
				Stmt2.close();
			}
			
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rt;
	}
	
public List<DocumentModel> getListDocumentneed(DocumentModel docu){
		
		int document_id = docu.getDocument_id(); 
		String document_name = docu.getDoc_name();
		
		
		String sql = "SELECT "
				+ "document_id, document_name "
				+ "FROM "
				+ "document_need_master "
				+ "Where ";
		if(new Validate().checkIntegerNotZero(document_id))
			sql += "document_id = "+document_id+" and " ;
		if(new Validate().Check_String_notnull_notempty(document_name))
			sql += "document_name = '"+document_name+"' and " ;
		
		sql += "document_id > 0 " ;
		
		List<DocumentModel> documentList = new LinkedList<DocumentModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				DocumentModel docModel = new DocumentModel();
				
				docModel.setDocument_id(rs.getInt("document_id"));
				docModel.setDoc_name(rs.getString("document_name"));
				
				documentList.add(docModel);
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
		
		return documentList;
	}
	
		
public List<DocumentModel> getListDocument(){
		
		Validate cValidate = new Validate();
		
		
		int document_id = 0;
		
		String sql = "SELECT "
				+ "document_id, document_name "
				+ "FROM "
				+ "document_need_master ";
				
		List<DocumentModel> documentList = new LinkedList<DocumentModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				DocumentModel docModel = new DocumentModel();
				
				docModel.setDocument_id(rs.getInt("document_id"));
				docModel.setDoc_name(rs.getString("document_name"));
				
				documentList.add(docModel);
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
		
		return documentList;
	}
	
	
public boolean addDocNeed(DocumentModel docModel) throws IOException, Exception{
	
	String SQL = "INSERT INTO document_need_master (document_name) VALUES "
				+ "('"+docModel.getDoc_name()+"')";
				

		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		int sStmt = pStmt.executeUpdate();
		
		
		if(sStmt>0){
			return true;
		}
	
			return false;
	
	}



	public boolean delDocNeed(DocumentModel docModel) throws IOException, Exception{
	
	String SQL = "DELETE FROM document_need_master "
					+ " where document_id = '"+docModel.getDocument_id()+"'";
	
	
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		int sStmt = pStmt.executeUpdate();
		
		
		if(sStmt>0){
			return true;
		}
	
			return false;
	
	}
	
	
	
	public boolean updateDocNeed(DocumentModel docModel) throws IOException, Exception{
		
		String SQL = "UPDATE document_need_master SET "
				+ "document_name ='"+docModel.getDoc_name()+"'"
				+ " where document_id = '"+docModel.getDocument_id()+"'";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	
	
	
	
}
