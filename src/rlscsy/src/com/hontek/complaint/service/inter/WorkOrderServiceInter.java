package com.hontek.complaint.service.inter;
import com.hontek.complaint.pojo.TbWorkOrder;

public interface WorkOrderServiceInter {
	
	public String addWorkOrder(TbWorkOrder workOrder);
	public String deleteWorkOrder(String id);
	public String updateWorkOrder(TbWorkOrder workOrder);
	public String findWorkOrderList(TbWorkOrder workOrder,int page,int rows,String sort,String order);
}
