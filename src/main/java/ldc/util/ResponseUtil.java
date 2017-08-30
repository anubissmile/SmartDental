package ldc.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.joda.time.chrono.AssembledChronology;

public class ResponseUtil {
	
	private static HttpServletResponse response;
	private static ResponseUtil instance;
	private static String characterEncode, contentType;
	
	/**
	 * Constructor
	 */
	public ResponseUtil(){
		/**
		 * Set response
		 */
		setInstance(this);
	}
	
	/**
	 * Writing the response.
	 * @author anubi
	 * @param String contentWrite | Content
	 */
	public static void write(String contentWrite){
		try {
			assembly();
			getResponse().getWriter().write(contentWrite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Assembling and register all properties.
	 * @author anubi
	 */
	private static void assembly(){
		HttpServletResponse res = ServletActionContext.getResponse();
		if(characterEncode != null){
			res.setCharacterEncoding(characterEncode);
		}
		if(contentType != null){
			res.setContentType(contentType);
		}
		setResponse(res);
	}
	
	/**
	 * GETTER & SETTER
	 */
	public static ResponseUtil setContentType(String conType){
		if(conType != null){
			contentType = conType;
		}
		return getInstance();
	}
	
	public static ResponseUtil setCharacterEncode(String encode){
		if(encode != null){
			characterEncode = encode;
		}
		return getInstance();
	}
	
	public static ResponseUtil getInstance() {
		return instance;
	}

	public void setInstance(ResponseUtil instance) {
		this.instance = instance;
	}

	public static String getCharacterEncode() {
		return characterEncode;
	}

	public static String getContentType() {
		return contentType;
	}

	public static HttpServletResponse getResponse() {
		return response;
	}

	public static void setResponse(HttpServletResponse response) {
		ResponseUtil.response = response;
	}
	
}
