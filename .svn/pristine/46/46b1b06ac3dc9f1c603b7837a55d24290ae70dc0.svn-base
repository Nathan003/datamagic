package com.dodoca.dataMagic.wxrrd.dao;

import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.wxrrd.model.ShopLine;

import java.util.List;

public interface ShopLineDao {

	/**
	 * 获取所有运营组
	 * @return
	 */
	public abstract List<ShopLine> get();

	/**
	 * 根据ID
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
	 * 根据运营组ID获取当月运营的商铺
	 * @param shopLineId
	 * @return
	 */
	List<Shop> getShopByShopLineId(String shopLineId);

	/**
	 * 查询本月ka商铺
	 * @return
	 */
	List<Shop> getThisMonthKaShop();

	/**
	 * 获取等级大于等于指定等级的商铺
	 * @param lv
	 * @return
	 */
	List<Shop> getNotKaShop(int lv);
}
