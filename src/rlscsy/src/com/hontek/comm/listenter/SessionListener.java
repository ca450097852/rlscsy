package com.hontek.comm.listenter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hontek.comm.base.LoginUser;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {

	}

	/*
	 * session超时或者调用.invalidate () ;时候触发.
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		LoginUser loginUser = (LoginUser) arg0.getSession().getAttribute(
				"loginUser");
		if (loginUser != null) {
			arg0.getSession().setAttribute("loginUser", null);
		}
	}
}
