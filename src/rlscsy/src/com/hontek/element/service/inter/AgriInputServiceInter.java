package com.hontek.element.service.inter;

import java.util.List;

import com.hontek.comm.base.LoginUser;
import com.hontek.element.pojo.TbAgriInput;
/**
 * <p>Title: 投入品购买信息</p>
 * <p>Description: 投入品购买信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface AgriInputServiceInter {

	public String addAgriInput(TbAgriInput agriInput);

	public String deleteAgriInput(String ids);

	public String updateAgriInput(TbAgriInput agriInput);

	public String findAgriInputList(TbAgriInput agriInput, int page, int rows, String sort,String order);
	
	public String findAgriInputComboList(LoginUser loginUser,String ids);

}
