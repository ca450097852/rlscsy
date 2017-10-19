package com.hontek.enforce.pojo;
/**
 * 
 * @author cjn
 * @date 2017/6/30
 * 执法日志表
 *
 */
public class LawLog {

	private Long logId;			//日志ID
	private String entName;		//执法单位
	private Long userId;		//执法人员ID
	private String lawTime;		//执法时间
	private String addr;		//执法地点
	private Long comId;			//被执法企业ID
	private String lawResult;	//处理结果
	private String logApp;		//附件
	private String createTime;	//创建时间
	private String logNo;		//日志编号
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLawTime() {
		return lawTime;
	}
	public void setLawTime(String lawTime) {
		this.lawTime = lawTime;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Long getComId() {
		return comId;
	}
	public void setComId(Long comId) {
		this.comId = comId;
	}
	public String getLawResult() {
		return lawResult;
	}
	public void setLawResult(String lawResult) {
		this.lawResult = lawResult;
	}
	public String getLogApp() {
		return logApp;
	}
	public void setLogApp(String logApp) {
		this.logApp = logApp;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLogNo() {
		return logNo;
	}
	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}
	
	
	
	public LawLog(Long logId, String entName, Long userId, String lawTime,
			String addr, Long comId, String lawResult, String logApp,
			String createTime, String logNo) {
		super();
		this.logId = logId;
		this.entName = entName;
		this.userId = userId;
		this.lawTime = lawTime;
		this.addr = addr;
		this.comId = comId;
		this.lawResult = lawResult;
		this.logApp = logApp;
		this.createTime = createTime;
		this.logNo = logNo;
	}
	public LawLog() {
		// TODO Auto-generated constructor stub
	}
}
