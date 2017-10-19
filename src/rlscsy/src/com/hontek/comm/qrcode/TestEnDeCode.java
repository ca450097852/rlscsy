/**
 * 
 */
package com.hontek.comm.qrcode;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.util.Hashtable;  
  
import javax.imageio.ImageIO;  
  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.DecodeHintType;  
import com.google.zxing.LuminanceSource;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.Reader;  
import com.google.zxing.ReaderException;  
import com.google.zxing.Result;  
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.common.HybridBinarizer;  
/**
 * @ClassName: TestEnDeCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015-8-27 上午09:11:48
 * @author qql
 * @version 1.0
 */
public class TestEnDeCode {
	 /** 
     *  
     */  
    public TestEnDeCode() {  
        // TODO Auto-generated constructor stub  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        TestEnDeCode t=new TestEnDeCode();  
        t.encode();  
        t.decode();  
  
    }  
  
    // 编码  
    public void encode() {  
        try {  
//            String str = "CN:男;COP:公司;ZW:职务";// 二维码内容  
//            String str ="MECARD:N:白春辉;ORG:沈阳审讯;ADR:辽宁省沈阳市铁西区;TEL:13591995832;EMAIL:kobe85712130@126.com;URL:sy.hontek.com.cn/ncpsy/trace.jsp?code=440200000079;";
            
            String str ="MECARD:N:蔡夙雅;ORG:广州薪火网络科技有限公司;ADR:广州市越秀区东风东路834号东峻广场1座30楼;TEL:13926430827;EMAIL:13926430827@139.com;FOX:020-28821018;";
            
            String path = "F:\\hwy.png";  
            BitMatrix byteMatrix;  
            byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("GBK"),"iso-8859-1"),  
                    BarcodeFormat.QR_CODE, 200, 200);  
            File file = new File(path);  
              
            MatrixToImageWriter.writeToFile(byteMatrix, "png", file);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 解码  
    public void decode() {  
        try {  
            Reader reader = new MultiFormatReader();  
            String imgPath = "F:\\hwy.png";  
            File file = new File(imgPath);  
            BufferedImage image;  
            try {  
                image = ImageIO.read(file);  
                if (image == null) {  
                    System.out.println("Could not decode image");  
                }  
                LuminanceSource source = new BufferedImageLuminanceSource(image);  
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(  
                        source));  
                Result result;  
                Hashtable hints = new Hashtable();  
                hints.put(DecodeHintType.CHARACTER_SET, "GBK");  
                result = new MultiFormatReader().decode(bitmap, hints);  
                String resultStr = result.getText();  
                System.out.println(resultStr);  
  
            } catch (IOException ioe) {  
                System.out.println(ioe.toString());  
            } catch (ReaderException re) {  
                System.out.println(re.toString());  
            }  
  
        } catch (Exception ex) {  
  
        }  
    }  
  
}
