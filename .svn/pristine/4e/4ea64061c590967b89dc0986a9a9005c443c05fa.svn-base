package com.dodoca.dataMagic.wxrrd.service.impl;

import com.dodoca.dataMagic.common.dao.ShopDao;
import com.dodoca.dataMagic.common.dao.UserDao;
import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.common.model.User;
import com.dodoca.dataMagic.common.service.impl.UserServiceBase;
import com.dodoca.dataMagic.utils.ConstantUtil;
import com.dodoca.dataMagic.utils.DataMagicUtil;
import com.dodoca.dataMagic.wxrrd.model.ShopLine;
import com.dodoca.dataMagic.wxrrd.service.ShopLineService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServieImpl extends UserServiceBase {

    private static Logger logger = Logger.getLogger(UserServieImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopLineService shopLineService;

    public void dashboardsAuth(User user) {
        if (null == user) {
            return;
        }
        String[] dashboards = {};

        if (!StringUtils.isEmpty(user.getShopId())) {
            try {
                Shop shop = shopDao.getById(user.getShopId());
                if (null == shop) {
                    return;
                }
                if (StringUtils.isEmpty(shop.getShopLine())) {
                    //普通商户
                    dashboards = ConstantUtil.getArray(user.getProject() + ".common_shop_dashvoards");
                } else {//KA商铺
                    dashboards = ConstantUtil.getArray(user.getProject() + ".ka_shop_dashvoards");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (!StringUtils.isEmpty(user.getShopLineId())) {
            if ("-1".equals(user.getShopLineId())) {//平台概览
                dashboards = ConstantUtil.getArray(user.getProject() + ".platform_dashvoards");
            } else if ("0".equals(user.getShopLineId())) {//特殊ka概览
                dashboards = ConstantUtil.getArray(user.getProject() + ".special_shop_line_dashvoards");
            } else {//普通ka概览
                dashboards = ConstantUtil.getArray(user.getProject() + ".common_shop_line_dashvoards");
            }

        }

        for (String dashboard : dashboards) {
            DataMagicUtil.dashboardsAuth(user.getId(), dashboard);
        }
    }

    public boolean isValidDatamagicAuthority(User user) {
        if(null == user){
            return false;
        }
        if (StringUtils.isEmpty(user.getShopLineId())) {
            Shop shop = shopDao.getById(user.getShopId());
            return isValidDatamagicAuth(shop);
        }
        return true;
    }

    private boolean isValidDatamagicAuth(Shop shop) {
        if (null == shop) {
            return false;
        }

        if (null == shop) {
            return false;
        }
        if (!StringUtils.isEmpty(shop.getShopLineId())) {//ka商铺
            return true;
        }
        if (shop.getLv() < 3) {//普通商铺
            return false;
        }
        return true;
    }

    public boolean isValidShopAuthority(User user, String shopID) {

        if(null == user || StringUtils.isEmpty(shopID)){
            return false;
        }

        Shop shop = shopDao.getById(shopID);
        if(null == shop){
            return false;
        }

        //商铺用户可以查看自己的shop
        if(!StringUtils.isEmpty(user.getShopId()) && shopID.equals(user.getShopId())){
            return true;
        }
        //普通ka用户可以查看自己运营的商铺
        if(!StringUtils.isEmpty(user.getShopLineId()) && user.getShopLineId().equals(shop.getShopLineId())){
            return true;
        }
        //运营组ID为0表示可以查看所有ka商铺
        if("0".equals(user.getShopLineId()) && !StringUtils.isEmpty(shop.getShopLineId())){
            return true;
        }

        if("-1".equals(user.getShopLineId()) && StringUtils.isEmpty(shop.getShopLineId())){//平台用户查看所有等级大于等于3且非ka商铺
            return true;
        }
        return false;
    }

	public User getByName(String username) {
		User user = userDao.getByName(username,ConstantUtil.PROJECT);
        if(null == user){
            return user;
        }
        //设置商铺名称
        if(!StringUtils.isEmpty(user.getShopId())){
            Shop shop = shopDao.getById(user.getShopId());
            if(null != shop){
                user.setShopName(shop.getShopName());
            }

        }
        //根据用户所属的运营组，设置运营组信息
        if(StringUtils.isEmpty(user.getShopLineId())){
            ShopLine shopLine = shopLineService.getById(user.getShopLineId());
            if(null != shopLine){
                user.setShopLine(shopLine.getShopLine());
            }
        }
        return user;
	}

}
