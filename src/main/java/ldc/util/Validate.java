package ldc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validate {
	
	/**
	 * Removing special character such as [-+.^:=/\\,] from our string set.
	 * @author anubissmile
	 * @param String | str
	 * @return String | null
	 */
	public String removeSpecialChar(String str){
		if(Check_String_notnull_notempty(str)){
			return str.replaceAll("[-+.^:=/\\,]","");
		}
		return null;
	}
	
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
	 * Checking that is null or empty.
	 * @author anubissmile
	 * @param String value | value String
	 * @return String | return value if not null not empty and N/A for else result.
	 */
	public String isNotAvailable(String value){
		boolean resultcheck = false;
		if(value == null){
			resultcheck = false;
		}else if(!value.equals("")){
			resultcheck = true;
		}
		return (resultcheck) ? value : "N/A";
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

	/**
	 * Checking wether duplicate in array set.
	 * @author anubissmile
	 * @param int[] aArr | Array set.
	 * @return boolean | Return true if it's duplicate or otherwise, return false.
	 */
	public static boolean isDuplicate(int[] aArr){
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
	    return duplicates;
	}

	/**
	 * Checking wether duplicate in array set.
	 * @author anubissmile
	 * @param double aArr | Array set.
	 * @return boolean | Return true if it's duplicate or otherwise, return false.
	 */
	public static boolean isDuplicate(double[] aArr){
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
	    return duplicates;
	}

	/**
	 * Checking wether duplicate in array set.
	 * @author anubissmile
	 * @param String aArr | Array set.
	 * @return boolean | Return true if it's duplicate or otherwise, return false.
	 */
	public static boolean isDuplicate(String[] aArr){
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
	    return duplicates;
	}

	/**
	 * Checking wether duplicate in array set.
	 * @author anubissmile
	 * @param char aArr | Array set.
	 * @return boolean | Return true if it's duplicate or otherwise, return false.
	 */
	public static boolean isDuplicate(char[] aArr){
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
	    return duplicates;
	}

	/**
	 * Checking wether duplicate in array set.
	 * @author anubissmile
	 * @param List<?> aArr | List set.
	 * @return boolean | Return true if it's duplicate or otherwise, return false.
	 */
	public static boolean isDuplicate(List<String> aArr){
	    boolean duplicates = false;
	    if(aArr instanceof ArrayList){
		    for (int j=0; j<aArr.size(); j++){
			      for (int k=j+1; k<aArr.size(); k++){
				        if (k!=j && aArr.get(k).equals(aArr.get(j))){
				        	duplicates=true;
				        	break;
				        }
			      }
		    }
	    }
	    return duplicates;
	}
	
}
