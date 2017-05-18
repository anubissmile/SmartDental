package test.com.smartict.DoctorWorkday;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import ldc.util.DateUtil;

public class GenerateWorkDay {
	@Test
	public void generate(){
		DateUtil dtUtil = new DateUtil();
		String strFirstDay = "2017-05-01";
		DateTime dt = dtUtil.buildDateTime("yyyy-MM-dd", strFirstDay, "");
		DateTimeFormatter df = DateTimeFormat.forPattern("E");
		System.out.println("Date : "+df.print(dt));
	}
}
