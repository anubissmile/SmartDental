package ldc.util;

import java.util.List;

public class Validate {
	public boolean checkIntegerNotZero(int receiveInt){
		
		if(receiveInt > 0) return true;
		
		return false;
	}
	public boolean CheckRegexNumberOnly(String textforCheck){
		boolean resultcheck = false;
		if(textforCheck.equals("")){
			resultcheck = true;
		}else{
			resultcheck = textforCheck.matches("\\d+$");
		}
				
		return resultcheck;
	}
	
	public boolean CheckListNotNull(List ListData){
		boolean resultCheck = false;
		boolean checkListnotnull = false;
		if(ListData != null){
			resultCheck = CheckListNotEmpty(ListData);
		}
		
		return resultCheck;
	}
	public boolean CheckListNotEmpty(List ListData){
		boolean resultCheck = false;
		boolean checkListNotEmpty = false;
		if(!ListData.isEmpty()){
			checkListNotEmpty = true;
		}
		
		return checkListNotEmpty;
	}
	
	/**
	 * 
	 * @param value
	 * @return boolean | return true if not null & not empty else return false.
	 */
	public boolean Check_String_notnull_notempty(String value){
		boolean resultcheck = false;
		if(value == null){
			resultcheck = false;
		}else if(!value.equals("")){
			resultcheck = true;
		}
		
		return resultcheck;
	}
	public boolean Check_String_notnull_notempty(String[] value){
		boolean resultcheck = false;
		if(value == null){
			resultcheck = false;
		}else if(!value.equals("")){
			resultcheck = true;
		}
		return resultcheck;
	}
	
	public boolean Check_Thai_Nationality_ID(String id){
		
		if(id.length() != 13){
			return false;
		}else{
			int sum = 0;
			for(int i=0;i < 12 ; i++){
				sum += Character.getNumericValue(id.charAt(i))*(13-i);
			}
			if((11-sum%11)%10 != Character.getNumericValue(id.charAt(12))){
				return false;
			}else{
				return true;
			}
			
		}
	}
}
