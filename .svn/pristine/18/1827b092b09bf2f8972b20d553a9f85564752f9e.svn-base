package com.dodoca.dataMagic.wxrrd.dao.jdbcimpl;

import com.dodoca.dataMagic.wxrrd.dao.ShopDetailDao;
import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisDetail;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ShopDetailDaoImpl implements ShopDetailDao {
	
    private static Logger log = Logger.getLogger(ShopDetailDaoImpl.class);

	@Resource(name = "projectJdbcTemplate")
	private JdbcTemplate projectJdbcTemplate;
    
	/**
	 * 根据shopId 查询商铺运营detail
	 */
	public List<ShopRapidanalysisDetail> getByShopId(String shopId) {
		String sql = "SELECT key_name,key_value,key_no,firstday_inWeek as firstdayInWeek FROM magic_cube.shop_rapidanalysis_detail  "
				+ " where shop_id = " + shopId
				+ " order by key_name,firstday_inWeek";
		return  projectJdbcTemplate.query(sql, new BeanPropertyRowMapper(ShopRapidanalysisDetail.class));
	}

}
