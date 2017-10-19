package com.hontek.sys.service.impl;

import java.util.Calendar;
import java.util.Date;

import com.hontek.comm.util.DateUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Calendar   ca   =   Calendar.getInstance();
		ca.setTime(new Date());                            //  someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH,   1);
		Date   firstDate   =   ca.getTime();		
		System.out.println(DateUtil.formatDateTime(firstDate));
		
		ca.add(Calendar.MONTH,   1);
		ca.add(Calendar.DAY_OF_MONTH,   -1);
		Date   lastDate   =   ca.getTime();
		System.out.println(DateUtil.formatDateTime(lastDate));*/
		String startDate = "";
		String endDate ="";
		
		Calendar   ca   =   Calendar.getInstance();
		ca.setTime(new Date());              
		ca.set(Calendar.MONTH, 0);
		ca.set(Calendar.DAY_OF_MONTH,   1);
		Date   firstDate   =   ca.getTime();		
		startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
		
		ca.set(Calendar.MONTH,   11);
		ca.set(Calendar.DATE,   31);
		Date   lastDate   =   ca.getTime();
		endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
		
		System.out.println(startDate);
		System.out.println(endDate);

	}

}
