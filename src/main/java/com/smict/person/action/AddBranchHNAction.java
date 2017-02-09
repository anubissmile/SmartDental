package com.smict.person.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.BranchData;

import ldc.util.GeneratePatientBranchID;

@SuppressWarnings("serial")
public class AddBranchHNAction extends ActionSupport{
	
	private String branch_code, nextNumber, branchHN, branchID;
	private String patHN;
	private ServicePatientModel servicePatModel;
		
	public String execute() throws Exception{
		/**
		 * GET PARAMETER AND PREPARE DATA
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		setPatHN(servicePatModel.getHn());
		
		if(!request.getParameter("branchModel.branch_code").isEmpty()){
			setBranch_code(request.getParameter("branchModel.branch_code"));
			GeneratePatientBranchID gpbID = new GeneratePatientBranchID();
			gpbID.generateBranchHN(getBranch_code());
			this.branch_code = gpbID.getBranchCode();
			
			setBranchHN(gpbID.getResultID()[0]);
			setNextNumber(gpbID.getResultID()[1]);
			setBranchID(gpbID.getResultID()[2]);
			
			/**
			 * UPDATE DATABASE
			 */
			BranchData bd = new BranchData();
			if(bd.updateBranchHN(
					this.nextNumber,
					this.branch_code,
					this.patHN,
					this.branchHN,
					this.branchID
			)){
				return SUCCESS;
			}
			
		}
		return SUCCESS;
	}
	
	

	/**
	 * GETTER SETTER ZONE
	 */
	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getNextNumber() {
		return nextNumber;
	}

	public void setNextNumber(String nextNumber) {
		this.nextNumber = nextNumber;
	}

	public String getBranchHN() {
		return branchHN;
	}

	public void setBranchHN(String branchHN) {
		this.branchHN = branchHN;
	}

	public String getPatHN() {
		return patHN;
	}

	public void setPatHN(String patHN) {
		this.patHN = patHN;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	
}
