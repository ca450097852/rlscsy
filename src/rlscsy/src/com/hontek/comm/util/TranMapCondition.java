package com.hontek.comm.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TranMapCondition {

	public static String TranMaptoString(Map mapCondition) {
		String condition = "";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			condition = " where 1=1 ";
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " like '%" + (String) obj+ "%'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
		}
		return condition;
	}
	
	/**
	 * 查询条件精确匹配
	 * @param mapCondition
	 * @return
	 */
	public static String TranMaptoString2(Map mapCondition) {
		String condition = "";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			condition = " where 1=1 ";
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " = '" + (String) obj
								+ "'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
		}
		return condition;
	}
	
	public static String TranMaptoString3(Map mapCondition) {
		String condition = " where 1=1 ";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " like '%" + (String) obj+ "%'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
		}
		return condition;
	}

}
