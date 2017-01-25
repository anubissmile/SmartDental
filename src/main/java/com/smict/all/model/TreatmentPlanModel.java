package com.smict.all.model;

import java.util.Date;

public class TreatmentPlanModel extends TreatmentMasterModel {
	private int treatment_planid;
	private String hn, treatmentPlanname, headerStatus, detailStatus,
			surf, tooth, tooth_range, headerStatusName, 
			detailStatusName;
	private Date createDatetime, updateDatetime;
	
	public TreatmentPlanModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTreatment_planid() {
		return treatment_planid;
	}
	public void setTreatment_planid(int treatment_planid) {
		this.treatment_planid = treatment_planid;
	}
	public String getHn() {
		return hn;
	}
	public void setHn(String hn) {
		this.hn = hn;
	}
	public String getTreatmentPlanname() {
		return treatmentPlanname;
	}
	public void setTreatmentPlanname(String treatmentPlanname) {
		this.treatmentPlanname = treatmentPlanname;
	}
	public String getHeaderStatus() {
		return headerStatus;
	}
	public void setHeaderStatus(String headerStatus) {
		this.headerStatus = headerStatus;
	}
	public String getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	public String getSurf() {
		return surf;
	}
	public void setSurf(String surf) {
		this.surf = surf;
	}
	public String getTooth() {
		return tooth;
	}
	public void setTooth(String tooth) {
		this.tooth = tooth;
	}
	public String getTooth_range() {
		return tooth_range;
	}
	public void setTooth_range(String tooth_range) {
		this.tooth_range = tooth_range;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getHeaderStatusName() {
		return headerStatusName;
	}
	public void setHeaderStatusName(String headerStatusName) {
		this.headerStatusName = headerStatusName;
	}
	public String getDetailStatusName() {
		return detailStatusName;
	}
	public void setDetailStatusName(String detailStatusName) {
		this.detailStatusName = detailStatusName;
	}
	
	
}
