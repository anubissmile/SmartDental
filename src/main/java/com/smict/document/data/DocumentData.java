package com.smict.document.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.document.model.DocumentModel;

import ldc.util.DBConnect;

public class DocumentData {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null,Stmt2=null;
	ResultSet rs = null;
	public List<DocumentModel> getDocument(String hn,String doc_type){
		List<DocumentModel> docModelList = new ArrayList<DocumentModel>();
		String sql = "SELECT * FROM document_upload WHERE ";
			sql += (!hn.equals(""))? " hn='"+hn+"' and " : " ";
			sql += (!doc_type.equals(""))? " document_folder='"+doc_type+"' and " : " ";
			sql += " hn != ''";
		
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
}
