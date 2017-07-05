package test.com.smict.orm;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;


public class TestORM {
	
	@Test
	public void testORM(){
		//		Laconic lc = new Laconic("product_phase_detail");
		
		
		List<HashMap<String, String>> rs = new Laconic("product_phase_detail").find("1");
		
		for(HashMap<String, String> map : rs){
			System.out.println(Double.valueOf(map.get("amount")));
		}
	}

}
