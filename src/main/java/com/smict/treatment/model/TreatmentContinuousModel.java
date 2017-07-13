package com.smict.treatment.model;

import java.util.List;

public class TreatmentContinuousModel {
	/**
	 * Treatment continuous phase 
	 */
	private int treatmentID, phaseID;
	private int phase, countNo;
	private double price, startPriceRange, endPriceRange;
	private String createdDate, updatedDate;
	
	/**
	 * Product phase details.
	 */
	private int productPhaseID, productID;
	private int amount, amountFree;
	private String productNameTH, productNameEN;
	private double productPrice;
	
	/**
	 * Treatment phase details
	 */
	private String treatmentCode;
	private String treatmentNameTH, treatmentNameEN;
	
	/**
	 * List.
	 */
	private List<TreatmentContinuousModel> treatmentContinuousModelList;
	private List<TreatmentContinuousModel> productPhaseList;
	private List<TreatmentContinuousModel> treatmentPhaseList;
	
	/**
	 * GETTER & SETTER
	 */
	public int getTreatmentID() {
		return treatmentID;
	}
	public int getPhaseID() {
		return phaseID;
	}
	public int getPhase() {
		return phase;
	}
	public int getCountNo() {
		return countNo;
	}
	public double getPrice() {
		return price;
	}
	public double getStartPriceRange() {
		return startPriceRange;
	}
	public double getEndPriceRange() {
		return endPriceRange;
	}
	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}
	public void setPhaseID(int phaseID) {
		this.phaseID = phaseID;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public void setCountNo(int countNo) {
		this.countNo = countNo;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setStartPriceRange(double startPriceRange) {
		this.startPriceRange = startPriceRange;
	}
	public void setEndPriceRange(double endPriceRange) {
		this.endPriceRange = endPriceRange;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getProductPhaseID() {
		return productPhaseID;
	}
	public int getProductID() {
		return productID;
	}
	public int getAmount() {
		return amount;
	}
	public int getAmountFree() {
		return amountFree;
	}
	public String getProductNameTH() {
		return productNameTH;
	}
	public String getProductNameEN() {
		return productNameEN;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPhaseID(int productPhaseID) {
		this.productPhaseID = productPhaseID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setAmountFree(int amountFree) {
		this.amountFree = amountFree;
	}
	public void setProductNameTH(String productNameTH) {
		this.productNameTH = productNameTH;
	}
	public void setProductNameEN(String productNameEN) {
		this.productNameEN = productNameEN;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getTreatmentCode() {
		return treatmentCode;
	}
	public String getTreatmentNameTH() {
		return treatmentNameTH;
	}
	public String getTreatmentNameEN() {
		return treatmentNameEN;
	}
	public List<TreatmentContinuousModel> getTreatmentContinuousModelList() {
		return treatmentContinuousModelList;
	}
	public void setTreatmentCode(String treatmentCode) {
		this.treatmentCode = treatmentCode;
	}
	public void setTreatmentNameTH(String treatmentNameTH) {
		this.treatmentNameTH = treatmentNameTH;
	}
	public void setTreatmentNameEN(String treatmentNameEN) {
		this.treatmentNameEN = treatmentNameEN;
	}
	public void setTreatmentContinuousModelList(List<TreatmentContinuousModel> treatmentContinuousModelList) {
		this.treatmentContinuousModelList = treatmentContinuousModelList;
	}
	public List<TreatmentContinuousModel> getProductPhaseList() {
		return productPhaseList;
	}
	public List<TreatmentContinuousModel> getTreatmentPhaseList() {
		return treatmentPhaseList;
	}
	public void setProductPhaseList(List<TreatmentContinuousModel> productPhaseList) {
		this.productPhaseList = productPhaseList;
	}
	public void setTreatmentPhaseList(List<TreatmentContinuousModel> treatmentPhaseList) {
		this.treatmentPhaseList = treatmentPhaseList;
	}
}
