package ldc.util;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class CalculateNumber {
	
	public int plusOneInt(int receive , int countToPlus){
		return receive+countToPlus;
	}
	
	public String plusInt(String receive , int countToPlus){
		return String.valueOf(Integer.parseInt(receive)+countToPlus);
	}
	
	public int getIntAge_fromBirthDate(int yearBirthdate, int monthBirthdate, int dateBirthdate){
		//Importent !!
		//Import JodaTime.jar
		LocalDate birthdate = new LocalDate (yearBirthdate, monthBirthdate, dateBirthdate);
		LocalDate now = new LocalDate();
		Years age = Years.yearsBetween(birthdate, now);

		return age.getYears();
	}
}
