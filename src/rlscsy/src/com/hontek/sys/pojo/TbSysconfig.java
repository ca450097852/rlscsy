package com.hontek.sys.pojo;

/**
 * @ClassName: TbSysconfig
 * @Description: TODO(系统配置信息表)
 * @date 2015-7-21 下午02:51:04
 * @author qql
 * @version 1.0
 */

public class TbSysconfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int configId;
	private String needareqaudit;//NEEDAREQAUDIT:是否需要区县审核;y需要，n不需要；默认n
	private String needcityaudit;//NEEDCITYAUDIT:是否需要地市审核;y需要，n不需要；默认n
	private String showpingjia;//SHOWPINGJIA:是否显示诚信评价;y显示，n不显示；默认y
	private String showjianguan;//SHOWJIANGUAN:是否显示监管信息;y显示，n不显示；默认y
	private String showjijian;//SHOWJIJIAN:是否信息自检信息;y显示，n不显示；默认y
	private String updatetime;//修改时间
	private String updateuser;//修改人

	/** default constructor */
	public TbSysconfig() {
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public String getNeedareqaudit() {
		return needareqaudit;
	}

	public void setNeedareqaudit(String needareqaudit) {
		this.needareqaudit = needareqaudit;
	}

	public String getNeedcityaudit() {
		return needcityaudit;
	}

	public void setNeedcityaudit(String needcityaudit) {
		this.needcityaudit = needcityaudit;
	}

	public String getShowpingjia() {
		return showpingjia;
	}

	public void setShowpingjia(String showpingjia) {
		this.showpingjia = showpingjia;
	}

	public String getShowjianguan() {
		return showjianguan;
	}

	public void setShowjianguan(String showjianguan) {
		this.showjianguan = showjianguan;
	}

	public String getShowjijian() {
		return showjijian;
	}

	public void setShowjijian(String showjijian) {
		this.showjijian = showjijian;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
}