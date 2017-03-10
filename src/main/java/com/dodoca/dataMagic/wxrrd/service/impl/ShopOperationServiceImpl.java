package com.dodoca.dataMagic.wxrrd.service.impl;

import com.dodoca.dataMagic.wxrrd.dao.ShopDetailDao;
import com.dodoca.dataMagic.wxrrd.dao.ShopSummaryDao;
import com.dodoca.dataMagic.wxrrd.model.ShopDetail;
import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisDetail;
import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisSummary;
import com.dodoca.dataMagic.wxrrd.service.ShopOperationService;
import com.dodoca.dataMagic.utils.JSONUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopOperationServiceImpl implements ShopOperationService {

	@Autowired
	private ShopDetailDao shopDetailDao;
	
	@Autowired
	private ShopSummaryDao shopSummaryDao;
	
	public List<ShopDetail> getDetailByShopId(String shopId) {
		// 对数据进行处理，整合为name ,money[],data[]
		List<ShopDetail> shopDetails = new ArrayList<ShopDetail>();
		// 原始数据
		List<ShopRapidanalysisDetail> shopRapidanalysisDetails = formatDetailValue(shopDetailDao.getByShopId(shopId));
		
		List<String> moneyList = new ArrayList<String>();
		List<String> dataList = new ArrayList<String>();
		ShopDetail shopDetail = null;
		// 整合拼装数据
		for (ShopRapidanalysisDetail shopRapidanalysisDetail : shopRapidanalysisDetails) {
			
			//将日期截取为只有 月和日
			shopRapidanalysisDetail.setFirstdayInWeek(shopRapidanalysisDetail.getFirstdayInWeek().substring(5));
			// 当list中 没有该keyname的时候，将唯一的keyname写入
			if (shopDetail == null) {
				// 在此处创建新对象，初始化清空
				shopDetail = new ShopDetail();
				shopDetail.setName(shopRapidanalysisDetail.getKeyName());
			}
			if (!shopRapidanalysisDetail.getKeyName().isEmpty()
					&& shopRapidanalysisDetail.getKeyName().equals(
							shopDetail.getName())) {
				// 如果该key name已经存在，将money 和date值写入数组
				moneyList.add(shopRapidanalysisDetail.getKeyValue());
				dataList.add(shopRapidanalysisDetail.getFirstdayInWeek());
			}
			else {
				// 将暂存的money 等list转为数组存
				shopDetail.setMoney(moneyList.toArray());
				shopDetail.setDate(dataList.toArray());
				// 将之前的shopDetail存入
				shopDetails.add(shopDetail);
				// 存入之后 重置
				shopDetail = null;
				moneyList.clear();
				dataList.clear();
				// 初始化 最新的shopDetail
				shopDetail = new ShopDetail();
				shopDetail.setName(shopRapidanalysisDetail.getKeyName());
				moneyList.add(shopRapidanalysisDetail.getKeyValue());
				dataList.add(shopRapidanalysisDetail.getFirstdayInWeek());
			}
		}
		//将最后一条存入
		shopDetail.setMoney(moneyList.toArray());
		shopDetail.setDate(dataList.toArray());
		shopDetails.add(shopDetail);
		return shopDetails;
	}

	public List<ShopRapidanalysisSummary> getSummaryByShopId(String shopId) {
		List<ShopRapidanalysisSummary> list = shopSummaryDao.getByShopId(shopId);
		list = formatSummaryValue(list);
		return list;
	}
	/**
	 * 格式化key_value，金额和人数，转化率等等
	 * @param summarys
	 * @return 
	 * @return
	 */
	public List<ShopRapidanalysisSummary> formatSummaryValue(List<ShopRapidanalysisSummary> summarys) {
		
		for (ShopRapidanalysisSummary shopRapidanalysisSummary : summarys) {
			int keyNo = Integer.parseInt(shopRapidanalysisSummary.getkey_no());
			String keyValue = shopRapidanalysisSummary.getkey_value();
			switch (keyNo) {
				case 1:case 2:case 4:
					shopRapidanalysisSummary.setkey_value(keyValue.substring(0,keyValue.length() - 2));
					break;
				case 3:case 7:case 10:case 12:case 13:case 14:case 15:case 16:
					shopRapidanalysisSummary.setkey_value(keyValue.substring(0,keyValue.length() - 5));
					break;
				case 6:case 8:case 9:case 11:case 17:
					// 如果是转化率的话，截取后4位，加小数点和%号
					keyValue = keyValue.substring(2);
					keyValue = keyValue.substring(0, 2) + "."+ keyValue.substring(2) + "%";
					// 再判断百分比 最前面一位是否为0
					if ("0".equals(keyValue.charAt(0) + "")) {
						// 去掉 最前面的0
						keyValue = keyValue.substring(1);
					}
					shopRapidanalysisSummary.setkey_value(keyValue);
					break;
				case 5:case 18:
					//人均的话，保留一位小数
					shopRapidanalysisSummary.setkey_value(keyValue.substring(0,keyValue.length() - 3));
					break;
				default:
					//默认 取整
					// 取整
					shopRapidanalysisSummary.setkey_value(keyValue.substring(0,keyValue.length() - 5));
					break;
			}
		}
		return summarys;
	}

	public List<ShopRapidanalysisDetail> formatDetailValue(List<ShopRapidanalysisDetail> details) {
		
		for (ShopRapidanalysisDetail shopRapidanalysisDetail : details) {
			int keyNo = Integer.parseInt(shopRapidanalysisDetail.getKeyNo());
			String keyValue = shopRapidanalysisDetail.getKeyValue();
			switch (keyNo) {
				case 1:case 2:case 4:
					shopRapidanalysisDetail.setKeyValue(keyValue.substring(0,keyValue.length() - 2));
					break;
				case 3:case 7:case 10:case 12:case 13:case 14:case 15:case 16:
					shopRapidanalysisDetail.setKeyValue(keyValue.substring(0,keyValue.length() - 5));
					break;
				case 6:case 8:case 9:case 11:case 17:
					// 如果是转化率的话，截取后4位，加小数点和%号
					keyValue = keyValue.substring(2);
					keyValue = keyValue.substring(0, 2) + "."+ keyValue.substring(2);
					// 再判断百分比 最前面一位是否为0
					if ("0".equals(keyValue.charAt(0) + "")) {
						// 去掉 最前面的0
						keyValue = keyValue.substring(1);
					}
					shopRapidanalysisDetail.setKeyValue(keyValue);
					break;
				case 5:case 18:
					//人均的话，保留一位小数
					shopRapidanalysisDetail.setKeyValue(keyValue.substring(0,keyValue.length() - 3));
					break;
				default:
					//默认 取整
					// 取整
					shopRapidanalysisDetail.setKeyValue(keyValue.substring(0,keyValue.length() - 5));
					break;
			}
		}
		return details;
	}
	
	@Test
	public void test(){
		 ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/springmvc.xml", "spring/applicationContext*.xml"});

	        try {
	        	ShopOperationService operationService =  (ShopOperationService) context.getBean("shopOperationServiceImpl");
	        	Map<String, Object> result = new HashMap<String, Object>();
	        	//String s = JSONUtil.objectToJson(operationService.selectSummary("13295616"));
	        	List<ShopDetail> list = operationService.getDetailByShopId("13295616");
	        	result.put("data", list);
	        	System.out.println(JSONUtil.objectToJson(result));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	

}
