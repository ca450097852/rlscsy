package com.hontek.element.service.inter;

import com.hontek.element.pojo.Xiujianchucao;

public interface XiujianchucaoServiceInter {

	String findList(Xiujianchucao xiujianchucao, int page, int rows,
			String sort, String order);

	String addXiujianchucao(Xiujianchucao xiujianchucao);

	String updateXiujianchucao(Xiujianchucao xiujianchucao);

	String deleteXiujianchucao(String ids);

}
