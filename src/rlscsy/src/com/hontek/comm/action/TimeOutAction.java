package com.hontek.comm.action;

import com.opensymphony.xwork2.ActionSupport;

public class TimeOutAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String ajaxSessionTimeout() {
		return "timeout"; 
	}
}
