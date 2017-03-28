package ldc.util;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.smict.auth.AuthModel;

public class Auth {

	/**
	 * Checking whether user was logged in already or not.
	 * @author anubissmile
	 * @param Boolean true : It will redirect to /home | false : do nothings.
	 * @return void
	 */
	public static void authCheck(Boolean active){
		String site = null;
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(session.getAttribute("userSession") == null){
			site = "authenticate";
		}else{
			site = "home";
		}

		/**
		 * REDIRECT
		 */
		if(active || (site == "authenticate")){
			Servlet serve = new Servlet();
			try {
				serve.redirect(request, response, site);
			} catch (ServletException e) {
//				System.out.println("ServletException");
				e.printStackTrace();
			} catch (IOException e) {
//				System.out.println("IOException");
				e.printStackTrace();
			}
		}
		
	} // end authCheck();
	
	@SuppressWarnings("unchecked")
	public static AuthModel user(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		HashMap<String, AuthModel> userSession = new HashMap<String, AuthModel>();
		AuthModel authModel = new AuthModel();
		userSession = (HashMap<String, AuthModel>) session.getAttribute("userSession");
		authModel = userSession.get("userEmployee");
		return authModel;
	}// end user();
}
