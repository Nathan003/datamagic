package com.dodoca.dataMagic.wxrrd.controller.springmvc;

import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.wxrrd.model.ShopLine;
import com.dodoca.dataMagic.wxrrd.service.ShopLineService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/shopLine")
public class ShopLineController {

    private Logger logger = Logger.getLogger(ShopLineController.class);
    @Autowired
    private ShopLineService shopLineService;

    //根据id查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable("id") String id) {
        System.out.println(shopLineService.getById(id).getShopLine());
        return "index";
    }

    //查询所有的对象 并返回json
    @RequestMapping(value = "/getAllShopLine", method = RequestMethod.POST)
    @ResponseBody
    public List<ShopLine> getAllShopLine(HttpServletRequest request, HttpServletResponse response) {

        List<ShopLine> shopLines;
        try {
            shopLines = shopLineService.get();
            shopLines.add(new ShopLine("0", "全部运营组"));
            shopLines.add(new ShopLine("-1", "平台"));
            return shopLines;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/{shopLineId}/shops", method = RequestMethod.GET)
    @ResponseBody
    public List<Shop> shops(@PathVariable("shopLineId") String shopLineId,HttpServletRequest request, HttpServletResponse response) {

        List<Shop> shops =  shopLineService.getShopByShopLineId(shopLineId);
        return shops;

    }
}
