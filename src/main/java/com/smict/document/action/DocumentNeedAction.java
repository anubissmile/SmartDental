package com.smict.document.action;

import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.document.data.DocumentData;
import com.smict.document.model.DocumentModel;

public class DocumentNeedAction  extends ActionSupport{

	private List<DocumentModel> docModel;
	private DocumentModel documentModel;
	
	
	public String DocumentNeed(){
		  DocumentData proDate =new DocumentData();
		  setDocModel(proDate.getListDocument());
		  
		  return NONE;
	}
	
	public String addDocumentNeed() throws IOException, Exception{
		DocumentData docData = new DocumentData();
		  docData.addDocNeed(documentModel);
		  
		  DocumentData proDate =new DocumentData();
		  setDocModel(proDate.getListDocument());
		  return SUCCESS;
		  
		 }
	
	
	
	public String delDocumentNeed() throws IOException, Exception{
				DocumentData docData = new DocumentData();
				  docData.delDocNeed(documentModel);
				  
				  DocumentData proDate =new DocumentData();
				  setDocModel(proDate.getListDocument());
				  return SUCCESS;
				  
		 }
	
	
	public String updateDocumentNeed() throws IOException, Exception{
		DocumentData proData = new DocumentData();
		  proData.updateDocNeed(documentModel);
		  
		  DocumentData proDate =new DocumentData();
		  setDocModel(proDate.getListDocument());
		  return SUCCESS;
	}
	
	
	


	public List<DocumentModel> getDocModel() {
		return docModel;
	}


	public void setDocModel(List<DocumentModel> docModel) {
		this.docModel = docModel;
	}


	public DocumentModel getDocumentModel() {
		return documentModel;
	}


	public void setDocumentModel(DocumentModel documentModel) {
		this.documentModel = documentModel;
	}
	
	
}
