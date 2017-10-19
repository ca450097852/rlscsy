package com.hontek.webservice.pojo;
/**
 * 资质附件
 * @author lzk
 *
 */
public class Annex {
	private String name;//附件名称
	private byte[] bytes;//附件内容
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
}
