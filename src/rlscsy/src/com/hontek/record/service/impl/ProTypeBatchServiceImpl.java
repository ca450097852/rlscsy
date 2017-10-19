package com.hontek.record.service.impl;

import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.CreateImg;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.CompanyDao;
import com.hontek.company.pojo.Company;
import com.hontek.record.dao.ProTypeBatchDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.dao.RecordDao;
import com.hontek.record.pojo.Combo;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.pojo.TbRecord;
import com.hontek.record.service.inter.ProTypeBatchServiceInter;
import com.hontek.sys.pojo.ComboboxData;
import com.hontek.sys.pojo.TsUser;
/**
 * 批次信息实现类
 *
 */
public class ProTypeBatchServiceImpl extends BaseServiceImpl implements ProTypeBatchServiceInter{
	private ProTypeBatchDao proTypeBatchDao;
	private ProTypeQrcodeDao proTypeQrcodeDao;
	private RecordDao recordDao;
	
	private CompanyDao companyDao;
	
	public void setProTypeBatchDao(ProTypeBatchDao proTypeBatchDao) {
		this.proTypeBatchDao = proTypeBatchDao;
	}

	public void setProTypeQrcodeDao(ProTypeQrcodeDao proTypeQrcodeDao) {
		this.proTypeQrcodeDao = proTypeQrcodeDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	ResourceBundle bundle = ResourceBundle.getBundle("dimenno");		

	//log4j 日志处理
	Logger logger = Logger.getLogger(ProTypeBatchServiceImpl.class);

	/**
	 * 添加批次
	 */
	public String addProTypeBatch(TbProTypeBatch proTypeBatch,TsUser user,HttpServletRequest request) {

		String jsonMsg = "添加批次成功!";
		try{
			TbProTypeQrcode proTypeQrcode = null;
			if(proTypeBatch.getPtqId()!=null){
				proTypeQrcode = proTypeQrcodeDao.get(TbProTypeQrcode.class, proTypeBatch.getPtqId());
			}else{
				return "参数错误";
			}
			if(proTypeQrcode==null){
				return "参数错误";
			}		
			//String dimenno = proTypeQrcode.getDimenno();			
			String nodeCode = companyDao.getNodeCode(proTypeQrcode.getEntId());			
			
			Company company = companyDao.findCompany(proTypeQrcode.getEntId());
			
			String MaxDimenno =  proTypeBatchDao.getMaxDimenno(nodeCode);
			String dimenno = "";
			if(MaxDimenno==null){
				dimenno = nodeCode+"0000001";
			}else{				
				int temp2 = Integer.valueOf(MaxDimenno.substring(9))+1;																
				dimenno = nodeCode+getPrefixNum(7, String.valueOf(temp2));	
			}
			proTypeBatch.setDimenno(dimenno);
			proTypeBatch.setCodeImg(dimenno+".png");
			
			Properties prop = new Properties();
			prop.load(this.getClass().getClassLoader().getResourceAsStream("dimenno.properties"));
			
			String url = prop.getProperty("url")+dimenno;
			proTypeBatch.setUrl(url);
			
			proTypeBatch.setPtbId(proTypeBatchDao.getTableSequence("tb_pro_type_batch"));
			this.proTypeBatchDao.save(proTypeBatch);	
			
			createDimennoImg(request, dimenno, proTypeQrcode.getProName(),company.getName(), company.getPhone(), "");
			
			//添加批准档案
			/*TbRecord record = new TbRecord();
			record.setRecId(recordDao.getTableSequence("TB_RECORD"));
			record.setRecName(proTypeBatch.getBatchName()+"档案");
			record.setCrttime(DateUtil.formatDateTime());
			record.setSeq(5);
			record.setCrttime(user.getUserId());
			record.setRecSts("1");
			record.setObjId(proTypeBatch.getPtbId());
			record.setObjTypeId(5);			
			recordDao.save(record);*/
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加批次失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;			
		
	}

	public String deleteProTypeBatch(String ids) {
		String jsonMsg ="删除失败!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (String id : idArray) {
					TbProTypeBatch proTypeBatch = proTypeBatchDao.get(TbProTypeBatch.class, Integer.valueOf(id));
					if(proTypeBatch!=null){
						proTypeBatchDao.delete(proTypeBatch);										
						jsonMsg ="删除成功!";
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String findPtBIdByProTypeBatch( int ptbId) {
		String ptb ="";
		if(ptbId!=0){
			
			List<Integer> ptbList =proTypeBatchDao.findPtBIdByProTypeBatch(ptbId);
			if(ptbList.size()>0){
				ptb=ptbList.get(0)+"";
				
			}
			
		}
		return ptb;
	}
	
	public String findPtBIdByProOut( int ptbId) {
		String ptb ="";
		if(ptbId!=0){
			
			List<Integer> ptbList =proTypeBatchDao.findPtBIdByProOut(ptbId);
			if(ptbList.size()>0){
				ptb=ptbList.get(0)+"";
				
			}
			
		}
		return ptb;
	}
	
	public String findDimenNoByPtBId(int ptbId){
		String dimenNo ="";
		if(ptbId!=0){
			
			List<String> ptbList =proTypeBatchDao.findDimenNoByPtBId(ptbId);
			if(ptbList.size()>0){
				dimenNo=ptbList.get(0)+"";
				
			}
			
		}
		return dimenNo;
	}
	
	
	public String findProTypeBatch(TbProTypeBatch proTypeBatch,int page, int rows, String order, String sort) {
		String jsonMsg ="0";
		if(proTypeBatch!=null){

			Pager<TbProTypeBatch> pager =proTypeBatchDao.findProTypeBatch(proTypeBatch, page, rows);
						
			jsonMsg =createEasyUiJson(pager);
		}
		return jsonMsg;
	}
	
	public List<TbProTypeBatch> findProTypeBatchList(TbProTypeBatch proTypeBatch){
		int ptqId = proTypeBatch.getPtqId();
		List<TbProTypeBatch> list = proTypeBatchDao.findProTypeBatchList(ptqId);
		return list;
	}
	
	
	public String updateProTypeBatch(TbProTypeBatch proTypeBatch) {
		String msg = "修改成功!";
		try {
			proTypeBatchDao.update(proTypeBatch);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败!"+e.getMessage();
		}		
		return msg;
	}

	public String updateProTypeBatchState(TbProTypeBatch proTypeBatch) {
		String msg = "审核成功!";
		try {
			 int ptbId = proTypeBatch.getPtbId();
			if(ptbId>0){
				
				TbProTypeBatch batch = proTypeBatchDao.get(TbProTypeBatch.class, ptbId);			
				batch.setBatchState(proTypeBatch.getBatchState());			
				 proTypeBatchDao.update(batch);

			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			msg = "审核失败!"+e.getMessage();
		}		
		return msg;
	}
	
	
	public String getCombobox(String ids) {
		if(ids!=null && !"".equals(ids)){
			List<ComboboxData> list = proTypeBatchDao.getCombobox(ids);
			return this.getDateJSON(list);
		}
		return null;
	}

	public String updateBatchState(int ptbId) {
		
		String msg = "设置成功";
		
		try {
			String hql = "update TbProTypeBatch set submitTime='"+DateUtil.formatDateTime()+"' , batchState = '1' where ptbId = " + ptbId;
			proTypeBatchDao.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "设置失败";
		}
		
		return msg;
	}
	
	
	public TbProTypeBatch getProTypeBatchByDimenno(String dimenno){
		TbProTypeBatch res = null;
		List<TbProTypeBatch> list = proTypeBatchDao.findListByTerm(" and t1.dimenno ='"+dimenno+"'");
		if(list!=null&&list.size()>0){
			res = list.get(0);
		}
		return res;
	}
	
	
	/**
	 * 根据分类二维码id查找批次列表
	 * @param ptbId
	 * @return
	 */
	public List<TbProTypeBatch> findProTypeBatchByPtqId(int ptbId){
		List<TbProTypeBatch> list = proTypeBatchDao.findListByTerm(" and t1.ptq_id ="+ptbId);
		return list;
	}
	
	
	private String createDimennoImg(HttpServletRequest request,String dimenno,String typeName,String companyName,String phone,String area){
		
		String path = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode";	
		String url=bundle.getString("url");
		
		String checkUrl = bundle.getString("checkUrl");
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		// 产品信息
		String code =dimenno; // 二维码
		
		String codeMaker = companyName;
		codeMaker=codeMaker==null?"":codeMaker;			
		String long_codeMaker="";  // 用于保存截取后的剩余长度.
		if(codeMaker!=null&&codeMaker.length()>13){	
			long_codeMaker=codeMaker.substring(13);
			codeMaker=codeMaker.substring(0,13);					
		}
		
		String proname = typeName;
		proname=proname==null?"":proname;
							
		String encoderContent =url+code;
		
		// 生成二维码图片的名字： 二维码.png
		String imgName1 = code+".png";
		String imgPath1 = imgPath+path+imgName1;		
		
		String imgName2 = code+"_2.png";
		String imgPath2 = imgPath+path+imgName2;	
		
		String imgName3 = code+"_3.png";
		String imgPath3 = imgPath+path+imgName3;
		       
        String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"logo.png";
        
		try {
			int width = 200;
			int height = width;
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);					
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath2, logo_path, logoConfig);		
			//BitMatrix matrix800 = MatrixToImageWriterEx.createQRCode(encoderContent, 800, 800);
			//MatrixToImageWriterEx.writeToFile(matrix800, "png", imgPath3, logo_path, logoConfig);			
		} catch (Exception e) {
			e.printStackTrace();
		}
										
		// x.w. 第二步：生成打印二维码 
		// 调用生成的图 类
		CreateImg cg = new CreateImg();
		String login_img = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"code_top.jpg";
		String codeImg = cg.CreateImg_record(login_img,typeName,companyName,dimenno,phone,area,imgPath1);
		
		return url+code;
	}

	public String getProTypeBatchConut(String ids) {
		String sql = "select count(*) from tb_pro_type_batch where ent_id="+ids;
		Integer integer = proTypeBatchDao.countBySql(sql);	
		return String.valueOf(integer);
	}

	
}
