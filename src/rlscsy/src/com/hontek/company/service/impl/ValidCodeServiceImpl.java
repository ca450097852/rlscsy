package com.hontek.company.service.impl;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.ImageUtil;
import com.hontek.comm.util.ZipUtil;
import com.hontek.company.dao.ValidCodeDao;
import com.hontek.company.dao.ValidCodeRecordDao;
import com.hontek.company.pojo.ValidCode;
import com.hontek.company.pojo.ValidCodeRecord;
import com.hontek.company.service.inter.ValidCodeServiceInter;
import com.hontek.record.dao.ProTypeBatchDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.dao.RecordDao;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.sys.dao.EntExpandDao;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 二维码验证表
 * @author IMZK
 *
 */
public class ValidCodeServiceImpl extends BaseServiceImpl implements ValidCodeServiceInter {
	private ValidCodeDao validCodeDao;
	private RecordDao recordDao;
	private ProTypeQrcodeDao proTypeQrcodeDao;
	private ProTypeBatchDao proTypeBatchDao;
	private EntExpandDao entExpandDao;
	private EnterpriseDao enterpriseDao;
	private ValidCodeRecordDao validCodeRecordDao;

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	public void setEntExpandDao(EntExpandDao entExpandDao) {
		this.entExpandDao = entExpandDao;
	}

