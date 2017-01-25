package com.smict.person.model;

public class BookBankModel {
	private int bookbank_id,bookbank_pk;
	private String bookbank_no,bookbank_name,bookbank_status;
	private String bank_id,bank_name_th,bank_name_en;
	
	public BookBankModel(){
		
	}

	
	
	
	public int getBookbank_id() {
		return bookbank_id;
	}

	public void setBookbank_id(int bookbank_id) {
		this.bookbank_id = bookbank_id;
	}

	public String getBank_id() {
		return bank_id;
	}

	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}

	public String getBank_name_th() {
		return bank_name_th;
	}

	public void setBank_name_th(String bank_name_th) {
		this.bank_name_th = bank_name_th;
	}

	public String getBank_name_en() {
		return bank_name_en;
	}

	public void setBank_name_en(String bank_name_en) {
		this.bank_name_en = bank_name_en;
	}

	public String getBookbank_no() {
		return bookbank_no;
	}
	public void setBookbank_no(String bookbank_no) {
		this.bookbank_no = bookbank_no;
	}
	public String getBookbank_name() {
		return bookbank_name;
	}
	public void setBookbank_name(String bookbank_name) {
		this.bookbank_name = bookbank_name;
	}
	public String getBookbank_status() {
		return bookbank_status;
	}
	public void setBookbank_status(String bookbank_status) {
		this.bookbank_status = bookbank_status;
	}
	public int getBookbank_pk() {
		return bookbank_pk;
	}
	public void setBookbank_pk(int bookbank_pk) {
		this.bookbank_pk = bookbank_pk;
	}
}
