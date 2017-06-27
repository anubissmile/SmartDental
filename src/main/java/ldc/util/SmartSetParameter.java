package ldc.util;

public class SmartSetParameter {
	public String buildStringParam(String value){
		if(value == null){
			return "NULL";
		}else if(!value.equals("")){
			return "'"+value+"'";
		}else{
			return "";
		}
	}
	
	public String buildIntParam(int receiveInt){
		if(receiveInt > 0) return String.valueOf(receiveInt);
		
		return "NULL";
	}
	
	public String buildDoubleParam(double receiveDouble){
		if(receiveDouble > 0) return String.valueOf(receiveDouble);
		
		return "NULL";
	}
}
