package com.smict.promotion.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BranchData;
import com.smict.person.model.BranchModel;
import com.smict.promotion.data.Promotion_sub_contactdata;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;
import com.smict.promotion.model.Promotion_sub_contactModel;

import ldc.util.Auth;
import ldc.util.Validate;


public class PromotionAction extends ActionSupport {
	private List<PromotionModel> promotionModel;
	private PromotionModel protionModel;
	private List<Promotion_sub_contactModel> promotionsubcontactModel;
	private List<BranchModel> branchmodel;
	private List<PromotionDetailModel> promotiondetailModel;
	private HashMap<String, String> pDetailMap;
	private PromotionDetailModel promotiondetailmodel;
	private List<PromotionModel> getpromotionlist;
	/**
	 * CONSTRUCTOR
	 */
	public PromotionAction(){
		Auth.authCheck(false);
	}
	
	
	public String addPromotionInsert() throws IOException, Exception{
		
		  Promotiondata promoData = new Promotiondata();
		  protionModel.setPromotion_id(promoData.addpromotioninsert(protionModel));
		  
		  if(protionModel.getIs_allbranch()=="0"){
		  promoData.addpromotionbranchinsert(protionModel);
		  }
		  if(protionModel.getIs_allsubcontact()=="0"){
		  promoData.addpromotioncontactinsert(protionModel);
		  }
		  setPromotionModel(promoData.getListPromotion());
		  
		  return SUCCESS;
		 }
	public void validateAddPromotionInsert(){
		Validate v = new Validate();
		if (!v.Check_String_notnull_notempty(protionModel.getName())){
			addFieldError("protionModel.name","please insert Promotion Name");
		}
		if (!v.Check_String_notnull_notempty(protionModel.getStart_date())){
			addFieldError("protionModel.start_date","please insert Start Date");
		}
		if (!v.Check_String_notnull_notempty(protionModel.getEnd_date())){
			addFieldError("protionModel.end_date","please insert End Date");
		}
	}
	public String addPromotion() throws IOException, Exception{
		
		Promotion_sub_contactdata prosubcontactData = new Promotion_sub_contactdata();
		setPromotionsubcontactModel(prosubcontactData.getListPromotion_sub_contact());
		BranchData branchdata = new BranchData();						
		setBranchmodel(branchdata.getListBranch());
		  return NONE;
		 }
	public String getpromotionlist(){

		Promotiondata promoData = new Promotiondata();
		setPromotionModel(promoData.getListPromotion());

		return NONE;
	}

