package com.dodoca.dataMagic.wxrrd.dao.jdbcimpl;

import com.dodoca.dataMagic.wxrrd.dao.ShopSummaryDao;
import com.dodoca.dataMagic.wxrrd.model.ShopRapidanalysisSummary;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ShopSummaryDaoImpl implements ShopSummaryDao {

    private static Logger log = Logger.getLogger(ShopSummaryDaoImpl.class);

	@Resource(name = "projectJdbcTemplate")
	private JdbcTemplate projectJdbcTemplate;
    
	public List<ShopRapidanalysisSummary> getByShopId(String shopId) {
		String sql = "select * from magic_cube.shop_rapidanalysis_summary as summary where summary.shop_id = " + shopId+""
				+ " order by key_no,key_name";
    	return projectJdbcTemplate.query(sql, new BeanPropertyRowMapper(ShopRapidanalysisSummary.class));

	}

}
