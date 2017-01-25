package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.person.model.BookBankModel;
import com.smict.person.model.BranchModel;

import ldc.util.DBConnect;

public class BookBankData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	ResultSet rs = null;
	PreparedStatement pStmt = null,pStmt2 = null;
	public List<BookBankModel> Get_banklist() throws IOException, Exception {
		String sqlQuery = "SELECT * FROM bank";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<BookBankModel> ResultList = new ArrayList<BookBankModel>();
		while (rs.next()) {
			BookBankModel bookModel = new BookBankModel();
			bookModel.setBank_id(rs.getString("bank_id"));
			bookModel.setBank_name_th(rs.getString("bank_name_th"));
			bookModel.setBank_name_en(rs.getString("bank_name_en"));
			ResultList.add(bookModel);
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	
	public int add_multi_bookbank(List<BookBankModel> bookbankList){
		int bookbank_id = 0;
		String sql = "select max(bookbank_id)+1 as bookbank_id from bookbank";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				bookbank_id = rs.getInt("bookbank_id");
			}
			sql = "INSERT INTO bookbank (bookbank_id,bank_id,bookbank_no,bookbank_name) VALUES ";
			int i = 0;
			for(BookBankModel bookbankModel :bookbankList){
				i++;
				if(i>1){
					sql += ",";
				}
				sql += "("+bookbank_id+",'"+bookbankModel.getBank_id()+"','"+bookbankModel.getBookbank_no()+"','"+bookbankModel.getBookbank_name()+"')";
			}
			pStmt2 = conn.prepareStatement(sql);
			pStmt2.executeUpdate();
			pStmt2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookbank_id;
	}
	public int add_multi_bookbank(List<BookBankModel> bookbankList,int bookbank_id){
		
		try {
			conn = agent.getConnectMYSql();

			String sql = "INSERT INTO bookbank (bookbank_id,bank_id,bookbank_no,bookbank_name) VALUES ";
			int i = 0;
			for(BookBankModel bookbankModel :bookbankList){
				i++;
				if(i>1){
					sql += ",";
				}
				sql += "("+bookbank_id+",'"+bookbankModel.getBank_id()+"','"+bookbankModel.getBookbank_no()+"','"+bookbankModel.getBookbank_name()+"')";
			}
			pStmt2 = conn.prepareStatement(sql);
			pStmt2.executeUpdate();
			pStmt2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookbank_id;
	}
	public void del_multi_bookbank(int bookbank_id){
		try {
			conn = agent.getConnectMYSql();
			String sql = "DELETE FROM bookbank WHERE bookbank_id="+bookbank_id;
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List <BookBankModel> get_bookBank_detail(int bookbank_id){	
		List <BookBankModel> bookBankList = new ArrayList<BookBankModel>();
		String sql = "SELECT * FROM bookbank WHERE bookbank_id = ?";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,bookbank_id);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				BookBankModel bankModel = new BookBankModel();
				bankModel.setBookbank_id(bookbank_id);
				bankModel.setBank_id(rs.getString("bank_id"));
				bankModel.setBookbank_name(rs.getString("bookbank_name"));
				bankModel.setBookbank_no(rs.getString("bookbank_no"));
				bankModel.setBookbank_status(rs.getString("bookbank_status"));
				bookBankList.add(bankModel);
			}
			if (!rs.isClosed())
				rs.close();
		
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bookBankList; 
	}
}
