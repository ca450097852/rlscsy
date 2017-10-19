package com.hontek.comm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.hontek.comm.base.ApplicationContextUtil;
import com.hontek.comm.base.GlobalValueManager;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.JsonMsg;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsLog;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.LogServiceInter;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport  {
    
    protected int             page             = 1;             //当前第几页
    protected int             rows         	   = 10;            // 每页数量
    protected String          sortname         = "";            // 排序字段
    protected String          sortorder        = "";            // 排序方式
    protected String          errorLog         = "";            //错误消息日志
    protected String		  jsonMsg		   = "";
    protected static LogServiceInter logService;
    protected String sort;
    protected String order;
    
    static{
    	logService = (LogServiceInter) ApplicationContextUtil.getContext().getBean("logService");
    }
    /**
     * 添加系统日志
     * @param funcName 功能名称
     * @param Acttype	操作类型
     */
    public void addSysLog(String funcName,String Acttype){
    	TsLog tslog = new TsLog();
    	
    	LoginUser user = getLoginUser();
    	if(user==null)
    		return;
    	
    	if(!(Acttype.equals(GlobalValueManager.LOGIN)||Acttype.equals(GlobalValueManager.EXIT))){
    		tslog.setColId(getColId());
    	}
    	tslog.setFuncName(funcName);
    	tslog.setActType(Acttype);
    	tslog.setEntId(user.getEntId());
    	tslog.setUserId(user.getUserId().toString());
    	tslog.setLogTime(DateUtil.formatDateTime());
    	tslog.setComputerIp(this.getOptionIp());
    	logService.saveLog(tslog);
    }
    /**
     * 从Cookie中获取栏目ID
     * @return
     */
    public int getColId(){
    	Cookie[] cookies = this.getRequest().getCookies();
    	int colId = 0;
    	if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				Cookie ck = cookies[i];
				if("colId".equals(ck.getName())){
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(ck.getValue());
					if(isNum.matches()){//判断是否是纯数字
						colId = Integer.parseInt(ck.getValue());
					}
				}
			}
		}
		return colId;
    }
    /**
     * 获取操作用户的IP
     * @return
     */
    public String getOptionIp(){
    	String IP = this.getRequest().getRemoteAddr();
    	
    	return IP;
    }
    
    private static final long serialVersionUID = 1L;
    
    public String getJsonMsg() {
		return jsonMsg;
	}

	public void setJsonMsg(String jsonMsg) {
		this.jsonMsg = jsonMsg;
	}

	/**
     * 返回request对象
     * @return
     * @Description:
     */
    protected HttpServletRequest getRequest () {
        return ServletActionContext.getRequest ();
    }
    
    /**
     * 返回response对象
     * @return
     * @Description:
     */
    protected HttpServletResponse getResponse () {
        return ServletActionContext.getResponse ();
    }
    
    /**
     * 返回session对象
     * @return
     * @Description:
     */
    protected HttpSession getSession () {
        return ServletActionContext.getRequest ().getSession (false);
    }
    
    /**
     * 返回用户昵称
     * @return
     */
    protected String getLoginUserNickName(){
    	LoginUser loginUser = getLoginUser();
    	return loginUser.getNickname();
    }
    
    protected String getLoginUserName(){
    	LoginUser loginUser = getLoginUser();
    	return loginUser.getUserName();
    }
    
    /**
     * 返回用户Id
     * @return
     */
    protected String getLoginUserId(){
    	
    	LoginUser loginUser = getLoginUser();
    	if(loginUser!=null){
    		return loginUser.getUserId();
    	}else{
    		return "";
    	}
    	
    }
    
    /**
     * 返回用户entId
     * @return
     */
    protected int getLoginUserEntId(){
    	LoginUser loginUser = getLoginUser();
    	return loginUser.getEntId();
    }
    
    
    /**
     * 打印Json字符串返回给客户
     * @param jsonString
     * @Description:
     */
    protected void printJsonString ( String jsonString ) {
        PrintWriter out = null;
        try {
        	HttpServletResponse response = getResponse();
        	response.setCharacterEncoding ( "UTF-8" );
            response.setContentType("text/html;charset=UTF-8");             
            out = response.getWriter ();
            out.print ( jsonString );
        }
        catch (IOException e) {
            e.printStackTrace ();
        }
        finally {
            out.close ();
        }
    }
    
    /**
     * 输出字符串 GBK编码
     * @param jsonString
     */
    protected void printStringByGBK ( String jsonString ) {
        PrintWriter out = null;
        try {
        	HttpServletResponse response = getResponse();
        	response.setCharacterEncoding ( "GBK" );
            response.setContentType("text/html;charset=GBK");             
            out = response.getWriter ();
            out.print ( new String(jsonString.getBytes(),"GBK") );
        }
        catch (IOException e) {
            e.printStackTrace ();
        }
        finally {
            out.close ();
        }
    }
    
    /**
     * 将对象转换为json字符
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
     * 将对象转换为json字符
     * @param object
     * @Description:
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
     * 返回当前登录用户对象信息
     * @return
     * @Description:
    */ 
    protected LoginUser getLoginUser () {
    	try {
    		Object obj = ServletActionContext.getRequest ().getSession ().getAttribute ( "loginUser" );
    		if(obj!=null){
    			return (LoginUser)obj;
    		}
		} catch (Exception e) {
		}
        return null; 
    }  
    
    protected TsUser getLoginTsUser () {
        return (TsUser) ServletActionContext.getRequest ().getSession ().getAttribute ( "tsUser" );
    }  
    
    
    protected CompanyUser getLoginCompanyUser () {
    	Object obj = ServletActionContext.getRequest ().getSession ().getAttribute ( "loginCompanyUser" );
    	if(obj!=null){
            return (CompanyUser) obj;
    	}
        return null;
    }  
    
    protected Company getLoginCompanyInSession () {
    	Object obj = ServletActionContext.getRequest ().getSession ().getAttribute ( "loginCompany" );
    	if(obj!=null){
            return (Company) obj;
    	}
        return null;
    }  
    
    protected boolean isAdmin(){
    	return this.getLoginUser()!=null && "y".equalsIgnoreCase(this.getLoginUser().getAdmin());
    }
    /**
     * 获取登录用户的id
     * @return
     */
    protected String getLonginUserId() {
    	 return getLoginUser().getUserId();
	}
    /**
     * 返回当前登录用户机构对象信息
     * @return
     * @Description:
    */ 
    protected TsEnterprise getEnterprse () {
        return (TsEnterprise) ServletActionContext.getRequest ().getSession ().getAttribute ( "enterprse" );
    }  
    
    public int getPage () {
        return page;
    }
    
    public void setPage ( int page ) {
        this.page = page;
    }
    
    
    public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSortname () {
        return sortname;
    }
    
    public void setSortname ( String sortname ) {
        this.sortname = sortname;
    }
    
    public String getSortorder () {
        return sortorder;
    }
    
    public void setSortorder ( String sortorder ) {
        this.sortorder = sortorder;
    }
    
    public String getErrorLog () {
        return errorLog;
    }
    
    public void setErrorLog ( String errorLog ) {
        this.errorLog = errorLog;
    }
    
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
