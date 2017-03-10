package com.dodoca.dataMagic.wxrrd.dao;

import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisDetail;

import java.util.List;

public interface ShopDetailDao {

	List<ShopRapidanalysisDetail> getByShopId(String shopId);

}
