package com.smict.promotion.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.ProductBrandDB;
import com.smict.product.data.ProductData;
import com.smict.product.data.ProductUnitDB;
import com.smict.product.data.ProductgroupDB;
import com.smict.product.data.ProducttypeDB;
import com.smict.product.model.ProductModel;
import com.smict.promotion.data.PromotionDetailData;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;

public class PromotionDetailAction extends ActionSupport {
	private List<PromotionDetailModel> promotiondetailModel;
	private List<PromotionDetailModel> promotiondetailModel1;
	PromotionDetailModel proDetailModel;
	private PromotionModel protionModel;

	
	public String addPromotionDetailInsert() throws IOException, Exception{
		
		  PromotionDetailData proData = new PromotionDetailData();
		  proData.addpromotiondetailinsert(proDetailModel);
		  
		  HttpServletRequest requestt = ServletActionContext.getRequest();
			
			PromotionDetailData proDate = new PromotionDetailData();
			setProtiondetailModel(proDate.getListPromotionDetail(String.valueOf(proDetailModel.getPromotion_id())));

		  
		  return SUCCESS;
		 }
	
		public String getPromotionDetailList(){
			  

		  HttpServletRequest request = ServletActionContext.getRequest();
			String id = request.getParameter("pro_id").toString();
			PromotionDetailData proData = new PromotionDetailData();
			setProtionModel(proData.getNameDetail(id));
			
			
			HttpServletRequest requestt = ServletActionContext.getRequest();
			String idpro = requestt.getParameter("pro_id").toString();
			PromotionDetailData proDate = new PromotionDetailData();
			setProtiondetailModel(proDate.getListPromotionDetail(idpro));
			proDetailModel = new PromotionDetailModel();
		  return NONE;

		 }
		

	 public String PromotionDetailDel() throws IOException, Exception{

		 PromotionDetailData promodetailData = new PromotionDetailData();
		 promodetailData.PromotionDetailDelete(proDetailModel);  
		 
		 
		 HttpServletRequest requestt = ServletActionContext.getRequest();
			String idpro = requestt.getParameter("pro_id").toString();
			PromotionDetailData proDate = new PromotionDetailData();
			setProtiondetailModel(proDate.getListPromotionDetail(idpro));
			
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
	
	public List<PromotionDetailModel> getPromotiondetailModel1() {
		return promotiondetailModel1;
	}

	public void setPromotiondetailModel1(List<PromotionDetailModel> promotiondetailModel1) {
		this.promotiondetailModel1 = promotiondetailModel1;
	}

	public PromotionModel getProtionModel() {
		return protionModel;
	}

	public void setProtionModel(PromotionModel protionModel) {
		this.protionModel = protionModel;
	}

	public PromotionDetailModel getProDetailModel() {
		return proDetailModel;
	}

	public void setProDetailModel(PromotionDetailModel proDetailModel) {
		this.proDetailModel = proDetailModel;
	}

}
