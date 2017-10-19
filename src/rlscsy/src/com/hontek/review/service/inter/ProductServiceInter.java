package com.hontek.review.service.inter;

import java.util.List;
import java.util.Map;

import com.hontek.review.pojo.TbProduct;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.ProductInfo;

public interface ProductServiceInter {

	Pager<TbProduct> findProductList(TbProduct product, int page, int rows,boolean flag,long entId, String order, String sort);

	void addProduct(TbProduct product,ProductInfo productInfo);

	void updateProduct(TbProduct product,ProductInfo productInfo);

	void updateProductState(String ids, String state);

	TbProduct getProductByDimenno(String code);

	TbProduct getProductByProId(int proId);

	boolean findIsUniqueCode(String dimenno);

	void deleteProduct(String ids);
	/**
	 * 添加同步产品
	 * @param proList
	 * @param interAccount
	 * @return
	 */
	List appendProduct(List<TbProduct> proList, TbInterAccount interAccount);

	/**
	 * 统计产品数量
	 * @param product
	 * @return
	 */
	public String findProductChart(TbProduct product);
	

	/**
	 * 统计企业产品数量
	 * @param product
	 * @return
	 */
	public String findEntProductChart(String areaName);
	
	
	public String findProductTable(TbProduct product);

	public String findAnimalProductList(int page, int rows);

	Pager<TbProduct> findProductListForEnt(TbProduct product, int page,
			int rows, boolean admin, long entId, String order, String sort);
}