	 public String PromotionDel() throws IOException, Exception{

		 Promotiondata promoData = new Promotiondata();
		 promoData.PromotionDelete(protionModel);  
		setPromotionModel(promoData.getListPromotion());
		  return SUCCESS;

		 }		
	 public String getMemberlist(){
		 Promotiondata promoData = new Promotiondata();
		 setPromotionModel(promoData.getmemberlist());
		 return SUCCESS; 
	 }
	 public String addMember() throws IOException, Exception{
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();
		 String total = request.getParameter("totalamount");
		 String defaultamount = request.getParameter("subcontactamount");
		 if(total!= ""){
			 protionModel.setTotal_amount(Double.parseDouble(total.replace(",", "")));
		 } 
		 if(defaultamount!=""){
			 protionModel.setSub_contact_amount(Double.parseDouble(defaultamount.replace(",", "")));
		 }
				 
		int keepgenkey = promoData.insertMember(protionModel);
		if(!StringUtils.isEmpty(protionModel.getSub_contact_type_id())){
		 if(protionModel.getSub_contact_type_id().equals("2")){
			 promoData.insertsubcontactWallet(keepgenkey,protionModel.getTotal_amount(),null);
		 }
		} 
		 return SUCCESS; 
	 }
	 public String updatestatusChange(){
		 Promotiondata promoData = new Promotiondata();
		 promoData.updateStatusSubcontact(protionModel);
		 return SUCCESS;
	 }
	 public String getEditMember(){
		 Promotiondata promoData = new Promotiondata();
		 setProtionModel(promoData.getMemberModel(Integer.parseInt(protionModel.getSub_contactid())
				 			,Integer.parseInt(protionModel.getSub_contact_type_id())));
		 return SUCCESS;
	 }
	 public String updateMember(){
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();
		 String total = request.getParameter("totalamount");
		 String defaultamount = request.getParameter("subcontactamount");
		 if(total!= "" && total != null){
			 protionModel.setTotal_amount(Double.parseDouble(total.replace(",", "")));
		 } 
		 if(defaultamount!="" && total != null){
			 protionModel.setSub_contact_amount(Double.parseDouble(defaultamount.replace(",", "")));
		 }
		 
		 if(promoData.IsSameMembertype(protionModel.getSub_contactid())&&promoData.IsSameWallet(protionModel.getSub_contactid())){
			 promoData.updateSubcontact(protionModel);
			 if(protionModel.getSub_contact_type_id()!=null){
				 if(protionModel.getSub_contact_type_id().equals("2")){
					 promoData.insertsubcontactWallet(Integer.parseInt(protionModel.getSub_contactid()),protionModel.getTotal_amount(),null);
				 } 
			 }
			  
		 }else{			 
			 promoData.updateSubcontact(protionModel);
			 if(protionModel.getSub_contact_type_id()!=null){
				 if(protionModel.getSub_contact_type_id().equals("2")){
					 promoData.updateisStatusSubcontactWallet(protionModel.getSub_contactid(),"t");
				 }else{
					promoData.updateisStatusSubcontactWallet(protionModel.getSub_contactid(),"f");
				 }
			 }
		 }
		 
		 
		 return SUCCESS;
	 }
	 public String getcompanyMember(){
		 Promotiondata promoData = new Promotiondata();
		 setProtionModel(promoData.getMemberModel(Integer.parseInt(protionModel.getSub_contactid())
				 			,Integer.parseInt(protionModel.getSub_contact_type_id())));
		 if(protionModel.getSub_contact_type_id().equals("2")){
			setGetpromotionlist(promoData.getSubcontactwalletLinelist(protionModel.getSub_contact_walletid()));
			return SUCCESS; 
		 }else if(protionModel.getSub_contact_type_id().equals("3")){
			 
			 return NONE; 
		 }else{
			 
			 return INPUT;
		 }
	 }
	 public String adjustmoneyCompany(){
		 protionModel.getSub_contactid();
		 protionModel.getSub_contact_type_id();
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();		
		 String totalall = request.getParameter("totalamountall");
		 String adjustamount = request.getParameter("adjustamount");
		 String checkAddOrDel = request.getParameter("checktypeis");
		 if(totalall!= "" && totalall != null){
			 protionModel.setTotal_amount(Double.parseDouble(totalall.replace(",", "")));
		 } 		 
		 if(adjustamount!= "" && adjustamount != null){
			 protionModel.setAmount(Double.parseDouble(adjustamount.replace(",", "")));		 
		 }
		 double endamount = 0;
		 if(checkAddOrDel.equals("1")){			 
			 promoData.insertSubcontactWalletline(protionModel.getSub_contact_walletid(),protionModel.getAmount(),2);
			 endamount =  protionModel.getAmount() + protionModel.getTotal_amount();
		 }else{
			 promoData.insertSubcontactWalletline(protionModel.getSub_contact_walletid(),protionModel.getAmount(),3);
			 endamount =  protionModel.getTotal_amount() - protionModel.getAmount(); 
		 }		  
		 promoData.AdjustSubcontactWallet(protionModel.getSub_contact_walletid(),endamount);
		 
		 
		 
		 return SUCCESS;
	 }
	 public String updatedefaultmoneyCompany(){
		 protionModel.getSub_contactid();
		 protionModel.getSub_contact_type_id();
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();		
		 String totalall = request.getParameter("totalamountall");
		 if(totalall!= "" && totalall != null){
			 protionModel.setSub_contact_amount(Double.parseDouble(totalall.replace(",", "")));
			 promoData.updateDefaultmoneySubcontact(protionModel);
		 }  
		 
		 
		 return SUCCESS;
	 }
	 
	 
	public PromotionModel getProtionModel() {
		return protionModel;
	}

	public void setProtionModel(PromotionModel protionModel) {
		this.protionModel = protionModel;
	}
	public List<PromotionModel> getPromotionModel() {
		return promotionModel;
	}
	public void setPromotionModel(List<PromotionModel> promotionModel) {
		this.promotionModel = promotionModel;
	}
	public List<Promotion_sub_contactModel> getPromotionsubcontactModel() {
		return promotionsubcontactModel;
	}
	public void setPromotionsubcontactModel(List<Promotion_sub_contactModel> promotionsubcontactModel) {
		this.promotionsubcontactModel = promotionsubcontactModel;
	}
	public List<BranchModel> getBranchmodel() {
		return branchmodel;
	}
	public void setBranchmodel(List<BranchModel> branchmodel) {
		this.branchmodel = branchmodel;
	}
	public List<PromotionDetailModel> getPromotiondetailModel() {
		return promotiondetailModel;
	}
	public void setPromotiondetailModel(List<PromotionDetailModel> promotiondetailModel) {
		this.promotiondetailModel = promotiondetailModel;
	}
	public PromotionDetailModel getPromotiondetailmodel() {
		return promotiondetailmodel;
	}
	public void setPromotiondetailmodel(PromotionDetailModel promotiondetailmodel) {
		this.promotiondetailmodel = promotiondetailmodel;
	}
	public HashMap<String, String> getpDetailMap() {
		return pDetailMap;
	}
	public void setpDetailMap(HashMap<String, String> pDetailMap) {
		this.pDetailMap = pDetailMap;
	}


	public List<PromotionModel> getGetpromotionlist() {
		return getpromotionlist;
	}


	public void setGetpromotionlist(List<PromotionModel> getpromotionlist) {
		this.getpromotionlist = getpromotionlist;
	}


}

