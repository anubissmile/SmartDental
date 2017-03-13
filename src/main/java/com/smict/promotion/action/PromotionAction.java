package com.smict.promotion.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BranchData;
import com.smict.person.model.BranchModel;
import com.smict.promotion.data.Promotion_sub_contactdata;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionModel;
import com.smict.promotion.model.Promotion_sub_contactModel;

import ldc.util.Validate;


public class PromotionAction extends ActionSupport {
	private List<PromotionModel> promotionModel;
	private PromotionModel protionModel;
	private List<Promotion_sub_contactModel> promotionsubcontactModel;
	private List<BranchModel> branchmodel;
	
	
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


}
