package com.hontek.comm.base;
/**
 * 全局变量管理
 * @author LBBMNM
 *
 */
public interface GlobalValueManager {
	
	/**
	 * 待提交
	 */
	public final static String RSTS_DTJ = "0";
	/**
	 * 待审
	 */
	public final static String RSTS_DS = "1";

	/**
	 * 暂停
	 */
	public final static String RSTS_STOP = "2";
	
	/**
	 * 不通过 /退回
	 */
	public final static String RSTS_NO = "3";
	
	/**
	 * 通过 
	 */
	public final static String RSTS_OK = "4";

	/**
	 * 登陆
	 */
	public final static String LOGIN = "5";
	
	/**
	 * 退出
	 */
	public final static String EXIT = "6";

}
