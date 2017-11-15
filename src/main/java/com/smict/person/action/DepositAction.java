package com.smict.person.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
 
import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.auth.AuthModel; 
import com.smict.person.data.DepositData;
import com.smict.person.model.DepositModel; 

import ldc.util.Auth; 

public class DepositAction extends ActionSupport{
	
	ServicePatientModel servicePatModel; 
	DepositModel depositModel;
	private List<DepositModel> depositList;
	DepositData depositdb = new DepositData();  
	/**
	 * CONSTRUCTOR
	 */
	public DepositAction(){
		Auth.authCheck(false);
	}
	 
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
	  
		ServicePatientModel servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(session.getAttribute("ServicePatientModel")!=null){ 
			String hn	= servicePatModel.getHn();  
			request.setAttribute("depositList", depositdb.getDeposit(hn));
		}
		
		return SUCCESS;
	}
	public String deposit_add() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		HashMap<String, AuthModel> newMap = (HashMap<String, AuthModel>) session.getAttribute("userSession"); 
		AuthModel authModel = newMap.get("userEmployee");
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(session.getAttribute("userSession")!=null){
			
			depositModel.setHn(servicePatModel.getHn());
			depositModel.setBranch_id(authModel.getBranchID()); 
			depositModel.setDeposit_type(depositModel.getDeposit_type());
			depositModel.setDeposit_by(authModel.getEmpPWD());
			double old_money = depositdb.GetOldMoney(servicePatModel.getHn());
			double transfer_money = Double.parseDouble(String.valueOf(depositModel.getTransfer_money()).replace(",", ""));
			depositModel.setTransfer_money(transfer_money);
			depositModel.setOld_money(old_money);
			depositModel.setTotal_money(old_money+transfer_money);
			
			depositdb.addDeposit(depositModel);
			
			request.setAttribute("depositList", depositdb.getDeposit(servicePatModel.getHn()));
		}  
		
		return SUCCESS;
	}

	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}

	public List<DepositModel> getDepositList() {
		return depositList;
	}

	public void setDepositList(List<DepositModel> depositList) {
		this.depositList = depositList;
	}

	public DepositModel getDepositModel() {
		return depositModel;
	}

	public void setDepositModel(DepositModel depositModel) {
		this.depositModel = depositModel;
	} 
	
}
