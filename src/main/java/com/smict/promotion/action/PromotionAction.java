package com.smict.promotion.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.ProducttypeDB;
import com.smict.product.model.ProductModel;
import com.smict.promotion.model.PromotionModel;

public class PromotionAction extends ActionSupport {
	private List<ProductModel> proModel;
	private Map<String,String> progroupList;
	private Map<String,String> probrandList;
	private Map<String,String> protypeList;
	private Map<String,String> prounitList;
	private PromotionModel promotionModel;
	
	
	public String promotion() throws IOException, Exception{
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		  return NONE;
		 }
	
	public List<ProductModel> getProModel() {
		return proModel;
	}
	public void setProModel(List<ProductModel> proModel) {
		this.proModel = proModel;
	}
	public Map<String, String> getProgroupList() {
		return progroupList;
	}
	public void setProgroupList(Map<String, String> progroupList) {
		this.progroupList = progroupList;
	}
	public Map<String, String> getProbrandList() {
		return probrandList;
	}
	public void setProbrandList(Map<String, String> probrandList) {
		this.probrandList = probrandList;
	}
	public Map<String, String> getProtypeList() {
		return protypeList;
	}
	public void setProtypeList(Map<String, String> protypeList) {
		this.protypeList = protypeList;
	}
	public Map<String, String> getProunitList() {
		return prounitList;
	}
	public void setProunitList(Map<String, String> prounitList) {
		this.prounitList = prounitList;
	}
	public PromotionModel getPromotionModel() {
		return promotionModel;
	}
	public void setPromotionModel(PromotionModel linkedList) {
		this.promotionModel = linkedList;
	}



}