	public void setValidCodeDao(ValidCodeDao validCodeDao) {
		this.validCodeDao = validCodeDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public void setProTypeQrcodeDao(ProTypeQrcodeDao proTypeQrcodeDao) {
		this.proTypeQrcodeDao = proTypeQrcodeDao;
	}

	public void setProTypeBatchDao(ProTypeBatchDao proTypeBatchDao) {
		this.proTypeBatchDao = proTypeBatchDao;
	}

	public void setValidCodeRecordDao(ValidCodeRecordDao validCodeRecordDao) {
		this.validCodeRecordDao = validCodeRecordDao;
	}

	/**
	 * 问题一：
	 * 存在隐患：产生四位随机码的时候如果产生的数量差不多达到的时候，重复率会比较大，这样循环产生随时码的时候可能会循环很多次，性能不高。严重可能导致内存溢出
	 * 解决方法：将四位的随机码全部生成放到一张表中，每次要批量得到随机码时可以通过sql得到未被使用的随机码
	 * 问题二：
	 * 存在隐患：产生二维码图片比较耗时，如果要生成的图片数量多的话。会使链接超时
	 * 解决方法：搞一张任务表，每次用户提交生成的时候只是生成一条任务。然后后台线程去生成图片
	 * 目前的解决方法是每次只准用户生成3000张图片
	 */
	public String addValidCode(String objId, String typeId, int count,int entId,
			HttpServletRequest request, HttpServletResponse response) {
		String msg = "fail";
		
		try {
			
			int objTypeId = Integer.parseInt(typeId);
			//获取主体域名
			TsEnterprise enterprise = enterpriseDao.getEnterPirseByEntId(entId);
			EntExpand entexpand = entExpandDao.getEntExpandEntId(enterprise.getBodyEntId());
			if(entexpand!=null){
				String dimenno = null;
				if(objTypeId==1){//主休档案
					if(enterprise!=null){
						dimenno = enterprise.getEntCode();
					}
				}else if(objTypeId==2 || objTypeId==3 || objTypeId==4){
					TbProTypeQrcode qr = proTypeQrcodeDao.get("from TbProTypeQrcode where ptqId="+objId);
					if(qr!=null)
						dimenno = qr.getDimenno();
				}else if(objTypeId==5){//批次
					TbProTypeBatch batch = proTypeBatchDao.get("from TbProTypeBatch where ptbId="+objId);
					if(batch!=null)
						dimenno = batch.getDimenno();
				}
				
				if(dimenno!=null && !"".equals(dimenno)){
					String url = "http://"+entexpand.getMbDomain()+"/lvdunwang/trace.jsp?code="+dimenno;
					
					ValidCodeRecord validCodeRecord = new ValidCodeRecord();					
					validCodeRecord.setVrId(validCodeRecordDao.getTableSequence("tb_valid_Code_Record".toUpperCase()));
					validCodeRecord.setCreatTime(DateUtil.formatDateTime());
					validCodeRecordDao.save(validCodeRecord);								
					
					//产生
					List<String> codes = this.createRandCode(dimenno,count);
					File zipFile = createImg(request,dimenno,url,codes);
					if(zipFile!=null){//图片生成成功
						String validDate = DateUtil.formatDateTime();
						for(String vcode :codes){
							ValidCode vc = new ValidCode();
							vc.setVcId(validCodeDao.getTableSequence("tb_valid_code".toUpperCase()));
							vc.setDimenno(dimenno);
							vc.setValidCode(vcode);
							vc.setValidDate(validDate);
							vc.setValidState("1");
							validCodeDao.save(vc);
						}
						msg = "/nytsyFiles/qrcode_temp/"+zipFile.getName();
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return msg;
	}
	
	/**
	 * 根据传过来的数量产生相应的4位随机码
	 * @param count
	 * @return
	 */
	private List<String> createRandCode(String dimenno,int count){
		String[] randCodes = {"0","1","2","3","4","5","6","7","8","9","Q","W","E","R","T","Y","U","I","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"}; 
		int code_sum = randCodes.length * randCodes.length * randCodes.length * randCodes.length;//四位随机码最多能有多少种组合
		if(count>0 || count < code_sum){
			int now_sum = validCodeDao.countByCode(dimenno);
			if(now_sum+count < code_sum){
				
				List<String> list = new ArrayList<String>();
				
				Set<String> codeSet = validCodeDao.getCodesByDimenno(dimenno);
				
				Random random = new Random(System.currentTimeMillis());
				
				
				while(list.size()!=count){
					
					StringBuffer bufCode = new StringBuffer();
					bufCode.append(randCodes[random.nextInt(randCodes.length)])
							.append(randCodes[random.nextInt(randCodes.length)])
							.append(randCodes[random.nextInt(randCodes.length)])
							.append(randCodes[random.nextInt(randCodes.length)]);
					
					if(codeSet.isEmpty() || !codeSet.contains(bufCode.toString())){
						list.add(bufCode.toString());
						codeSet.add(bufCode.toString());
					}
					
				}
				
				
				return list;
			}
			
		}
		
		return null;
	}
	
	
	public File createImg(HttpServletRequest request,String code,String qrcontent,List<String> codes) throws IOException{
		
		if(codes==null || codes.isEmpty())
			return null;
		
		int width = 100;
		int height = 100;
		
		String path = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode_temp";
		String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"logo.png";
		
		String main_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"main.png";
		
//	File dimennoFile = new File(imgPath+path+enterprise.getEntCode()+".png");
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		String imgName1 = code+".png";
		String imgPath1 = imgPath+path+imgName1;
		String imgPath_output = imgPath+path+code+path;
		File outFile = new File(imgPath_output);
		if(!outFile.exists()){
			outFile.mkdirs();
		}
		
		
		MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
		BitMatrix matrix = MatrixToImageWriterEx.createQRCode(qrcontent, width, height);
		MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);
		
		//加了失败重试后，防止让线程进行死循环
		int errct = 0;//失败重试次数，如果超过5次失败的话跳过
		int errsum = 0;//总的失败次数，超过150次的话终止循环，返回NULL
		for(int i=0;i<codes.size();i++){
			try {
				errct = 0;
				String vcode = codes.get(i);
				ImageUtil.composePic(main_path,imgPath1,imgPath_output+vcode+".png",70,63,vcode,100, 190); 
			} catch (Exception e) {
				e.printStackTrace();
				errsum++;
				errct++;
				
				if(errsum>150)
					return null;
				
				if(errct<=5)
					i--;//失败重试
				
			}
		}
		
		//压缩文件 
		File zipfile = ZipUtil.zip(imgPath_output);
		
		//删除文件 
		File dFile = new File(imgPath1);
		deleteAllFilesOfDir(dFile);
		
		dFile = new File(imgPath_output);
		deleteAllFilesOfDir(dFile);
			
		return zipfile;
	}
	
	private void deleteAllFilesOfDir(File path) {  
	    if (!path.exists())  
	        return;  
	    if (path.isFile()) {  
	        path.delete();  
	        return;  
	    }  
	    File[] files = path.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        deleteAllFilesOfDir(files[i]);  
	    }  
	    path.delete();  
	}

	public String updateValidCode(ValidCode validCode) {
		String msg = "参数缺失";
		if(validCode!=null && validCode.getDimenno()!=null && validCode.getValidCode()!= null){
			try {
				ValidCode vc = validCodeDao.get("from ValidCode where dimenno='"+validCode.getDimenno()+"' and validCode='"+validCode.getValidCode().toUpperCase()+"'");
				if(vc!=null){
					if("1".equals(vc.getValidState())){
						vc.setValidState("2");
						vc.setValidDate(DateUtil.formatDateTime());
						vc.setValidUserr(validCode.getValidUserr());
						
						msg = "验证通过，您可以放心使用，多谢您的使用。";
					}else{
						msg = "该验证码已经于"+vc.getValidDate()+"被验证过了。";
					}
				}else{
					msg = "查询不到该验证码，请输入正确的验证码";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msg;
	}  
	
	
}
