package ldc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {
	
	/**
	 * Redirect the page
	 * @author anubissmile
	 * @param request
	 * @param response
	 * @param site
	 * @throws ServletException
	 * @throws IOException
	 */
	public void redirect(HttpServletRequest request, HttpServletResponse response, String site) throws
		ServletException, IOException{
		
		response.setContentType("text/html");  
		PrintWriter pw = response.getWriter();  

		response.sendRedirect(site);  
		  
		pw.close();  
	}
	

	/**
	 * Get site root path.
	 * @author anubissmile
	 * @return String
	 */
	public static String realPath(String path){
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession().getServletContext().getRealPath(path);
	}
}
