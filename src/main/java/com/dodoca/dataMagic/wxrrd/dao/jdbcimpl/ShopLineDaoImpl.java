package com.dodoca.dataMagic.wxrrd.dao.jdbcimpl;

import com.dodoca.dataMagic.common.model.Shop;
import com.dodoca.dataMagic.wxrrd.dao.ShopLineDao;
import com.dodoca.dataMagic.wxrrd.model.ShopLine;
import com.dodoca.dataMagic.wxrrd.service.impl.ShopLineServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShopLineDaoImpl implements ShopLineDao {

	private static Logger log = Logger.getLogger(ShopLineServiceImpl.class);

	@Resource(name = "projectJdbcTemplate")
	private JdbcTemplate projectJdbcTemplate;

	/**
	 * 查询所有
	 */
	public List<ShopLine> get() {

		String sql = "select distinct this_month_line as shop_line,this_month_line_id as shop_line_id" +
				" from magic_cube.shop_line_roll2month order by convert( this_month_line using gbk ) collate gbk_chinese_ci asc";
		List<ShopLine> shopLines=  projectJdbcTemplate.query(sql, new BeanPropertyRowMapper(ShopLine.class));
		log.debug("本月所有运营组个数：" + shopLines);
		return shopLines;
	}

	/**
	 * 根据主键 查询
	 */
	public ShopLine getById(String shopLineId) {

		String sql = "select distinct this_month_line as shop_line,this_month_line_id as shop_line_id" +
				" from magic_cube.shop_line_roll2month where this_month_line_id='" + shopLineId + "'";
		List<ShopLine> shopLines =  projectJdbcTemplate.query(sql, new BeanPropertyRowMapper(ShopLine.class));
		if(shopLines.isEmpty()){
			log.debug("通过id没有查询到该用户组");
			return null;
		}else{
			log.debug("根据主键id查询运营组成功");
			return shopLines.get(0);
		}
	}

	/**
	 * 根据运营组 查询
	 */
	public ShopLine getByName(String shopLine) {

		String sql = "select distinct this_month_line as shop_line,this_month_line_id as shop_line_id" +
				" from magic_cube.shop_line_roll2month where this_month_line='" + shopLine + "'";
		List<ShopLine> shopLines =  projectJdbcTemplate.query(sql, new BeanPropertyRowMapper(ShopLine.class));
		if(shopLines.isEmpty()){
			log.debug("通过shopLine没有查询到该运营组");
			return null;
		}else{
			log.debug("根据运营组名称查询运营组成功");
			return shopLines.get(0);
		}
	}


	/**
	 * 插入单条数据
	 */
	public String save(ShopLine shopLine) {

		final ShopLine tempShopLine = shopLine;
		log.debug("插入单条user数据");
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?)";
		projectJdbcTemplate.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement pstm) throws SQLException {
				// TODO 预处理 ，进行插入
				try {
					setIntoPstm(pstm, tempShopLine);
				} catch (Exception e) {
					//将日志打印，并将错误抛出
					log.debug("ShopLineShop插入单条数据错误：id为:"+ tempShopLine.getShopLineId());
				}
			}
		});
		return shopLine.getShopLineId();
	}


	public boolean save(List<ShopLine> shopLineList) {

//		final  List<ShopLine> tempShopLineList = shopLineList;
//		log.debug("插入单条ShopLineShop数据");
//		String sql = "insert into shop_line_shop values(?,?,?)";
//		try {
//			projectJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
//
//				public void setValues(PreparedStatement pstm,int i)throws SQLException
//				{
//					try {
//						setIntoPstm(pstm, tempShopLineList.get(i));
//					} catch (Exception e) {
//						log.debug("批量插入运营组数据错误");
//					}
//				}
//				public int getBatchSize()
//				{
//					return tempShopLineList.size();
//				}
//			});
//			log.debug("批量插入成功");
//			return true;
//		} catch (DataAccessException e) {
//			// TODO 判断是否批量插入
//			log.debug("批量插入运营组失败 错误信息："+e);
//			return false;
//		}
		return true;
	}

	public List<Shop> getShopByShopLineId(String shopLineID) {
		final String finalShopLineID = shopLineID;
		final String sql = "select wsh.id as shop_id,wsh.name as shop_name" +
				" from magic_cube.shop_line_roll2month tshl join wxrrd.shop wsh on tshl.shopid=wsh.id" +
				" where tshl.this_month_line_id=? order by convert( wsh.name using gbk ) collate gbk_chinese_ci asc";
		log.debug(sql);
		List<Shop> shops = projectJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1,finalShopLineID);
			}
		},new BeanPropertyRowMapper<Shop>(Shop.class));
		return shops;
	}

	public List<Shop> getThisMonthKaShop() {
		String sql = "select wsh.id as shop_id,wsh.name as shop_name" +
				" from magic_cube.shop_line_roll2month tshl join wxrrd.shop wsh on tshl.shopid=wsh.id" +
				" where tshl.this_month_line_id is not null order by convert( wsh.name using gbk ) collate gbk_chinese_ci asc";
		log.debug(sql);
		List<Shop> shops = projectJdbcTemplate.query(sql,new BeanPropertyRowMapper<Shop>(Shop.class));
		return shops;
	}

	public List<Shop> getNotKaShop(final int lv) {
		final String sql = "select wsh.id as shop_id, wsh.`name` as shop_name, mcshc.shop_cate1_id," +
				" mcshc.shop_cate1_name, mcshc.shop_cate2_id, mcshc.shop_cate2_name," +
				" mcshlr.this_month_line_id as shop_line_id, mcshlr.this_month_line as shop_line," +
				" mcshlc.lv from wxrrd.shop wsh join magic_cube.shop mcsh on wsh.id = mcsh.shop_id" +
				" left join magic_cube.shop_line_roll2month mcshlr on wsh.id = mcshlr.shopid" +
				" left join magic_cube.shop_cate mcshc on mcsh.shop_cate_id = mcshc.shop_cate2_id" +
				" left join magic_cube.shop_level_cur mcshlc on mcshlc.shop_id = mcsh.shop_id" +
				" where mcshlr.this_month_line_id is null and mcshlc.lv >= ? order by convert( wsh.name using gbk ) collate gbk_chinese_ci asc";

		List<Shop> shops = projectJdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1,lv);
			}
		}, new BeanPropertyRowMapper<Shop>(Shop.class));
		return shops;
	}

	/**
	 * 给PreparedStatement注入参数
	 * @param pstm
	 * @param shopLine
	 * @return
	 * @throws Exception
	 */
	public static PreparedStatement setIntoPstm(PreparedStatement pstm,ShopLine shopLine) throws Exception{
		pstm.setObject(1, shopLine.getShopLineId());
		pstm.setObject(2, shopLine.getShopLine());

		return pstm;
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] {"spring/applicationContext-project-dao.xml","spring/applicationContext-dao.xml","spring/applicationContext-service.xml"});
		ShopLineDao dao = (ShopLineDao)ac.getBean("shopLineDaoImpl");
		List list = dao.getThisMonthKaShop();
		System.out.println("11111111111111111111111111111----------------" + list.size());
	}
}
