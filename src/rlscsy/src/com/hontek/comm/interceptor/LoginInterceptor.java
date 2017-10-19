package com.hontek.comm.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hontek.comm.base.LoginUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
 
public class LoginInterceptor extends MethodFilterInterceptor { 

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());

	protected String doIntercept(ActionInvocation actionInvocation)
			throws Exception {
		String result = "";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpSession session = request.getSession();
			LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
			
			/*Cookie[] cookies = request.getCookies();
	    	Long colId = null;
	    	if(cookies!=null){
				for(int i=0;i<cookies.length;i++){
					Cookie ck = cookies[i];
					if("colId".equals(ck.getName())){
						colId = Long.parseLong(ck.getValue());
					}
				}
	    	}*/

			if (loginUser == null) {
				if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))|| request.getParameter("ajax") != null) {
					PrintWriter out = response.getWriter();
					String jsonstr = "{\"rows\":{\"statusCode\":\"301\"}}";
					out.println(jsonstr);
					return null;
				} else {
					return "login";
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		result = actionInvocation.invoke();
		return result;
	}
}
