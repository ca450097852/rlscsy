package com.hontek.comm.util;

public class CodeSeq {
	public static int seq = 0;
	
	public static String getSeq(){
		seq ++;
		if(seq>10000){
			seq = 0;
		}
		
		String date = DateUtil.formatYYYMMDDHHMMSS();
		
		int t = 10000+seq;
		
		return date+(t+"").substring(1,5);
	}
}
