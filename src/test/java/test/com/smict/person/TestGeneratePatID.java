package test.com.smict.person;

import org.junit.Assert;
import org.junit.Test;
import org.joda.time.*;

public class TestGeneratePatID {

	private int thisYear = DateTime.now().getYear();
	private int yearFormat, yearBE;
	
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



