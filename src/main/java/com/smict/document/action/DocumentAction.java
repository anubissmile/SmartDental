package com.smict.document.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.document.data.DocumentData;
import com.smict.document.model.DocumentModel;

import ldc.util.Auth;
import ldc.util.Encrypted;

@SuppressWarnings("serial")
public class DocumentAction extends ActionSupport{
	DocumentModel docModel;
	ServicePatientModel servicePatModel; 
	private List<File> myFile;
	private List<String> myFileContentType;
	private List<String> myFileFileName;
	
	private String destPath;
	String alertStatus, alertMessage;
	
	/**
	 * CONSTRUCTOR
	 */
	public DocumentAction(){
		Auth.authCheck(false);
	}
	
	public String begin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("ServicePatientModel")!=null){
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			String hn = servicePatModel.getHn();
			String doc_type = "";
			if(!request.getParameter("dt").toString().equals("All")){
				 doc_type = request.getParameter("dt").toString();
			}
			DocumentData docData = new DocumentData();
			List<DocumentModel> docModelList = new ArrayList<DocumentModel>();
			docModelList = docData.getDocument(hn, doc_type);
			request.setAttribute("DocumentList", docModelList);
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer";
		} 
		return SUCCESS;
	}
	
	public String del() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("ServicePatientModel")!=null){
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			destPath = request.getSession().getServletContext().getRealPath("/");
			String hn			= servicePatModel.getHn();
			String document_id = request.getParameter("del").toString();
			DocumentData docData = new DocumentData();
			int rt = docData.delDocument(document_id,hn,destPath);
			List<DocumentModel> docModelList = new ArrayList<DocumentModel>();
			docModelList = docData.getDocument(hn, "");
			request.setAttribute("DocumentList", docModelList);
			if(rt>0){
				request.setAttribute("status_error", "");
				request.setAttribute("status_success", "ลบไฟล์สำเร็จ!");
			}else{
				request.setAttribute("status_error", "ลบไฟล์ไม่สำเร็จ");
				request.setAttribute("status_success", "");
			}
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer"; 
		} 
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int fileCount = getMyFile().size();
		
		try{
			for(int i = 0; i < fileCount; i++){
				if(session.getAttribute("ServicePatientModel")!=null){
					/**
					 * UPLOAD PROFILE PICTURE.
					 */
					
					servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
					String hn = servicePatModel.getHn();
				
					String folderName = request.getParameter("NameOfFolder").toString();
					String docDate = request.getParameter("docDate").toString();
					destPath = request.getSession().getServletContext().getRealPath("/")+"../Document/"+folderName;
					String lastDot = getMyFileFileName().get(i);
					String extension = lastDot.substring(lastDot.lastIndexOf("."));
					String doc_type = getMyFileContentType().get(i);
					String [] chkType = doc_type.split("/");
					String class_icon = "uk-icon-file-o";
					if(chkType[0].equals("text")){
						class_icon = "uk-icon-file-text-o";
					}else if(chkType[0].equals("image")){
						class_icon = "uk-icon-file-photo-o";
					}else if(chkType[1].equals("msword")){
						class_icon = "uk-icon-file-word-o";
					}else if(chkType[1].equals("vnd.ms-excel")||chkType[1].equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
						class_icon = "uk-icon-file-excel-o";
					}else if(chkType[1].equals("pdf")){
						class_icon = "uk-icon-file-pdf-o";
					}else if(chkType[1].equals("x-rar-compressed")||chkType[1].equals("zip")||chkType[1].equals("octet-stream")){
						class_icon = "uk-icon-file-archive-o";
					}
					
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					Date ConvertDate = new Date();
					calendar.setTime(ConvertDate);
					String newFileName = new Encrypted().encrypt(hn + getMyFileFileName().get(i));
					newFileName = new ldc.util.Validate().removeSpecialChar(newFileName).substring(0, 4);
					newFileName += "-" + hn + "-" + docDate;
					File destFile  = new File(destPath, newFileName+extension);
					
					
					DocumentData docData = new DocumentData();
					int rt = 0;
							
					rt = docData.addDocument(hn, "../Document/"+folderName+"/"+newFileName+extension, doc_type,folderName,class_icon,docDate);
					
					if(rt>0){
						request.setAttribute("status_error", "");
						request.setAttribute("status_success", "อัพโหลดไฟล์สำเร็จ! ชื่อไฟล์ : "+newFileName+extension);
						
						FileUtils.copyFile(myFile.get(i), destFile);
					}else{
						request.setAttribute("status_error", "อัพโหลดไฟล์ไม่สำเร็จ");
						request.setAttribute("status_success", "");
					}
					
					
					List<DocumentModel> docModelList = new ArrayList<DocumentModel>();
					docModelList = docData.getDocument(hn, folderName);
					request.setAttribute("DocumentList", docModelList);
				}else{
					alertStatus = "danger";
					alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
					return "getCustomer"; 
				} 
			}
		 }catch(IOException e){
	         e.printStackTrace();
	         request.setAttribute("status_success", "");
	         request.setAttribute("status_error", "อัพโหลดไฟล์ไม่สำเร็จ! ");
	     }
		return SUCCESS;
	}
	
	public String beginReport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("ServicePatientModel")!=null){
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			String hn = servicePatModel.getHn();
			 
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer";
		} 
		return SUCCESS;
	}
	
	/**
	 * GETTER & SETTER ZONE.
	 */
	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}
	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public String getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}
	public DocumentModel getDocModel() {
		return docModel;
	}
	public void setDocModel(DocumentModel docModel) {
		this.docModel = docModel;
	}


	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}

	public void setMyFileContentType(List<String> myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public void setMyFileFileName(List<String> myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public List<File> getMyFile() {
		return myFile;
	}

	public List<String> getMyFileContentType() {
		return myFileContentType;
	}

	public List<String> getMyFileFileName() {
		return myFileFileName;
	}
	
}
