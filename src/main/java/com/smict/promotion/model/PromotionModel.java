package com.smict.promotion.model;

public class PromotionModel {
	String name, use_condition, start_date, end_date, ismonday, istuesday, iswendesday, isthursday, 
					isfriday, issaturday, issunday, start_time, end_time;
	double billcostover;
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUse_condition() {
		return use_condition;
	}
	public void setUse_condition(String use_condition) {
		this.use_condition = use_condition;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getIsmonday() {

		return buildStringfromboolean(ismonday);
	}
	
	public String buildStringfromboolean(String strBoolean){
		if(strBoolean.length()<=4)
		{
			return "1";
		}
		else{
			return "0";
		}

	}
	
	public void setIsmonday(String ismonday) {
		
			this.ismonday = ismonday;
	}
	public String getIstuesday() {
		return buildStringfromboolean(istuesday);
	}
	public void setIstuesday(String istuesday) {
		this.istuesday = istuesday;
	}
	public String getIswendesday() {
		return buildStringfromboolean(iswendesday);
	}
	public void setIswendesday(String iswendesday) {
		this.iswendesday = iswendesday;
	}
	public String getIsthursday() {
		return buildStringfromboolean(isthursday);
	}
	public void setIsthursday(String isthursday) {
		this.isthursday = isthursday;
	}
	public String getIsfriday() {
		return buildStringfromboolean(isfriday);
	}
	public void setIsfriday(String isfriday) {
		this.isfriday = isfriday;
	}
	public String getIssaturday() {
		return buildStringfromboolean(issaturday);
	}
	public void setIssaturday(String issaturday) {
		this.issaturday = issaturday;
	}
	public String getIssunday() {
		return buildStringfromboolean(issunday);
	}
	public void setIssunday(String issunday) {
		this.issunday = issunday;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public double getBillcostover() {
		return billcostover;
	}
	public void setBillcostover(double billcostover) {
		this.billcostover = billcostover;
	}
}
