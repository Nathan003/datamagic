package com.dodoca.dataMagic.wxrrd.service;

import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.wxrrd.model.ShopLine;

import java.util.List;

public interface ShopLineService {

	/**
	 * 查询所有
	 */
	public abstract List<ShopLine> get();

	/**
	 * 按照主键id查询
	 * @param shopLineId
	 * @return
	 */
	public abstract ShopLine getById(String shopLineId);

	/**
	 * 通过shop_line查询user数据
	 */
	public abstract ShopLine getByName(String shopLine);

	/**
	 * 插入单条数据
	 */
	public abstract String save(ShopLine shopLine);

	/**
	 * 批量插入数据
	 */
	public abstract boolean save(List<ShopLine> shopLineList);

	/**
	 *
	 * @param shopLineId
	 * @return
	 */
	List<Shop> getShopByShopLineId(String shopLineId);
}