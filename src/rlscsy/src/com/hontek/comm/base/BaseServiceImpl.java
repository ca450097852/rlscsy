package com.hontek.comm.base;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hontek.comm.util.*;
import com.hontek.sys.pojo.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * <p>Title: </p>
 * <p>Description: Service 实现类基类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class BaseServiceImpl {

	/**
	 * 将排序条件封装
	 * @param sort
	 * @param order
	 * @return
	 */
	public String sortCondtion(String sort,String order){
		StringBuffer condition = new StringBuffer(""); 
		//注意sort和order的顺序
		if(sort!=null&&!"".equals(sort)&&order!=null&&!"".equals(order)){
			condition.append(" order by ");
			String [] sortArray = sort.split(",");
			String [] orderArray = order.split(",");
			for (int i = 0; i < orderArray.length; i++) {
				condition.append(sortArray[i]);
				condition.append(" ");
				condition.append(orderArray[i]);
				if(i<orderArray.length-1){
					condition.append(", ");
				}
			}			
		}
		return condition.toString();
	}
	
	/**
	 * 将排序条件封装
	 * @param sort
	 * @param order
	 * @param defalutSort 默认排序
	 * @return
	 */
	public String sortCondtion(String sort,String order,String defalutSort){
		StringBuffer condition = new StringBuffer(" order by "); 
		//注意sort和order的顺序
		if(sort!=null&&!"".equals(sort)&&order!=null&&!"".equals(order)){
			String [] sortArray = sort.split(",");
			String [] orderArray = order.split(",");
			for (int i = 0; i < orderArray.length; i++) {
				condition.append(sortArray[i]);
				condition.append(" ");
				condition.append(orderArray[i]);
				if(i<orderArray.length-1){
					condition.append(", ");
				}
			}			
		}else{
			condition.append(defalutSort);
		}
		return condition.toString();
	}
	
	
    /**
     * 将对象列表转换为json字符
     * @param object
     * @Description:
     */
    protected String getJSON( Object object ) {
    	//过滤关联
    	/*JsonConfig config = new JsonConfig();
    	config.setJsonPropertyFilter(new PropertyFilter() {			
			public boolean apply(Object source, String name, Object value) {
				if(name.equalsIgnoreCase("tbProduct")||
						name.equalsIgnoreCase("tsEnterprise")||
						name.equalsIgnoreCase("tsUser")||
						name.equalsIgnoreCase("auditorUser")){
					return true;
				}else{
					return false;
				}
			}
		});
    	
    	config.setExcludes(new String[]{"tbProduct","tsEnterprise","tsUser","auditorUser"});
    	config.setIgnoreDefaultExcludes(false);
    	config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);*/
    	
        return JSONArray.fromObject ( object ).toString ();
    }
    
    /**
     * 对象转JSON
     * @param object
     * @return
     */
    protected String getObjectJSON( Object object ) {
        return JSONObject.fromObject ( object ).toString ();

    }
    
    /**
     * 根据Pager对象，生成EasyUI
     * @param pager
     * @return
     */
    protected String createEasyUiJson(Pager pager){
    	String jsonstr = "{\"total\":" + pager.getTotal() + ",\"rows\":"+ getJSON(pager.getData()) + "}";
    	return jsonstr;
    }
    
    
    /**
     * 将对象转换为json字符
     * @param object
     * @Description:
     */
    protected String getDateJSON( Object object ) {
    	JsonConfig config = new JsonConfig();
    	config.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());    	
        return JSONArray.fromObject ( object ,config ).toString ();
    }
    /**
     * 根据Pager对象，生成EasyUI
     * 属性有日期类型时调用此方法
     * @param pager
     * @return
     */
    protected String createEasyUiJsonWithDateProperty(Pager pager){
    	String jsonstr = "{\"total\":" + pager.getTotal() + ",\"rows\":"+ getDateJSON(pager.getData()) + "}";
    	return jsonstr;
    }
    
    /**
     * 下拉转换
     * @param list
     * @return
     */
    public String getSelectOptions(List<TreeVo> list){
    	StringBuffer buffer = new StringBuffer("");
    	for (TreeVo treeVo : list) {
    		buffer.append("<option value='");
    		buffer.append(treeVo.getId());
    		buffer.append("'>");
    		buffer.append(treeVo.getText());
    		buffer.append("</option>");
		}
    	return buffer.toString();
    }
    
    /**
     * 验证手机号码是否正确
     * @param mobile
     * @return
     */
    public boolean matchMobile(String mobile){  	
    	Pattern p = Pattern.compile("^[1][34578]\\d{9}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
    
    /**
     * 字符集转换
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public String convertString(String str) throws UnsupportedEncodingException{
    	if(str==null||"".equals(str)){
    		return "";
    	}else{
    		return new String(str.toString().getBytes("GBK"),"iso8859-1");
    	}
    }
    
	
	public  static String getPrefixNum(int length,String value){
		while(value.length()<length){
			value = "0"+value;
		}
		return value;
	}
}
