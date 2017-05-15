package test.com.smict.person;

import org.junit.Test;

import ldc.util.DateUtil;

public class TestDoctor {
	
	@Test
	public void testConvertDate(){
		String hireDate = "10-05-2017";
		DateUtil d = new DateUtil();
		if(new ldc.util.Validate().Check_String_notnull_notempty(hireDate)){
			hireDate = d.convertDateSpecificationPattern("dd-mm-YYYY", "YYYY-mm-dd", hireDate, false);
			System.out.println(hireDate);
		}
	}
	
//	@Test
//	public void addMultipleTelephoneNo(){
//		for(int i = 1 ; i < 15000 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void removeMultipleTelephoneNo(){
//		for(int i = 1 ; i < 25145 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void updateMultipleTelephoneNo(){
//		for(int i = 1 ; i < 10523 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void addMultipleAddress(){
//		for(int i = 1 ; i < 75140 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void removeMultipleAddress(){
//		for(int i = 1 ; i < 15000 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void updateMultipleAddress(){
//		for(int i = 1 ; i < 42530 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void addFamily(){
//		for(int i = 1 ; i < 7500 ; i++){
//			System.out.println("round "+i);
//		}
//	}
//	
//	@Test
//	public void addDoctor(){
//		for(int i = 1 ; i < 251023 ; i++){
//			System.out.println("round "+i);
//		}
//	}
}
