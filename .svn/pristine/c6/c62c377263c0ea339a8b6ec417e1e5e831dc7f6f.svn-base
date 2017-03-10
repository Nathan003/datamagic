package com.dodoca.dataMagic.wxrrd.controller.springmvc;

import com.alibaba.druid.util.StringUtils;
import com.dodoca.dataMagic.wxrrd.model.ShopDetail;
import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisSummary;
import com.dodoca.dataMagic.common.model.User;
import com.dodoca.dataMagic.common.service.ShopService;
import com.dodoca.dataMagic.common.service.UserServie;
import com.dodoca.dataMagic.wxrrd.service.ShopOperationService;
import com.dodoca.dataMagic.utils.ConstantUtil;
import com.dodoca.dataMagic.utils.DataMagicUtil;
import com.dodoca.dataMagic.utils.ValidUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商铺运营状况controller
 *
 * @author admin
 */
@Controller
@RequestMapping("/shopOperation")
public class ShopOperationController {
    private Logger logger = Logger.getLogger(ShopOperationController.class);
    @Autowired
    private ShopOperationService shopOperationService;
    @Autowired
    ShopService shopService;
    @Autowired
    private UserServie userService;

    /**
     * 各指标的详细数据
     *
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> shopRapid(@RequestBody Map<String, Object> info, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        String shopId = (String) info.get("shopId");
        String username = (String) info.get("userName");

        String token = DataMagicUtil.getCookieValue(request, "sensorsdata-token_" + ConstantUtil.PROJECT);
        //校验用户是否已登录
        if (!ValidUtils.validLogin(username, token)) {
            logger.debug("请先登录");
            response.setStatus(401);
            result.put("erro", "没有权限访问");
            return result;
        }
        //参数检验
        if (StringUtils.isEmpty(shopId)) {
            logger.debug("shopId为空");
            //TODO
            response.setStatus(460);
            result.put("erro", "没有权限访问");
            return result;
        }
        User user = userService.getByName(username);
        //3.判断用户是否有查看shop的权限
        if (!userService.isValidShopAuthority(user, shopId)) {
            logger.debug("没有查看当前商铺的权限");
            response.setStatus(460);
            result.put("erro", "没有权限访问");
            return result;
        }
        List<ShopDetail> detaildata = shopOperationService.getDetailByShopId(shopId);
        result.put("detaildata", detaildata);

        List<ShopRapidanalysisSummary> summaryData = shopOperationService.getSummaryByShopId(shopId);
        result.put("summaryData", summaryData);

        response.setStatus(200);
        return result;
    }

}
