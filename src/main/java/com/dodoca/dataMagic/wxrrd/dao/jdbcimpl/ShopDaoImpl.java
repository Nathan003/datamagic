package com.dodoca.dataMagic.wxrrd.dao.jdbcimpl;

import com.dodoca.dataMagic.common.dao.ShopDao;
import com.dodoca.dataMagic.common.model.Shop;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lifei on 2016/12/14.
 */
@Repository
public class ShopDaoImpl implements ShopDao{

    @Resource(name = "projectJdbcTemplate")
    private JdbcTemplate projectJdbcTemplate;

    public Shop getById(final String shopID) {
        final String sql = "select wsh.id as shop_id, wsh.`name` as shop_name, mcshc.shop_cate1_id," +
                " mcshc.shop_cate1_name, mcshc.shop_cate2_id, mcshc.shop_cate2_name," +
                " mcshlr.this_month_line_id as shop_line_id, mcshlr.this_month_line as shop_line," +
                " mcshlc.lv from wxrrd.shop wsh join magic_cube.shop mcsh on wsh.id = mcsh.shop_id" +
                " left join magic_cube.shop_line_roll2month mcshlr on wsh.id = mcshlr.shopid" +
                " left join magic_cube.shop_cate mcshc on mcsh.shop_cate_id = mcshc.shop_cate2_id" +
                " left join magic_cube.shop_level_cur mcshlc on mcshlc.shop_id = mcsh.shop_id" +
                " where wsh.id = ?";

        List<Shop> shops = projectJdbcTemplate.query(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,shopID);
            }
        }, new BeanPropertyRowMapper<Shop>(Shop.class));
        if(shops.isEmpty()){
            return null;
        }
        return shops.get(0);
    }
}
