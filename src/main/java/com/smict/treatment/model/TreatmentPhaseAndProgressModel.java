package com.smict.treatment.model;

public class TreatmentPhaseAndProgressModel {
	
	/**
	 * Treatment.
	 */
	private int treatmentID;
	
	/**
	 * Patient.
	 */
	private String hn;
	
	/**
	 * Treatment continuous phase.
	 */
	private int phaseID, phase, countNo;
	private double price, startPriceRange, endPriceRange;
	private int phaseStatus;
	
	/**
	 * Treatment continuous product detail.
	 */
	private int treatProductID, productID;
	private int amount, amountFree;
	
	/**
	 * Treatment continuous treatment detail.
	 */
	private int treatmentDetailID, treatmentListID;
	
	/**
	 * Patient's treatment progress.
	 */
	private int progressID, progressTreatID;
	private int progressCountNo;
	private int progressStatus;
	private int progressPhaseID; // Maybe never used
	
	/**
	 * Progress state.
	 */
	private int countAllPhase, sumAllPhaseRound;
	
	
	
	/**
	 * GETTER & SETTER ZONE.
	 */
	public int getTreatmentID() {
		return treatmentID;
	}
	public String getHn() {
		return hn;
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
	public int getPhaseStatus() {
		return phaseStatus;
	}
	public int getTreatProductID() {
		return treatProductID;
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
	public int getTreatmentDetailID() {
		return treatmentDetailID;
	}
	public int getTreatmentListID() {
		return treatmentListID;
	}
	public int getProgressID() {
		return progressID;
	}
	public int getProgressTreatID() {
		return progressTreatID;
	}
	public int getProgressCountNo() {
		return progressCountNo;
	}
	public int getProgressStatus() {
		return progressStatus;
	}
	public int getProgressPhaseID() {
		return progressPhaseID;
	}
	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}
	public void setHn(String hn) {
		this.hn = hn;
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
	public void setPhaseStatus(int phaseStatus) {
		this.phaseStatus = phaseStatus;
	}
	public void setTreatProductID(int treatProductID) {
		this.treatProductID = treatProductID;
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
	public void setTreatmentDetailID(int treatmentDetailID) {
		this.treatmentDetailID = treatmentDetailID;
	}
	public void setTreatmentListID(int treatmentListID) {
		this.treatmentListID = treatmentListID;
	}
	public void setProgressID(int progressID) {
		this.progressID = progressID;
	}
	public void setProgressTreatID(int progressTreatID) {
		this.progressTreatID = progressTreatID;
	}
	public void setProgressCountNo(int progressCountNo) {
		this.progressCountNo = progressCountNo;
	}
	public void setProgressStatus(int progressStatus) {
		this.progressStatus = progressStatus;
	}
	public void setProgressPhaseID(int progressPhaseID) {
		this.progressPhaseID = progressPhaseID;
	}
	public int getSumAllPhaseRound() {
		return sumAllPhaseRound;
	}
	public void setSumAllPhaseRound(int sumAllPhaseRound) {
		this.sumAllPhaseRound = sumAllPhaseRound;
	}
	public int getCountAllPhase() {
		return countAllPhase;
	}
	public void setCountAllPhase(int countAllPhase) {
		this.countAllPhase = countAllPhase;
	}
	
	
	
	
}
