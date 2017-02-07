package ldc.util;

import java.io.IOException;
import org.joda.time.*;

public class GeneratePatientBranchID {
	
	private int thisYear = DateTime.now().getYear();
	private int yearFormat, yearBE;
	private int nextNumber; 
	private String branchCode;
	private String[] resultID = new String[2];
	
	public void generateBranchHN(String valStr) throws IOException{
		if(!valStr.isEmpty() && valStr != null){
			/**
			 * SPLIT STRING
			 */
			String[] id = valStr.split("-");
			setBranchCode(id[0]);
			setNextNumber(Integer.parseInt(id[1]));

			/**
			 * GET YEAR AND CONVERT INTO RIGHT FORMAT.
			 */
			this.yearBE = parseToBE(this.thisYear);
			this.yearFormat = Integer.parseInt(parseYear(this.yearBE));
//				Assert.assertEquals(60, yearFormat);		
			
			/**
			 * - FETCH NEXT NUMBER AND CONVERT INTO RIGHT FORMAT 0000003.
			 * - INCREMENT AND RETURN IT.
			 */
			resultID[0] = String.valueOf(nextNumber);
			resultID[1] = String.valueOf(++nextNumber);
			for(;resultID[0].length()<7;){
				resultID[0] = "0" + resultID[0];
			}
			resultID[0] = this.branchCode + "-" + String.valueOf(this.yearFormat) + "-" + resultID[0];
			System.out.println(resultID[0]);
//				System.out.println(resultID[0]);
//				return resultID;
		}
	}
	
	public String parseYear(int year){
		return String.valueOf(year).substring(
			2, 
			String.valueOf(year).length()
		);
	}

	public int parseToBE(int year){
		return year + 543;
	}
	
	
	/**
	 * GETTER SETTER ZONE
	 */
	public int getNextNumber() {
		return nextNumber;
	}

	public void setNextNumber(int nextNumber) {
		this.nextNumber = nextNumber;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String[] getResultID() {
		return resultID;
	}

	public void setResultID(String[] resultID) {
		this.resultID = resultID;
	}
	
}
