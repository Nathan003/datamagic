package com.dodoca.dataMagic.dd.service.impl;

import com.dodoca.dataMagic.common.dao.ShopDao;
import com.dodoca.dataMagic.common.dao.UserDao;
import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.common.model.User;
import com.dodoca.dataMagic.common.service.impl.UserServiceBase;
import com.dodoca.dataMagic.utils.ConstantUtil;
import com.dodoca.dataMagic.utils.DataMagicUtil;
import com.dodoca.dataMagic.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifei on 2017/2/22.
 */
@Service
public class UserServiceImpl extends UserServiceBase{

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名获取用户信息，并补全用户的商铺和运营组信息
     * @param username
     * @return
     */
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
        return user;
    }

    /**
     *
     * 给用户授权概览
     * @param user
     */
    public void dashboardsAuth(User user) {
        if (null == user) {
            return;
        }
        String[] dashboards = {};
        //商铺概览
        if (!StringUtils.isEmpty(user.getShopId())) {
            try {
                Shop shop = shopDao.getById(user.getShopId());
                if (null == shop) {
                    return;
                }
                //普通商户
                dashboards = ConstantUtil.getArray(user.getProject() + ".common_shop_dashvoards");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //
        //授权
        for (String dashboard : dashboards) {
            DataMagicUtil.dashboardsAuth(user.getId(), dashboard);
        }
    }

    /**
     * 校验用户是否有使用数据魔方的权限
     * @param user
     * @return
     */
    public boolean isValidDatamagicAuthority(User user) {
        if(null == user){
            return false;
        }

        //如果店铺不是到店的店铺，不能使用数据魔方
        if (StringUtils.isEmpty(user.getShopLineId())) {
            Shop shop = shopDao.getById(user.getShopId());
            return isValidDatamagicAuth(shop);
        }
        return true;
    }

    /**
     * 校验店铺是否有使用数据魔方的权限
     * @param shop
     * @return
     */
    private boolean isValidDatamagicAuth(Shop shop) {

        if(null == shop){
            return false;
        }
        return true;
    }

    /**
     * 校验用户是否有查看商铺数据的权限
     * @param user
     * @param shopID
     * @return
     */
    public boolean isValidShopAuthority(User user, String shopID) {

        if(null == user || StringUtils.isEmpty(shopID)){
            return false;
        }
        //用户能查看自己的商铺
        if(!StringUtils.isEmpty(user.getShopId()) || user.getShopId().equals(shopID)){
            return true;
        }
        return false;
    }
}
