package com.dodoca.dataMagic.wxrrd.dao;

import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisSummary;

import java.util.List;

public interface ShopSummaryDao {

	List<ShopRapidanalysisSummary> getByShopId(String shopId);
}
