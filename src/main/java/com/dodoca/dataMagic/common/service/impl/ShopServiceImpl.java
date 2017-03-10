package com.dodoca.dataMagic.common.service.impl;

import com.dodoca.dataMagic.common.dao.ShopDao;
import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.common.service.ShopService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by lifei on 2016/12/14.
 */

@Service
public class ShopServiceImpl implements ShopService {

    private static Logger log = Logger.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopDao shopDao;

    public Shop get(String shopId) {
        if(StringUtils.isEmpty(shopId)){
            return null;
        }
        return shopDao.getById(shopId);
    }
}
