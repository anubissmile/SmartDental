package test.com.smict.appointment;

import org.junit.Test;

import com.smict.appointment.action.AppointmentModel;
import com.smict.appointment.action.AppointmentUtil;

import org.junit.Assert;


public class GenerateAppointmentCode {
	
	private String prefix = "APP";
	private char separator = '/';
	private int length = 5, nextNumber = 1, increment = 3;
	private String resultCode;
	
	@SuppressWarnings("deprecation")
	public void testGenAppointmentCode(){
		int result = this.nextNumber + this.increment;
		StringBuilder sb = new StringBuilder();
		
		/**
		 * Make length.
		 */
		int zeroLength = this.length - String.valueOf(result).length();
		if(zeroLength > 0){
			while((sb.toString().length() + 1) <= zeroLength){
				sb.append("0");
			}
			resultCode = sb.append(String.valueOf(result)).toString();
			sb.setLength(0);
		}
		
		/**
		 * Assemply the code.
		 */
		this.resultCode = sb.append(this.prefix).append(this.separator).append(this.resultCode).toString();
		
		Assert.assertEquals("APP/00004", this.resultCode);
	}
	
	@Test
	public void testRunGenAppointmentCode(){
		AppointmentModel appModel = new AppointmentModel();
		appModel.setBranchID("CMI");
		appModel.setBranchCode("431");
		AppointmentModel resultModel = AppointmentUtil.getAppointmentCode(appModel);
	}

}
