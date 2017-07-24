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
import com.smict.person.model.DoctorModel;

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
	public List<BookBankModel> getbookBank_detailList(int docit){
		
		String sqlQuery = "select "
				+ "bookbank.bookbank_id,bookbank.bank_id,bookbank.bookbank_no, "
				+ "bookbank.bookbank_name,bookbank.bookbank_status,bookbank.doctor_id, "
				+ "doctor.first_name_th,doctor.last_name_th,pre_name.pre_name_th,bank.bank_name_th "
				+ "FROM bookbank "
				+ "INNER JOIN doctor ON bookbank.doctor_id = doctor.doctor_id "
				+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
				+ "INNER JOIN bank ON bookbank.bank_id = bank.bank_id "
				+ "WHERE doctor.doctor_id = "+docit+" ";

		
		
		List<BookBankModel> ResultList = new ArrayList<BookBankModel>();
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			
			while(rs.next()){
				BookBankModel tmd = new BookBankModel();
				tmd.setBank_id(rs.getString("bookbank.bank_id"));
				tmd.setBookbank_id(rs.getInt("bookbank.bookbank_id"));
				tmd.setBank_name_th(rs.getString("bank.bank_name_th"));
				tmd.setBookbank_no(rs.getString("bookbank.bookbank_no"));
				tmd.setBook_doctorID(rs.getInt("bookbank.doctor_id"));
				tmd.setBookbank_name(rs.getString("bookbank.bookbank_name"));
				tmd.setDoctorModellist(getdoctoraccountrel(tmd.getBookbank_id()));
				ResultList.add(tmd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultList;
	}
	public List<DoctorModel> getdoctoraccountrel(int bookbankid){
		
		String sqlQuery = "select "
				+ "account_rel_doctorbranch.id,account_rel_doctorbranch.doctor_branch_id, "
				+ "account_rel_doctorbranch.bookbank_id,branch.branch_name "
				+ "FROM account_rel_doctorbranch "
				+ "INNER JOIN branch ON account_rel_doctorbranch.doctor_branch_id = branch.branch_id "
				+ "WHERE account_rel_doctorbranch.bookbank_id = "+bookbankid+" ";

		
		
		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
		ResultSet	rsl = Stmt.executeQuery(sqlQuery);
			
			while(rsl.next()){
				DoctorModel tmd = new DoctorModel();
				tmd.setAccount_branchID(rsl.getString("account_rel_doctorbranch.doctor_branch_id"));
				tmd.setAccount_branchName(rsl.getString("branch.branch_name"));
				ResultList.add(tmd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultList;
	}
	public int insertDoctorbookbank(BookBankModel bookModel,int docid){
		

		String SQL = "INSERT INTO bookbank (bank_id,bookbank_no,bookbank_name,doctor_id ) "
				+ "values ('"+bookModel.getBank_id()+"','"+bookModel.getBookbank_no()+"','"+bookModel.getBookbank_name()+"','"+docid+"' ) ";
					
		int bookid=0;		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (rs.next()){
				bookid=rs.getInt(1);
			}			
			if (!rs.isClosed())
				rs.close();
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookid;
	}
	public void insertDoctorbookbankwithbranch(int bookid,String [] branchID){
		

		String SQL = "INSERT INTO account_rel_doctorbranch (doctor_branch_id,bookbank_id ) "
				+ "values ";
				int i=0;
				for(String branch : branchID){
					if(i>=1){
						SQL+=" , ";
					}
					SQL+="('"+branch+"','"+bookid+"' ) ";
					i++;
				}
					
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void UpdateDoctorbookbank(BookBankModel bookModel){
		

		String SQL = "UPDATE  bookbank "
				+ "SET "
				+ "bank_id = '"+bookModel.getBank_id()+"' "
				+ ",bookbank_no = '"+bookModel.getBookbank_no()+"' "
				+ ",bookbank_name = '"+bookModel.getBookbank_name()+"' "
				+ "WHERE bookbank_id = '"+bookModel.getBookbank_id()+"' ";
					
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void delDoctorbookbankwithbranch(BookBankModel bookModel){
		

		String SQL = "DELETE FROM account_rel_doctorbranch "
				+ "WHERE bookbank_id = '"+bookModel.getBookbank_id()+"' ";
					
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delDoctorbookbank(BookBankModel bookModel){
		

		String SQL = "DELETE FROM bookbank "
				+ "WHERE bookbank_id = '"+bookModel.getBookbank_id()+"' ";
					
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
