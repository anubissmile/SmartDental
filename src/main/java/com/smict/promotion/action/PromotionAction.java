package com.smict.promotion.action;

import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionModel;


public class PromotionAction extends ActionSupport {
	private List<PromotionModel> promotionModel;
	private PromotionModel protionModel;
	
	public String addPromotionInsert() throws IOException, Exception{
		
		  Promotiondata promoData = new Promotiondata();
		  promoData.addpromotioninsert(protionModel);
		  setPromotionModel(promoData.getListPromotion());
		  
		  return SUCCESS;
		 }
	public String addPromotion() throws IOException, Exception{
		
		  
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
	
}
