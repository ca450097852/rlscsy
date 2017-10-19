package com.hontek.comm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;


public class CreateImg {

	BufferedImage image;
	
	/**
	 * 设置图片DPI并生成图片
	 * @param fileLocation
	 */
	void createImage(String fileLocation) {
		try {
			//FileOutputStream fos = new FileOutputStream(fileLocation);
			//BufferedOutputStream bos = new BufferedOutputStream(fos);
			//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			//encoder.encode(image);	
			//设置图片DPI并生成图片
			ImageManipulation imageManipulation = new ImageManipulation();
			imageManipulation.setGridImage(image);
			imageManipulation.saveGridImage(new File(fileLocation));
			
			//bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二维码管理 和 二维码审核
	 * @param top_img
	 * @param proname
	 * @param maker
	 * @param codeNumber
	 * @param checkUrl
	 * @param codeNumberImg
	 * @param imgPath
	 * @return
	 */
	public String CreateImg_proBatch(String top_img,String proname,String nompanyName,String long_codeMaker,String codeNumber,String checkUrl,String codeNumberImg,String imgPath) {

		int imageWidth = 480;// 图片的宽度   530
		int imageHeight = 240;// 图片的高度   240
		String codeImg="";  // 保存协会认证的二维码图片的名称
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.black);
		
		graphics.setFont(new Font("", 0, 14));
		//判断是否需要换行
		if(long_codeMaker!=null&&!"".equals(long_codeMaker)){			
			graphics.drawString("品牌种类:" + proname, 10, 98);
			graphics.drawString("企业名称:" + nompanyName, 10, 122);
			graphics.drawString(long_codeMaker, 72, 140);
			graphics.drawString("追溯码:" + codeNumber, 10, 160);
			graphics.drawString("追溯网址: " + checkUrl, 10, 188);
			graphics.drawString("监制方: 广东省农业厅", 10, 218);
		}else{
			graphics.drawString("品牌种类:" + proname, 10, 100);
			graphics.drawString("企业名称:" + nompanyName, 10, 130);
			graphics.drawString("追溯码:" + codeNumber, 10, 160);
			graphics.drawString("追溯网址:" + checkUrl, 10, 190);
			graphics.drawString("监制方: 广东省农业厅", 10, 220);
		}
		BufferedImage bimg_top = null;
		BufferedImage bimg_code = null;
		try {
			bimg_top = javax.imageio.ImageIO.read(new java.io.File(top_img));
			bimg_code = javax.imageio.ImageIO.read(new java.io.File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bimg_top != null){
			
			graphics.drawImage(bimg_top, 0, 0,480,74, null);
		}
		if (bimg_code != null){
			
			graphics.drawImage(bimg_code, 311, 77,160,160, null);
		}
		bimg_top.flush();
		bimg_code.flush();
		graphics.dispose();
		codeImg = codeNumber+".png"; 
		createImage(imgPath);		
		return codeImg;
	}
	
	
	/**
	 * 二维码管理 和 二维码审核
	 * @param top_img
	 * @param proname
	 * @param maker
	 * @param codeNumber
	 * @param codeSts
	 * @param codeNumberImg
	 * @param imgPath
	 * @return
	 */
	public String CreateImg_new(String top_img,String proname,String maker,String long_codeMaker,String codeNumber,String checkUrl,String codeNumberImg,String imgPath) {

		int imageWidth = 480;// 图片的宽度   530
		int imageHeight = 240;// 图片的高度   240
		String codeImg="";  // 保存协会认证的二维码图片的名称
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.black);
		
		graphics.setFont(new Font("", 0, 14));
		//判断是否需要换行
		if(long_codeMaker!=null&&!"".equals(long_codeMaker)){			
			graphics.drawString("企业名称:" + proname, 10, 98);
			graphics.drawString("企业法人:" + maker, 10, 122);
			graphics.drawString(long_codeMaker, 72, 140);
			graphics.drawString("追溯码:" + codeNumber, 10, 160);
			graphics.drawString("追溯网址: " + checkUrl, 10, 188);
			graphics.drawString("监制方: 广东省农业厅", 10, 218);
		}else{
			graphics.drawString("企业名称:" + proname, 10, 100);
			graphics.drawString("企业法人:" + maker, 10, 130);
			graphics.drawString("追溯码:" + codeNumber, 10, 160);
			graphics.drawString("追溯网址:" + checkUrl, 10, 190);
			graphics.drawString("监制方: 广东省农业厅", 10, 220);
		}
		//ImageIcon imageIcon = new ImageIcon(imgurl);
		//graphics.drawImage(imageIcon.getImage(), 100, 20, null);

		// 改成这样:
		BufferedImage bimg_top = null;
		BufferedImage bimg_code = null;
		try {
			System.out.println("读取到的二维码原图片是："+imgPath);
			bimg_top = javax.imageio.ImageIO.read(new java.io.File(top_img));
			bimg_code = javax.imageio.ImageIO.read(new java.io.File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bimg_top != null){
			
			graphics.drawImage(bimg_top, 0, 0,480,74, null);
		}
		if (bimg_code != null){
			
			graphics.drawImage(bimg_code, 311, 77,160,160, null);
		}
		//graphics.drawString("二维码", 5, 12);
		bimg_top.flush();
		bimg_code.flush();
		graphics.dispose();

		//createImage("c:\\wxw.png");
		// 保存图片
		
		//codeImgPath = imgPath+path+codeNumber+".png";
		
		//System.out.println("保存协会二维码图片的路径："+codeImgPath);
		codeImg = codeNumber+".png"; 
		createImage(imgPath);
		
		return codeImg;
	}
	
	/**
	 * 二维码在右边 480*240px
	 * @param top_img
	 * @param typeName
	 * @param companyName
	 * @param dimenno
	 * @param phone
	 * @param area
	 * @param imgPath
	 * @return
	 */
	public String CreateImg_record(String top_img,String typeName,String companyName,String dimenno,String phone,String area,String imgPath) {

		int imageWidth = 480;// 图片的宽度   530
		int imageHeight = 240;// 图片的高度   240
		String codeImg="";  // 保存协会认证的二维码图片的名称
		
		//if(typeName.length()>15)
		//	typeName = typeName.substring(0, 14).concat("...");
		if(companyName.length()>18)
			companyName = companyName.substring(0, 17).concat("...");
		//if(area.length()>15)
		//	area = area.substring(0, 14).concat("...");
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.black);
		
		graphics.setFont(new Font("", 0, 14));
		//判断是否需要换行
		graphics.drawString("品牌种类:" + typeName, 10, 100);
		graphics.drawString("生产单位:" + companyName, 10, 130);
		graphics.drawString("电话:" + phone, 10, 160);
		graphics.drawString("产地:" + area, 10, 190);
		graphics.drawString("追溯码:"+dimenno, 10, 220);
		//ImageIcon imageIcon = new ImageIcon(imgurl);
		//graphics.drawImage(imageIcon.getImage(), 100, 20, null);

		// 改成这样:
		BufferedImage bimg_top = null;
		BufferedImage bimg_code = null;
		try {
			System.out.println("读取到的二维码原图片是："+imgPath);
			bimg_top = javax.imageio.ImageIO.read(new java.io.File(top_img));
			bimg_code = javax.imageio.ImageIO.read(new java.io.File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bimg_top != null){
			
			graphics.drawImage(bimg_top, 0, 0,480,74, null);
		}
		if (bimg_code != null){
			
			graphics.drawImage(bimg_code, 311, 77,160,160, null);
		}
		//graphics.drawString("二维码", 5, 12);
		bimg_top.flush();
		bimg_code.flush();
		graphics.dispose();
		codeImg = phone+".png"; 
		createImage(imgPath);
		
		return codeImg;
	}
	
	
	/**
	 * 二维码在左边 480*240px
	 * @param top_img
	 * @param typeName
	 * @param companyName
	 * @param dimenno
	 * @param phone
	 * @param area
	 * @param imgPath
	 * @return
	 */
	public String CreateImg_dimeno(String top_img,String typeName,String companyName,String dimenno,String phone,String area,String imgPath) {

		int imageWidth = 480;// 图片的宽度   530
		int imageHeight = 240;// 图片的高度   240
		String codeImg="";  // 保存协会认证的二维码图片的名称
		
		//if(typeName.length()>15)
		//	typeName = typeName.substring(0, 14).concat("...");
		if(companyName.length()>18)
			companyName = companyName.substring(0, 17).concat("...");
		//if(area.length()>15)
		//	area = area.substring(0, 14).concat("...");
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.black);
		
		graphics.setFont(new Font("", 0, 14));
		//判断是否需要换行
		graphics.drawString("品牌种类:" + typeName, 181, 100);
		graphics.drawString("生产单位:" + companyName, 181, 130);
		graphics.drawString("电话:" + phone, 181, 160);
		graphics.drawString("产地:" + area, 181, 190);
		graphics.drawString("追溯码:"+dimenno, 181, 220);
		//ImageIcon imageIcon = new ImageIcon(imgurl);
		//graphics.drawImage(imageIcon.getImage(), 100, 20, null);

		// 改成这样:
		BufferedImage bimg_top = null;
		BufferedImage bimg_code = null;
		try {
			System.out.println("读取到的二维码原图片是："+imgPath);
			bimg_top = javax.imageio.ImageIO.read(new java.io.File(top_img));
			bimg_code = javax.imageio.ImageIO.read(new java.io.File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bimg_top != null){		
			graphics.drawImage(bimg_top, 0, 0,480,74, null);
		}
		if (bimg_code != null){			
			graphics.drawImage(bimg_code, 10, 77,160,160, null);
		}
		//graphics.drawString("二维码", 5, 12);
		bimg_top.flush();
		bimg_code.flush();
		graphics.dispose();
		codeImg = phone+".png"; 
		createImage(imgPath);//生成的二维码标签图片将会覆盖掉原二维码图片；
		
		return codeImg;
	}
	
	
	/**
	 * 二维码在右边 400*200px
	 * @param top_img
	 * @param typeName
	 * @param companyName
	 * @param dimenno
	 * @param phone
	 * @param area
	 * @param imgPath
	 * @return
	 */
	public String CreateImg_record_size1(String top_img,String typeName,String companyName,String dimenno,String phone,String area,String imgPath) {

		int imageWidth = 400;// 图片的宽度   400
		int imageHeight = 200;// 图片的高度   200
		String codeImg="";  // 保存协会认证的二维码图片的名称

		if(companyName.length()>13)
			companyName = companyName.substring(0, 13).concat("...");
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.black);
		
		graphics.setFont(new Font("", 0, 14));
		//判断是否需要换行
		graphics.drawString("品牌种类:" + typeName, 10, 80);
		graphics.drawString("生产单位:" + companyName, 10, 107);
		graphics.drawString("电话:" + phone, 10, 134);
		graphics.drawString("产地:" + area, 10, 160);
		graphics.drawString("追溯码:"+dimenno, 10, 187);
		
		// 改成这样:
		BufferedImage bimg_top = null;
		BufferedImage bimg_code = null;
		try {
			//System.out.println("读取到的二维码原图片是："+imgPath);
			bimg_top = javax.imageio.ImageIO.read(new java.io.File(top_img));
			bimg_code = javax.imageio.ImageIO.read(new java.io.File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bimg_top != null){			
			graphics.drawImage(bimg_top, 0, 0,400,61, null);
		}
		if (bimg_code != null){			
			graphics.drawImage(bimg_code, 261, 61,140,140, null);
		}
		bimg_top.flush();
		bimg_code.flush();
		graphics.dispose();
		codeImg = phone+".png"; 
		createImage(imgPath);
		
		return codeImg;
	}
	
	
	/**
	 * 二维码在左边  400*200px
	 * @param top_img
	 * @param typeName
	 * @param companyName
	 * @param dimenno
	 * @param phone
	 * @param area
	 * @param imgPath
	 * @return
	 */
	public String CreateImg_dimeno_size1(String top_img,String typeName,String companyName,String dimenno,String phone,String area,String imgPath) {

		int imageWidth = 400;// 图片的宽度   400
		int imageHeight = 200;// 图片的高度   200
		String codeImg="";  // 保存协会认证的二维码图片的名称
		
		//if(typeName.length()>15)
		//	typeName = typeName.substring(0, 14).concat("...");
		if(companyName.length()>13)
			companyName = companyName.substring(0, 13).concat("...");
		//if(area.length()>15)
		//	area = area.substring(0, 14).concat("...");
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.black);
		
		graphics.setFont(new Font("", 0, 14));
		//判断是否需要换行
		graphics.drawString("品牌种类:" + typeName, 145, 80);
		graphics.drawString("生产单位:" + companyName, 145, 107);
		graphics.drawString("电话:" + phone, 145, 134);
		graphics.drawString("产地:" + area, 145, 161);
		graphics.drawString("追溯码:"+dimenno, 145, 187);
		//ImageIcon imageIcon = new ImageIcon(imgurl);
		//graphics.drawImage(imageIcon.getImage(), 100, 20, null);
		// 改成这样:
		BufferedImage bimg_top = null;
		BufferedImage bimg_code = null;
		try {
			System.out.println("读取到的二维码原图片是："+imgPath);
			bimg_top = javax.imageio.ImageIO.read(new java.io.File(top_img));
			bimg_code = javax.imageio.ImageIO.read(new java.io.File(imgPath));
			if (bimg_top != null){		
				graphics.drawImage(bimg_top, 0, 0,400,61, null);
				bimg_top.flush();
			}
			if (bimg_code != null){			
				graphics.drawImage(bimg_code, 5, 61,140,140, null);
				bimg_code.flush();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		graphics.dispose();
		codeImg = phone+".png"; 
		createImage(imgPath);//生成的二维码标签图片将会覆盖掉原二维码图片；		
		return codeImg;
	}
		
	
	public static void main(String[] args) {
		CreateImg cg = new CreateImg();
		try {
			cg.CreateImg_record_size1("f://code/code_top_1.jpg","类型名称","广州薪火薪火网络科技有限公司","201507290001","15626013130","广州", "f://code/logo.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
