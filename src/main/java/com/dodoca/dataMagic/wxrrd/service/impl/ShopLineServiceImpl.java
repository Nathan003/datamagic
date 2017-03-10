package com.dodoca.dataMagic.wxrrd.service.impl;

import com.dodoca.dataMagic.common.dao.ShopDao;
import com.dodoca.dataMagic.wxrrd.dao.ShopLineDao;
import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.wxrrd.model.ShopLine;
import com.dodoca.dataMagic.wxrrd.service.ShopLineService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopLineServiceImpl implements ShopLineService {

	Logger log = Logger.getLogger(ShopLineServiceImpl.class);
	
	@Autowired
	private ShopLineDao shopLineDao;
	@Autowired
	private ShopDao shopDao;

	public List<ShopLine> get(){
		
		return shopLineDao.get();
	}

	public ShopLine getById(String shopLineId){
		
		return shopLineDao.getById(shopLineId);
	}

	public ShopLine getByName(String shopLine){
		
		return shopLineDao.getByName(shopLine);
	}

	public  String save(ShopLine shopLine){
			
		return shopLineDao.save(shopLine);
	}

	public boolean save(List<ShopLine> shopLineList){
		
		return shopLineDao.save(shopLineList);
	}

	public List<Shop> getShopByShopLineId(String shopLineId) {
		if(StringUtils.isEmpty(shopLineId)){
			return new ArrayList<Shop>();
		}
		if("0".equals(shopLineId)){
			return shopLineDao.getThisMonthKaShop();
		}
		if("-1".equals(shopLineId)){
			int lv =3;
			return shopLineDao.getNotKaShop(lv);
		}
		return shopLineDao.getShopByShopLineId(shopLineId);
	}

}
