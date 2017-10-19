package com.hontek.comm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 将java.util.Date 类型字段转为json时
 * @author dream
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

	private String format ="yyyy-MM-dd";
	
	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value);
	}
	
	private Object process(Object value){		
		if(value instanceof Date){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}
}
