package com.hontek.company.pojo;

import java.io.Serializable;
/**
 * 信用评价信息表
 * @author dream
 *
 */
public class Evaluation implements Serializable {

	/*   eval_id              int                  not null,
   ent_id               int                  null,
   eval_contents        varchar(1000)        null,
   eval_time            varchar(25)          null,
   company_id           int                  null,
   company_contents     varchar(1000)        null,
   company_time         varchar(25)          null,*/
	
	
	private int evalId;
	private int entId;
	private String evalContents;
	private String evalTime;
	
	private int companyId;
	private String companyContents;
	private String companyTime;
	
	public Evaluation() {
		super();
	}

	public int getEvalId() {
		return evalId;
	}

	public void setEvalId(int evalId) {
		this.evalId = evalId;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getEvalContents() {
		return evalContents;
	}

	public void setEvalContents(String evalContents) {
		this.evalContents = evalContents;
	}

	public String getEvalTime() {
		return evalTime;
	}

	public void setEvalTime(String evalTime) {
		this.evalTime = evalTime;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyContents() {
		return companyContents;
	}

	public void setCompanyContents(String companyContents) {
		this.companyContents = companyContents;
	}

	public String getCompanyTime() {
		return companyTime;
	}

	public void setCompanyTime(String companyTime) {
		this.companyTime = companyTime;
	}


	
	
}
