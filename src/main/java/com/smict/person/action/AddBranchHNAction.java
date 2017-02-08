package com.smict.person.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;

import ldc.util.DBConnect;
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
			if(updateBranchHN()){
				return SUCCESS;
			}
			
		}
		return SUCCESS;
	}
	
	public boolean updateBranchHN() throws IOException, Exception{
		
		DBConnect agent = new DBConnect();
		Connection conn = null;
		PreparedStatement pStmtUpdate, pStmtInsert = null;
		String sqlUpdate = "UPDATE `branch` SET `next_number`='" + this.nextNumber + "' WHERE (`branch_code`='" + this.branch_code + "')";
		String sqlInsert = "INSERT INTO `patient_file_id` (`hn`, `branch_hn`, `branch_id`) VALUES ("
				+ "'" + this.patHN + "', "
				+ "'" + this.branchHN + "', "
				+ "'" + this.branchID + "')";
		
		conn = agent.getConnectMYSql();
		pStmtUpdate = conn.prepareStatement(sqlUpdate);
		pStmtInsert = conn.prepareStatement(sqlInsert);
		
		return (pStmtUpdate.executeUpdate() > 0 && pStmtInsert.executeUpdate() > 0) ? true : false;
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
