package com.smict.document.action;

import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.document.data.DocumentData;
import com.smict.document.model.DocumentModel;

@SuppressWarnings("serial")
public class DocumentNeedAction  extends ActionSupport{

	private List<DocumentModel> docModel;
	private DocumentModel documentModel;
	private DocumentData docData;
	
	
	public String DocumentNeed(){
		  DocumentData proDate = new DocumentData();
		  setDocModel(proDate.getListDocument());
		  
		  return NONE;
	}
	
	public String deleteFileByUser(){
//		System.out.println(documentModel.getReason() + " : " + documentModel.getDocument_id());
		DocumentData docData = new DocumentData();
		/**
		 * GET DOC STATUS.
		 */
		documentModel.setOldStatus(docData.getDocStatus(String.valueOf(documentModel.getDocument_id())).get(0).getStatusKey());
		
		
		/** 
		 * UPDATE STATUS (TO DELETED STATUS).
		 */
		documentModel.setStatusKey(2);
		documentModel.setNewStatus(2);
		docData.updateDocStatus(documentModel);
		
		/**
		 * ADD DOCUMENT LOG.
		 */
		
		return SUCCESS;
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
