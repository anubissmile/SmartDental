package ldc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {
	public void redirect(HttpServletRequest request, HttpServletResponse response, String site) throws
		ServletException, IOException{
		
		response.setContentType("text/html");  
		PrintWriter pw = response.getWriter();  

		response.sendRedirect(site);  
		  
		pw.close();  
	}
}
