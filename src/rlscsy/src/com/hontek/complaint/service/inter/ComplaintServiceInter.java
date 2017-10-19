package com.hontek.complaint.service.inter;

import java.io.InputStream;
import java.util.List;

import com.hontek.complaint.pojo.TbComplaint;

public interface ComplaintServiceInter {
	
	public String addComplaint(TbComplaint complaint);
	public String deleteComplaint(String id);
	public String updateComplaint(TbComplaint complaint);
	public String findComplaintList(TbComplaint complaint,int page,int rows,String sort,String order);
	public InputStream getFileInputStream(String cid);
	public void appendComplaint(TbComplaint com);
}
