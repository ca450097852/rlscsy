package com.hontek.webservice.pojo;
/**
 * 溯源信息附件实体类
 * @author lzk
 *
 */
public class TraceAppendix {
	private int id;
	private String appendixName;
	private byte[] bytes;
	private String appdixType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppendixName() {
		return appendixName;
	}
	public void setAppendixName(String appendixName) {
		this.appendixName = appendixName;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getAppdixType() {
		return appdixType;
	}
	public void setAppdixType(String appdixType) {
		this.appdixType = appdixType;
	}
	
	
}
