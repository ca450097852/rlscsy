package com.hontek.webservice.pojo;
/**
 * 返回结果实体类
 * @author lzk
 *
 */
public class ResultClass {
	private boolean success;//判断调用是否成功 true：成功 false:失败
	private String info;
	private String errorInfo;//错误信息
	
	private String qrcodeImgName;//图片名字
	private byte[] qrcodeImg;//二维码图片 带文字
	private String qrcodeBigName;//高清图片名称
	private byte[] qrcodeBig;//二维码高清图
	private String qrcodeSmallName;//小图图片名称
	private byte[] qrcodeSmall;//二维码小图
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public byte[] getQrcodeImg() {
		return qrcodeImg;
	}
	public void setQrcodeImg(byte[] qrcodeImg) {
		this.qrcodeImg = qrcodeImg;
	}
	public byte[] getQrcodeBig() {
		return qrcodeBig;
	}
	public void setQrcodeBig(byte[] qrcodeBig) {
		this.qrcodeBig = qrcodeBig;
	}
	public byte[] getQrcodeSmall() {
		return qrcodeSmall;
	}
	public void setQrcodeSmall(byte[] qrcodeSmall) {
		this.qrcodeSmall = qrcodeSmall;
	}
	public String getQrcodeImgName() {
		return qrcodeImgName;
	}
	public void setQrcodeImgName(String qrcodeImgName) {
		this.qrcodeImgName = qrcodeImgName;
	}
	public String getQrcodeBigName() {
		return qrcodeBigName;
	}
	public void setQrcodeBigName(String qrcodeBigName) {
		this.qrcodeBigName = qrcodeBigName;
	}
	public String getQrcodeSmallName() {
		return qrcodeSmallName;
	}
	public void setQrcodeSmallName(String qrcodeSmallName) {
		this.qrcodeSmallName = qrcodeSmallName;
	}
	
	
	
}
