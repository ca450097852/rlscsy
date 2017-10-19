package com.hontek.comm.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class TwoDimensionCodeImage implements QRCodeImage {
	
	BufferedImage bufImg;  
    
    public TwoDimensionCodeImage(BufferedImage bufImg) {  
        this.bufImg = bufImg;  
    }  

	public int getHeight() {
		// TODO Auto-generated method stub
		//return 0;
		return bufImg.getHeight();
	}

	public int getPixel(int arg0, int arg1) {
		// TODO Auto-generated method stub
		//return 0;
		return bufImg.getRGB(arg0, arg1);
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		//return 0;
		return bufImg.getWidth();
	}

}
