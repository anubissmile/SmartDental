package com.smict.employee.action;

import java.util.List;

import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionModel;

	

public class EmployeeAction {
	
	private List<PromotionModel> promotionModel;
	private PromotionModel protionModel;
	
	public String getListEmployee(){
		Promotiondata promoData = new Promotiondata();
		setPromotionModel(promoData.getListPromotion());
		
		return NONE;
	}

	public List<PromotionModel> getPromotionModel() {
		return promotionModel;
	}

	public void setPromotionModel(List<PromotionModel> promotionModel) {
		this.promotionModel = promotionModel;
	}

	public PromotionModel getProtionModel() {
		return protionModel;
	}

	public void setProtionModel(PromotionModel protionModel) {
		this.protionModel = protionModel;
	}
	
	
	
	
}
