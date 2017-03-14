package com.smict.promotion.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.ProductData;
import com.smict.product.data.ProductgroupDB;
import com.smict.product.data.ProducttypeDB;
import com.smict.promotion.data.PromotionDetailData;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;

public class PromotionDetailAction extends ActionSupport {
	private List<PromotionDetailModel> promotiondetailModel;
	private List<PromotionDetailModel> promotiondetailModel1;
	private PromotionDetailModel protiondetailModel;
	public String addPromotionDetailInsert() throws IOException, Exception{
		
		  PromotionDetailData proData = new PromotionDetailData();
		  proData.addpromotiondetailinsert(protiondetailModel);
		  
		  PromotionDetailData promotiondetailData =new PromotionDetailData();
		  setPromotiondetailModel(promotiondetailData.getListPromotionDetail());
		  PromotionDetailData promotiondetailData1 =new PromotionDetailData();
		  setPromotiondetailModel1(promotiondetailData1.getListPromotionDetail2());
		  return SUCCESS;
		 }
	
		public String getPromotionDetailList(){
			  
		  PromotionDetailData promotiondetailData =new PromotionDetailData();
		  setPromotiondetailModel(promotiondetailData.getListPromotionDetail());
		  PromotionDetailData promotiondetailData1 =new PromotionDetailData();
		  setPromotiondetailModel1(promotiondetailData1.getListPromotionDetail2());
  
		  return NONE;

		 }
		

	 public String PromotionDetailDel() throws IOException, Exception{

		 PromotionDetailData promodetailData = new PromotionDetailData();
		 promodetailData.PromotionDetailDelete(protiondetailModel);  
		 setPromotiondetailModel(promodetailData.getListPromotionDetail());
		 PromotionDetailData promotiondetailData1 =new PromotionDetailData();
		  setPromotiondetailModel1(promotiondetailData1.getListPromotionDetail2());
		 
		  return SUCCESS;

}

	public List<PromotionDetailModel> getPromotiondetailModel() {
		return promotiondetailModel;
	}

	public void setPromotiondetailModel(List<PromotionDetailModel> promotiondetailModel) {
		this.promotiondetailModel = promotiondetailModel;
	}
	
	public void setProtiondetailModel(List<PromotionDetailModel> promotiondetailModel) {
		this.promotiondetailModel = promotiondetailModel;
	}

	public PromotionDetailModel getProtiondetailModel() {
		return protiondetailModel;
	}

	public void setProtiondetailModel(PromotionDetailModel protiondetailModel) {
		this.protiondetailModel = protiondetailModel;
	}

	public List<PromotionDetailModel> getPromotiondetailModel1() {
		return promotiondetailModel1;
	}

	public void setPromotiondetailModel1(List<PromotionDetailModel> promotiondetailModel1) {
		this.promotiondetailModel1 = promotiondetailModel1;
	}


	
	
	

}
