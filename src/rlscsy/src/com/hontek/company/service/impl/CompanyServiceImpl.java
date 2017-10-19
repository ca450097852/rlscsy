package com.hontek.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.ChineseToEnglish;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.CompanyDao;
import com.hontek.company.dao.CompanyUserDao;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.company.service.inter.CompanyServiceInter;
import com.hontek.review.pojo.TreeVo;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TsEnterprise;


/**
 * <p>Title:企业信息表</p>
 * <p>Description: 企业信息表接口实现 类</p>
 * @version 1.0
 */
public class CompanyServiceImpl extends BaseServiceImpl implements CompanyServiceInter {

	private CompanyDao companyDao;
	private CompanyUserDao companyUserDao;
	private EnterpriseDao enterpriseDao;
	
	Logger logger = Logger.getLogger(CompanyServiceImpl.class);
	
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	public void setCompanyUserDao(CompanyUserDao companyUserDao) {
		this.companyUserDao = companyUserDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}
	/**
	 * 添加企业
	 */
	public String addCompany(Company company) {
		String msg = "添加成功!";
		try {
			if(company.getArea()!=null&&!"".equals(company.getArea())){
				TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, Integer.parseInt(company.getArea()));//根据区域id获取区域实体
				if(enterprise!=null&&!"".equals(enterprise.getEntCode())){
					String entCode = enterprise.getEntCode();//区域编码
					
					int parentId = company.getParentId();
					String comType = company.getComType();
					
					String nodeCode = "";//节点编码
					String comCode = "";//经营者编码
					
					if(parentId==0){/*流通节点*/
						nodeCode = getNodeCode(entCode);//节点编码=区域码+节点备案流水号
						company.setNodeCode(nodeCode);
						company.setComId(companyDao.getTableSequence("tb_company".toUpperCase()));
						company.setRegTime(DateUtil.formatDateTime());
						company.setState("1");//使用
						companyDao.save(company);//创建企业
						
						if(company.getComType()!=null&&!"".equals(company.getComType())&&company.getNodeAnCom()!=null&&!"".equals(company.getNodeAnCom())){
							Company clonecompany =  company.clone();
							msg = addNodeCompany(clonecompany);
						}
						
					}else{/*经营者*/
						Company node = companyDao.get(Company.class, parentId);//获取上级节点
						if(node!=null){
							comCode = getComCode(node.getNodeCode());//经营者备案流水号=上级节点编码+经营者备案流水号
							company.setComCode(comCode);
							company.setComId(companyDao.getTableSequence("tb_company".toUpperCase()));
							company.setRegTime(DateUtil.formatDateTime());
							company.setState("1");//使用
							companyDao.save(company);//创建企业
							
							
							CompanyUser comUser = new CompanyUser(
									companyUserDao.getTableSequence("tb_company_user".toUpperCase()),
									company.getComId(),
									(company.getAccount()==null||"".equals(company.getAccount()))?(company.getUserName()==null?ChineseToEnglish.getAllFirstLetter(company.getName()):company.getUserName()):company.getAccount(),
									(company.getPassword()==null||"".equals(company.getPassword()))?"rc0000":company.getPassword(),
									company.getName(),
									company.getPhone(),
									"1",
									"1",
									DateUtil.formatDateTime()
									);
							companyUserDao.save(comUser);//创建企业会员
						}else{
							msg = "添加失败!无此节点！";
						}
					}
				}
				
				
			}else{
				msg = "添加失败!区域不能为空！";
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	/**
	 * 添加节点的企业
	 */
	public String addNodeCompany(Company company) {
		String msg = "操作成功!";
		int pid = company.getComId();
		company.setComId(companyDao.getTableSequence("tb_company".toUpperCase()));
		company.setParentId(pid);//节点id
		company.setRegTime(DateUtil.formatDateTime());
		company.setComCode(company.getNodeCode()+"0000");//流通节点+经营者的编码=节点编码+0000
		company.setFlag("");
		company.setNodeCode("");
		companyDao.save(company);//创建企业
		
		String userAccount = (company.getAccount()==null||"".equals(company.getAccount()))?(company.getUserName()==null?ChineseToEnglish.getAllFirstLetter(company.getName()):company.getUserName()):company.getAccount();
		String userPassword = (company.getPassword()==null||"".equals(company.getPassword()))?"rc0000":company.getPassword();
		CompanyUser comUser = new CompanyUser(
				companyUserDao.getTableSequence("tb_company_user".toUpperCase()),
				company.getComId(),
				userAccount,
				userPassword,
				company.getName(),
				company.getPhone(),
				"1",
				"1",
				DateUtil.formatDateTime()
				);
		companyUserDao.save(comUser);//创建企业会员
		
		msg = "操作成功!您的账号为："+userAccount+"，密码为："+userPassword;
		
		return msg;
	}
	

	/**
	 * 注册企业
	 */
	public String addCompany2(Company company) {
		String msg = "注册成功!";
		try {
			boolean namef = companyDao.findNameIsUnique(company.getName());
			if(namef){
				 msg = "-2";//该企业已被注册
			}else{
				boolean accountf =companyUserDao.findAccountIsUnique(company.getAccount());
				if(accountf){
					msg = "-1";//该账号已被注册
				}else{
					
					if(!"".equals(company.getArea())){
						
						TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, Integer.parseInt(company.getArea()));//根据区域id获取区域实体
						if(enterprise!=null&&!"".equals(enterprise.getEntCode())){
							String entCode = enterprise.getEntCode();//区域编码
							int parentId = company.getParentId();
							String comType = company.getComType();
							
							String nodeCode = "";//节点编码
							String comCode = "";//经营者编码
							
							if(parentId==0){/*流通节点*/
								nodeCode = getNodeCode(entCode);//节点编码=6位区域码+3位节点备案流水号（节点码共9位）
								company.setNodeCode(nodeCode);
								company.setComId(companyDao.getTableSequence("tb_company".toUpperCase()));
								company.setRegTime(DateUtil.formatDateTime());
								company.setState("1");//使用
								companyDao.save(company);//创建企业
								
								if(company.getComType()!=null&&!"".equals(company.getComType())&&company.getNodeAnCom()!=null&&!"".equals(company.getNodeAnCom())){
									Company clonecompany =  company.clone();
									msg = addNodeCompany(clonecompany);
								}
								
							}else{/*经营者*/
								Company node = companyDao.get(Company.class, parentId);//获取上级节点
								if(node!=null){
									comCode = getComCode(node.getNodeCode());//经营者备案流水号=上级节点编码+经营者备案流水号（经营者码共13位）
									company.setComCode(comCode);
									company.setComId(companyDao.getTableSequence("tb_company".toUpperCase()));
									company.setRegTime(DateUtil.formatDateTime());
									company.setState("1");//使用
									companyDao.save(company);//创建企业
									
									String userAccount = (company.getAccount()==null||"".equals(company.getAccount()))?(company.getUserName()==null?ChineseToEnglish.getAllFirstLetter(company.getName()):company.getUserName()):company.getAccount();
									String userPassword = (company.getPassword()==null||"".equals(company.getPassword()))?"rc0000":company.getPassword();
									CompanyUser comUser = new CompanyUser(
											companyUserDao.getTableSequence("tb_company_user".toUpperCase()),
											company.getComId(),
											userAccount,
											userPassword,
											company.getName(),
											company.getPhone(),
											"1",
											"1",
											DateUtil.formatDateTime()
											);
									companyUserDao.save(comUser);//创建企业会员
									
									msg = "注册成功!您的账号为："+userAccount+"，密码为："+userPassword;
									
								}
							}
						}
						
					}
				}
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	/**
	 * 根据区域编码获取，节点备案流水号
	 * @param areaCode
	 * @return
	 */
	public String getNodeCode(String areaCode){
		int n = companyDao.getTableSequence("tb_company_node_"+areaCode);//节点备案流水号
		int length  = String.valueOf(Math.abs(n)).length();
		String basestr = "000";
		String mstr = "";
		if((3-length)>0){
			mstr = basestr.substring(0, (3-length));
		}
		return areaCode+mstr+n;
	}
	
	/**
	 * 根据节点编码获取，经营者备案流水号
	 * @param nodeCode
	 * @return
	 */
	public String getComCode(String nodeCode){
		int n = companyDao.getTableSequence("tb_company_type_"+nodeCode);//经营者备案流水号
		int length  = String.valueOf(Math.abs(n)).length();
		String basestr = "0000";
		String mstr = "";
		if((4-length)>0){
			mstr = basestr.substring(0, (4-length));
		}
		return nodeCode+mstr+n;
	}
	

	/**
	 * 删除企业
	 */
	public String deleteCompany(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Company company = companyDao.get(Company.class, Integer.valueOf(idArray[i]));
					if(company!=null){
						companyDao.delete(company);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	

	/**
	 * 发布企业
	 */
	public String updatePublishCompany(String ids) {
		String msg = "发布企业成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Company company = companyDao.get(Company.class, Integer.valueOf(idArray[i]));
					if(company!=null){
						company.setState("1");
						companyDao.update(company);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "发布企业出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	
	/**
	 * 分页查询企业
	 */
	public String findCompanyPagerList(Company company,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(company!=null){
				String name = company.getName();//产品情况
				if(name!=null&&!"".equals(name)){
					condition.append(" and name like '%"+name+"%' ");
				}
				if(company.getFlag()!=null&&!"".equals(company.getFlag())){
					condition.append(" and flag = '"+company.getFlag()+"' ");
				}
				if(company.getState()!=null&&!"".equals(company.getState())){
					condition.append(" and state = '"+company.getState()+"' ");
				}
				if(company.getParentId()==0){
					condition.append(" and parentId = 0 ");
				}
				if(company.getParentId()==-1){
					condition.append(" and parentId <> 0 ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<Company>  pager = companyDao.findPager(Company.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询企业出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	
	
	/**
	 * 分页查询企业
	 */
	public String findPagerList(Company company,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			Pager<Company>  pager = companyDao.findPager(company, page, rows, sort, order);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询企业出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}
	
	/**
	 * 修改企业
	 */
	public String updateCompany(Company company) {
		String msg = "修改成功!";
		try {
			companyDao.update(company);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	/**
	 * 修改企业logo
	 */
	public String updateCompanyLogo(Company company) {
		String msg = "上传成功!";
		try {
			String logo=company.getComLogo();
			company=companyDao.get(Company.class, company.getComId());
			company.setComLogo(logo);
			companyDao.update(company);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "上传出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	public String updateCompanyState(String ids,String state){
		String msg = "设置成功!";
		try {
			if(ids!=null&&!"".equals(ids)&&!"".equals(state)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Company company = companyDao.get(Company.class, Integer.valueOf(idArray[i]));
					if(company!=null){
						company.setState(state);
						companyDao.update(company);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String getCompanyToSelect(String area,String flag){
		String jsonString = "";
		try {
			List<Company> list = companyDao.getCompanyToSelect(area);
			List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
			
			if(flag!=null&&!"".equals(flag)){
				Map<String, Object> map0 = new HashMap<String, Object>();
				map0.put("id", "");
				map0.put("text", "--请选择--");
				listResults.add(map0);
			}
			
			for (Company com : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", com.getComId());
				map.put("text", com.getName());
				listResults.add(map);
			}
			
			jsonString = getJSON(listResults);
		} catch (Exception e) {
			logger.error("查询流通节点下拉：" + e.getMessage());
		}
		return jsonString;
	}
	
	
	/**
	 * 根据comId主键获取
	 * @param comId
	 * @return Company
	 */
	public Company getCompanyByComId(int comId){
		return companyDao.get(Company.class, comId);
	}
	
	/**
	 * @param company
	 * @return
	 */
	public List<Company> findList(Company company){
		List<Company> list = companyDao.findList(company);
		return list;
	}
	
	/**
	 * 查询名称是否存在
	 */
	public String findNameIsUnique(String name){
		boolean f = companyDao.findNameIsUnique(name);
		return String.valueOf(f);
	}
	
	
	public Company getNodeByComId(int comId) {
		return companyDao.getNodeObject(comId);
	}
	
	
	/**
	 * 根据企业comCode获取
	 * @param comCode
	 * @return
	 */
	public Company getCompanyByComCode(String comCode){
		Company result = null;
		/*方案1*/
		if(comCode!=null&&!"".equals(comCode)){
			result = companyDao.get(" from Company where comCode = '"+comCode+"'");
		}
		/*方案2
		Company param = new Company();
		param.setComCode(comCode);
		List<Company> list = companyDao.findList(param);
		if(!list.isEmpty()){
			result = list.get(0);
		}*/
		return result;
	}
	public String findNodeCompanyList() {
		
		String [] node = {"屠宰企业","批发企业","菜市场","超市","团体消费单位","其他"};
		TreeVo top = new TreeVo();
		top.setId(0);
		top.setText("流通节点");
		List<TreeVo> children = new ArrayList<TreeVo>();
		
		for (int i = 0; i < node.length; i++) {
			TreeVo treeVo = new TreeVo();
			treeVo.setId(i+1);
			treeVo.setText(node[i]);
			
			Company company = new Company();
			company.setFlag(String.valueOf(i+1));
			company.setParentId(0);
			
			List<Company> list  = companyDao.findList(company);
			if(list!=null&&!list.isEmpty()){
				List<TreeVo> children2 = new ArrayList<TreeVo>();
				for (Company company2 : list) {				
					TreeVo treeVo2 = new TreeVo();
					treeVo2.setId(company2.getComId());
					treeVo2.setText(company2.getName());
					JSONObject attributes = new JSONObject();
					attributes.put("isNode", true);
					treeVo2.setAttributes(attributes);
					children2.add(treeVo2);
				}
				treeVo.setChildren(children2);
			}

			children.add(treeVo);
		}		
		top.setChildren(children);

		List<TreeVo> topVo = new ArrayList<TreeVo>();
		topVo.add(top);

		return getJSON(topVo);
	}
	public String findCompanyAreaName(int comId) {
		
		return companyDao.findCompanyAreaName(comId);
	}
}
