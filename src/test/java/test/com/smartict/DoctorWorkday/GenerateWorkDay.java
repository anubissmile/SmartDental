package test.com.smartict.DoctorWorkday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import ldc.util.DateUtil;

public class GenerateWorkDay {
	public void generate(){
		DateUtil dtUtil = new DateUtil();
		String strFirstDay = "2017-05-01";
		DateTime dt = dtUtil.buildDateTime("yyyy-MM-dd", strFirstDay, "");
		DateTimeFormatter df = DateTimeFormat.forPattern("E");
		System.out.println("Date : "+df.print(dt));
		dt = dt.plusDays(1);
		System.out.println("Date : "+df.print(dt));
	}

	public void isListDuplicate(){
		long start = System.currentTimeMillis();
		List<String> aArr = new ArrayList<String>();
		aArr.add("02-2560");
		aArr.add("02-2560");
		System.out.println(aArr.getClass().getSimpleName());
	    boolean duplicates=false;
	    if(aArr instanceof ArrayList){
		    for (int j=0; j<aArr.size(); j++){
			      for (int k=j+1; k<aArr.size(); k++){
				        if (k!=j && aArr.get(k) == aArr.get(j)){
					          duplicates=true;
					          break;
				        }
			      }
		    }
		    System.out.println(duplicates);
	    }
	    
		long end = System.currentTimeMillis();
		
		System.out.println("Insert time was " + (end - start));
	}


	public void isArrayDuplicate(){
		long start = System.currentTimeMillis();
	
		int[] aArr = new int[]{1,2,3};
		System.out.println(aArr.getClass().getSimpleName());
	    boolean duplicates=false;
	    for (int j=0; j<aArr.length; j++){
		      for (int k=j+1; k<aArr.length; k++){
			        if (k!=j && aArr[k] == aArr[j]){
				          duplicates=true;
				          break;
			        }
		      }
	    }
	    System.out.println(duplicates);
	    
		long end = System.currentTimeMillis();
		System.out.println("Insert time was " + (end - start));
	}
	
	@Test
	public void getTimeDiff(){
		DateUtil dt = new DateUtil();
		System.out.println(dt.getMinuteDiff("12:00:00", "12:15:00"));
	}

	
}
