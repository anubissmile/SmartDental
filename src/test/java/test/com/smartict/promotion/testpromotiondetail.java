package test.com.smartict.promotion;

import org.junit.Test;

import com.smict.promotion.data.PromotionDetailData;

public class testpromotiondetail {

	@Test
	public void getProduct(){
		PromotionDetailData prodata = new PromotionDetailData();
		System.out.println(prodata.getJsonArrayProduct("น้ำมัน").toString());
		
	}
	@Test
	public void getMedicine(){
		PromotionDetailData prodata = new PromotionDetailData();
		System.out.println(prodata.getJsonArrayMedicine("ไพริน").toString());
		
	}
	
	@Test
	public void getTreatment(){
		PromotionDetailData prodata = new PromotionDetailData();
		System.out.println(prodata.getJsonArrayTreatment("ผ่าฟันคุด").toString());
		
	}
	

}
