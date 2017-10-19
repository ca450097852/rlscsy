package com.hontek.comm.qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import java.util.Hashtable;

import javax.imageio.*;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.hontek.comm.util.ImageManipulation;

public class MatrixToImageWriterEx {
private static final MatrixToLogoImageConfig DEFAULT_CONFIG = new MatrixToLogoImageConfig();
	

	/**/
	public static void main(String[] args) {
		try {
			int width = 139;
			int height = width;
//			String content = "http://www.hzlyxh.cn/hzly/codeInter.jsp?id=233201401200052";
			//MECARD类型数据
			String content = "种类名称：白菜MECARD:N:白春辉;ORG:沈阳审讯;ADR:辽宁省沈阳市铁西区;TEL:13591995832;EMAIL:kobe85712130@126.com;URL:www.baidu.com;";
			//BIZCARD类型数据
			String content2 = "BIZCARD:N:xia;X:ren;T:Software Engineer;C:sina;A:chaoyang, beijing, China 100101;B:+8655555555;E:webmaster@zhiwenweb.cn;";
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(content, width, height);
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 5);
			MatrixToImageWriterEx.writeToFile(matrix, "png", "D:/imgQRCode"+width+".png", "D:/logo.png", logoConfig);
			System.out.println("生成二维码结束！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据内容生成二维码数据
	 * @param content 二维码文字内容[为了信息安全性，一般都要先进行数据加密]
	 * @param width 二维码照片宽度
	 * @param height 二维码照片高度
	 * @return
	 */
	public static BitMatrix createQRCode(String content, int width, int height){
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();   		
		//设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hints.put(EncodeHintType.MARGIN, 1);  
        
        System.out.println("content==="+content);
        
        // 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix matrix = null;  
        try {  
            matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints); 
        } catch (WriterException e) {  
            e.printStackTrace();  
        }
        return matrix;
	}
	
	/*public static BitMatrix createQRCode2(String content, int width, int height){
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();   
		//设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hints.put(EncodeHintType.MARGIN, 0);  
        // 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix matrix = null;  
        try {  
            matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints); 
        } catch (WriterException e) {  
            e.printStackTrace();  
        }
        return matrix;
	}*/
	
	/**
	 * 写入二维码、以及将照片logo写入二维码中
	 * @param matrix 要写入的二维码
	 * @param format 二维码照片格式
	 * @param imagePath 二维码照片保存路径
	 * @param logoPath logo路径
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, String imagePath, String logoPath) throws IOException {
	
		MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
		
		MatrixToImageWriter.writeToFile(matrix, format, new File(imagePath),config);
		
		//添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
		BufferedImage img = ImageIO.read(new File(imagePath));
		MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoPath, DEFAULT_CONFIG);
	}
	/**
	 * 写入二维码、以及将照片logo写入二维码中
	 * @param matrix 要写入的二维码
	 * @param format 二维码照片格式
	 * @param imagePath 二维码照片保存路径
	 * @param logoPath logo路径
						 * @param logoConfig logo配置对象
						 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, String imagePath, String logoPath, MatrixToLogoImageConfig logoConfig) throws IOException {
		
		MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);

		
		MatrixToImageWriter.writeToFile(matrix, format, new File(imagePath),config);
		
		//添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
		BufferedImage img = ImageIO.read(new File(imagePath));
		MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoPath, logoConfig);
	}

	/**
	 * 将照片logo添加到二维码中间
	 * @param image 生成的二维码照片对象
	 * @param imagePath 照片保存路径
	 * @param logoPath logo照片路径
	 * @param formate 照片格式
	 */
	public static void overlapImage(BufferedImage image, String formate, String imagePath, String logoPath, MatrixToLogoImageConfig logoConfig) {
		try {
			BufferedImage logo = ImageIO.read(new File(logoPath));
			Graphics2D g = image.createGraphics();
			//考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
			int width = image.getWidth() / logoConfig.getLogoPart();
			int height = image.getHeight() / logoConfig.getLogoPart();
			//logo起始位置，此目的是为logo居中显示
			int x = (image.getWidth() - width) / 2;
			int y = (image.getHeight() - height) / 2;
			//绘制图
			g.drawImage(logo, x, y, width, height, null);
			
			//给logo画边框
			//构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
			g.setStroke(new BasicStroke(logoConfig.getBorder()));
			g.setColor(logoConfig.getBorderColor());
			g.drawRect(x, y, width, height);
			
			g.dispose();
			//写入logo照片到二维码
			//ImageIO.write(image, formate, new File(imagePath));
			
			//设置图片DPI并生成图片
			ImageManipulation imageManipulation = new ImageManipulation();
			imageManipulation.setGridImage(image);
			imageManipulation.saveGridImage(new File(imagePath));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
