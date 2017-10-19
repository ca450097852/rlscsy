package com.hontek.comm.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import com.swetake.util.Qrcode;

public class TwoDimensionCode {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 */
	public void encoderQRCode(String content, String imgPath) {
		//this.encoderQRCode(content, imgPath, "png", 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 */
	public void encoderQRCode(String content, OutputStream output) {
		//this.encoderQRCode(content, output, "png", 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public void encoderQRCode(String content, String imgPath, String imgType) {
		try {
			//this.encoderQRCode(content, imgPath, imgType, 7);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 */
	public void encoderQRCode(String content, OutputStream output, String imgType) {
		//this.encoderQRCode(content, output, imgType, 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode(String content, String imgPath, String imgType, int size,String logo_path) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size,logo_path);

			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode2(String content, String imgPath, String imgType, int size,String logo_path) {
		try {
			BufferedImage bufImg = this.qRCodeCommon2(content, imgType, size,logo_path);

			File imgFile = new File(imgPath);
			
			//TwoDimensionCode tc = new TwoDimensionCode();
			//bufImg = this.resize(bufImg, 600, 600,imgPath);
			//生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 
     * 改变图片的尺寸
     *  
     * @param source 
     *            源文件 
     * @param targetW 
     *            目标长 
     * @param targetH 
     *            目标宽 
     */  
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH,String imgPath) throws IOException  
    {  
        int type = source.getType();  
        BufferedImage target = null;  
        double sx = (double) targetW / source.getWidth();  
        double sy = (double) targetH / source.getHeight();  
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放  
        // 则将下面的if else语句注释即可  
        if (sx > sy)  
        {  
            sx = sy;  
            targetW = (int) (sx * source.getWidth());  
        }  
        else  
        {  
            sy = sx;  
            targetH = (int) (sy * source.getHeight());  
        }  
        if (type == BufferedImage.TYPE_CUSTOM)  
        { // handmade  
            ColorModel cm = source.getColorModel();  
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,  
                    targetH);  
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();  
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);  
        }  
        else  
        {  
            //固定宽高，宽高一定要比原图片大  
            //target = new BufferedImage(targetW, targetH, type);  
            target = new BufferedImage(600, 600, type);  
        }  
        Graphics2D g = target.createGraphics();  
          
        //写入背景  
        String str=imgPath;
        imgPath=str.substring(0,str.indexOf("qrcode"));
        System.out.println("#############"+imgPath);
        String path = System.getProperty("file.separator") ;
        imgPath = imgPath+"qrcode"+path+"one.png";
        System.out.println("#############22"+imgPath);
        g.drawImage(ImageIO.read(new File(imgPath)), 0, 0, null);  
          
        // smoother than exlax:  
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);  
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));  
        g.dispose();  
        return target;  
    } 
	
	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode(String content, OutputStream output, String imgType, int size,String logo_path) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size,logo_path);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	private BufferedImage qRCodeCommon(String content, String imgType, int size,String logo_path) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('L');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			//int imgSize=235;
			System.out.println("contentBytes："+contentBytes.length+" -- 图片的大小："+imgSize);
			bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码 
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}
			createPhotoAtCenter(bufImg,logo_path); //--- 调用添加logo方法
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}
	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	private BufferedImage qRCodeCommon2(String content, String imgType, int size,String logo_path) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('L');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			//int imgSize=500;
			System.out.println("contentBytes："+contentBytes.length+" -- 图片的大小："+imgSize);
			bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码 
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
			}
			//Image img = ImageIO.read(new File("c:/logo.png"));//实例化一个Image对象。
			createPhotoAtCenter(bufImg,logo_path); //--- 调用添加logo方法
			//gs.drawImage(img, 20, 20, null);
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	public String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}
	/**
     * 在二维码中间加入图片
     * 
     * @param bugImg
     * @return
     */
    private BufferedImage createPhotoAtCenter(BufferedImage bufImg,String logo_path) throws Exception {
    	 System.out.println("+++++++++++++++++++++++++++"+logo_path);
    	 Image im = ImageIO.read(new File(logo_path));
         Graphics2D g = bufImg.createGraphics();
         //设置二维码中间logo大小
         int imageWidth=35;
         int imageHeight=35;
       //获取bufImg的中间位置
         int centerX = bufImg.getMinX() + bufImg.getWidth()/2 - imageWidth/2;
         int centerY = bufImg.getMinY() + bufImg.getHeight()/2 - imageHeight/2;
         g.drawImage(im,centerX,centerY,imageWidth,imageHeight,null);
         g.dispose();
         bufImg.flush();
    	return bufImg;
    }
	public static void main(String[] args) {
		String imgPath = "C:/1.jpg";
		String encoderContent = "http://www.hzlyxh.cn/hzly/codeInter.jsp?id=233201401200052";
		TwoDimensionCode handler = new TwoDimensionCode();
		handler.encoderQRCode(encoderContent, imgPath, "png");
		// try {
		// OutputStream output = new FileOutputStream(imgPath);
		// handler.encoderQRCode(content, output);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//System.out.println("========encoder success");

		//String decoderContent = handler.decoderQRCode(imgPath);
		//System.out.println("解析结果如下：");
		//System.out.println(decoderContent);
		//System.out.println("========decoder success!!!");
	}
}