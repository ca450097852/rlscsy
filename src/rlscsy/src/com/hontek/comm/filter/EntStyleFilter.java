package com.hontek.comm.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hontek.comm.base.ApplicationContextUtil;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.service.inter.EntExpandServiceInter;
import com.hontek.sys.service.inter.EntStyleServiceInter;
import com.hontek.sys.service.inter.EnterpriseServiceInter;

public class EntStyleFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 根据域名获取风格
	 */
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String domain = request.getServerName();
		
		
		EntStyleServiceInter entStyleService = (EntStyleServiceInter) ApplicationContextUtil.getContext().getBean("entStyleService");
		
		//前台UI风格配置
		Object entStyle_QT = session.getAttribute("entStyle_QT");
		if(entStyle_QT==null){
			
			EntStyle entStyle = entStyleService.getEntStyleByDomain(domain,"1");
			
			String systemName = "广州市肉类蔬菜流通追溯管理平台";

			//如何为空的话自动生成一个对象给他，要不然每次都要进行匹配
			if(entStyle==null){
				entStyle = new EntStyle();
			}else{
				EnterpriseServiceInter enterpriseService = (EnterpriseServiceInter) ApplicationContextUtil.getContext().getBean("enterprseService");
				TsEnterprise enterprise = enterpriseService.getEnterPirseByEntId(entStyle.getEntId());
				if(enterprise!=null){
					session.setAttribute("entAddr", enterprise.getRegAddr());
					session.setAttribute("entPhone", enterprise.getTel());
					session.setAttribute("areaId", enterprise.getAreaId());
				}
				EntExpandServiceInter entExpandService = (EntExpandServiceInter) ApplicationContextUtil.getContext().getBean("entExpandService");
				EntExpand entExpand = entExpandService.getEntExpandByEntId(entStyle.getEntId());
				if(entExpand!=null && !"".equals(entExpand.getMbName())){
					systemName = entExpand.getMbName();
					session.setAttribute("showCode", entExpand.getShowCode());
					session.setAttribute("validCode", entExpand.getValidCode());
					
					session.setAttribute("auditPro", entExpand.getAuditPro());
					session.setAttribute("auditBatch", entExpand.getAuditBatch());
				}
			}
			
			session.setAttribute("systemName", systemName);
			
			session.setAttribute("entStyle_QT", entStyle);
			
		}
		
		//企业后台UI风格配置
		Object companyStyle = session.getAttribute("companyStyle");
		if(companyStyle==null){
			EntStyle entStyle = entStyleService.getEntStyleByDomain(domain,"2");
			
			//如何为空的话自动生成一个对象给他，要不然每次都要进行匹配
			if(entStyle==null){
				entStyle = new EntStyle();
			}

			session.setAttribute("companyStyle", entStyle);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
