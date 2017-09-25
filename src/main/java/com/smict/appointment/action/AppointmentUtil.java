package com.smict.appointment.action;

public class AppointmentUtil {

	/**
	 * Get new appointment code.
	 * - See test.com.smict.appointment.GenerateAppointmentCode.testRunGenAppointmentCode() for scratch.
	 * @author anubi
	 * @param AppointmentModel appModel
	 * @return AppointmentModel appointmentModel
	 */
	public static AppointmentModel getAppointmentCode(AppointmentModel appModel){
		AppointmentModel appointmentModel = new AppointmentModel();
		AppointmentData appData = new AppointmentData();
		int rec = 0;
		
		/**
		 * Find the latest code.
		 */
		appointmentModel = appData.getLatestAppointmentCode(appModel);
		while(String.valueOf(appointmentModel.getAppointmentCodeID()) == null || appointmentModel.getAppointmentCodeID() == 0){
			/**
			 * Make the new one and insert.
			 */
			appointmentModel.setPrefix("APP");
			appointmentModel.setSeparator('/');
			appointmentModel.setLength(5);
			appointmentModel.setNextNumber(1);
			appointmentModel.setIncrement(1);
			appointmentModel.setBranchID(appModel.getBranchID());
			appointmentModel.setBranchCode(appModel.getBranchCode());

			/**
			 * Insert
			 */
			appData.insertAppointmentGenerationCode(appointmentModel);
			
			/**
			 * Find it again.
			 */
			appointmentModel = appData.getLatestAppointmentCode(appModel);
		}

		/**
		 * Generate new appointment code & set next number.
		 */
		appointmentModel = generateAppointmentCode(appointmentModel);
		
		return appointmentModel;
	}
	
	
	/**
	 * PRIVATE ZONE.
	 */
	
	/**
	 * Generate new appointment code.
	 * @author anubi
	 * @param AppointmentModel appointmentModel
	 * @return String | Appointment code.
	 */
	private static AppointmentModel generateAppointmentCode(AppointmentModel appointmentModel){
		AppointmentData appData = new AppointmentData();
		int rec = 0;
		
		/**
		 * Find new code.
		 */
		int nextNumber = appointmentModel.getNextNumber() + appointmentModel.getIncrement();
		StringBuilder sb = new StringBuilder();
		
		/**
		 * Make length.
		 */
		int zeroLength = appointmentModel.getLength() - String.valueOf(appointmentModel.getNextNumber()).length();
		if(zeroLength > 0){
			while((sb.toString().length() + 1) <= zeroLength){
				sb.append("0");
			}
			appointmentModel.setAppointmentCode(sb.append(String.valueOf(appointmentModel.getNextNumber())).toString());
			sb.setLength(0);
		}
		
		/**
		 * Assemply the code.
		 */
		appointmentModel.setAppointCode(sb.append(appointmentModel.getPrefix()).append(appointmentModel.getSeparator()).append(appointmentModel.getAppointmentCode()).toString());
		appointmentModel.setNextNumber(nextNumber);
		
		/**
		 * Update new next number.
		 */
		rec = appData.updateAppointmentNextNumber(appointmentModel);
		
		return appointmentModel;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
