package com.hontek.comm.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	/** 
     * 
     * @param filesrc 
     * @param logosrc 
     * @param outsrc 
     * @param x 位置 
     * @param y 位置 
     */ 
  public static void composePic(String filesrc,String logosrc,String outsrc,int x,int y,String validCode,int vx,int vy) { 
    try { 
        File bgfile = new File(filesrc); 
        Image bg_src = javax.imageio.ImageIO.read(bgfile); 
         
        File logofile = new File(logosrc); 
        Image logo_src = javax.imageio.ImageIO.read(logofile); 
         
        int bg_width = bg_src.getWidth(null); 
        int bg_height = bg_src.getHeight(null); 
        int logo_width = logo_src.getWidth(null);; 
        int logo_height = logo_src.getHeight(null); 
  
        BufferedImage tag = new BufferedImage(bg_width, bg_height, BufferedImage.TYPE_INT_RGB); 
         
        Graphics2D g2d = tag.createGraphics(); 
        g2d.drawImage(bg_src, 0, 0, bg_width, bg_height, null); 
         
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1.0f)); //透明度设置开始  
        g2d.drawImage(logo_src,x,y,logo_width,logo_height, null);            
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); //透明度设置 结束 
        
        
        Font font = new Font("Serif", Font.BOLD, 15);     
        g2d.setFont(font);
        g2d.setColor(Color.black);
        g2d.drawString(validCode, vx, vy); 
        
        
        FileOutputStream out = new FileOutputStream(outsrc); 
        ImageIO.write(tag, "jpg", out);//写图片 
       /* JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
        encoder.encode(tag);*/ 
        out.close(); 
    }catch (Exception e) { 
        e.printStackTrace(); 
    } 
  } 
  
  /**
   * 设置图片大小（单张图片）
   * @param src 路径
   * @param oldimg 旧图片名称
   * @param output 新图片名称
   * @param newWidth 新图片宽度
   * @param newHeight 新图片高度
   */
  public static void changeImage(String src, String output, int newWidth,int newHeight) {
         try {
             File file = new File(src);
             Image img = ImageIO.read(file);
             // 构造Image对象
//             int wideth = img.getWidth(null); // 得到源图宽
//             int height = img.getHeight(null); // 得到源图长
             BufferedImage tag = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
             tag.getGraphics()
                    .drawImage(img, 0, 0, newWidth, newHeight, null); // 绘制后的图
             FileOutputStream out = new FileOutputStream(output);
             JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
             encoder.encode(tag); // 近JPEG编码
             out.close();
         } catch (IOException e) {
             System.out.println("处理文件出现异常");
             e.printStackTrace();
         }
      }
  /** 
   * 剪切图片,没有处理图片后缀名是否正确,还有gif动态图片 
   * @param sourcePath 源路径(包含图片) 
   * @param targetPath 目标路径 null则默认为源路径 
   * @param x 起点x坐标 
   * @param y 起点y左边 
   * @param width 剪切宽度 
   * @param height 剪切高度 
   * @throws IOException if sourcePath image doesn't exist 
   */  
  public static void cutImage(String sourcePath,String targetPath,int x,int y,int width,int height) throws IOException{  
      File imageFile = new File(sourcePath);  
      if(!imageFile.exists()){  
          throw new IOException("Not found the images:"+sourcePath);  
      }  
      if(targetPath==null || targetPath.isEmpty()) targetPath = sourcePath;  
      String format = sourcePath.substring(sourcePath.lastIndexOf(".")+1,sourcePath.length());  
      BufferedImage image = ImageIO.read(imageFile);  
      image = image.getSubimage(x, y, width, height);  
      ImageIO.write(image, format, new File(targetPath));  
  }  
  
  public static void main(String args[]) throws IOException { 
      Long star = System.currentTimeMillis(); 
      String src = "f:\\imgtest\\main.png";
      String qrcode = "f:\\imgtest\\qr.png";
      String qrcode_small = "f:\\imgtest\\qr_1.png";
      String outFile = "f:\\imgtest\\out.jpg";
      
//      changeImage(qrcode,qrcode_small,100,100);
      
      //cutImage(qrcode_small,qrcode_small,5,5,90,90);
      
//      composePic(src,qrcode,outFile,70,63); 
      Long end =System.currentTimeMillis(); 
      System.out.print("time====:"+(end-star)); 
  } 
}
