package test.com.smict.person;

import org.junit.Assert;
import org.junit.Test;
import org.joda.time.*;

public class TestGeneratePatID {
	
	private int thisYear = DateTime.now().getYear();
	private int yearFormat, yearBE;
	private int nextNumber = 20, branchCode = 847;
	private String expectID = "847-60-0000020", resultID;
	
	@Test
	public void testGenerateBranchID(){
		/**
		 * GET YEAR AND CONVERT INTO RIGHT FORMAT
		 */
		this.yearBE = parseToBE(this.thisYear);
		this.yearFormat = Integer.parseInt(parseYear(this.yearBE));
//		Assert.assertEquals(60, yearFormat);		
		
		/**
		 * - FETCH NEXT NUMBER AND CONVERT INTO RIGHT FORMAT 0000003.
		 * - INCREMENT AND RETURN IT.
		 */
		resultID = String.valueOf(nextNumber);
		for(;resultID.length()<7;){
			resultID = "0" + resultID;
		}
		resultID = this.branchCode + "-" + this.yearFormat + "-" + resultID;
		System.out.println(resultID);
		Assert.assertEquals(this.expectID, resultID);
		
	}
	
	public String parseYear(int year){
		return String.valueOf(year).substring(
			2, 
			String.valueOf(year).length()
		);
	}

	@Test
	public void testParseYear(){
		this.yearFormat = Integer.parseInt(parseYear(this.thisYear));
		Assert.assertEquals(17, this.yearFormat);
	}
	
	public int parseToBE(int year){
		return year + 543;
	}
	
	@Test
	public void testParseToBE(){
		this.yearBE = parseToBE(this.thisYear);
		this.yearFormat = Integer.parseInt(parseYear(this.yearBE));
		Assert.assertEquals(60, this.yearFormat);
	}
	
	
}



