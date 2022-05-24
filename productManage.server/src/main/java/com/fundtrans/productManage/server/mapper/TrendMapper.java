package com.fundtrans.productManage.server.mapper;


import com.fundtrans.pojo.Trend;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface TrendMapper {

    @Select("select * from trend where product_id=#{product_id}")
    public List<Trend> findTrendById(@Param("product_id") String product_id);

    @Insert("insert into trend values(#{trend.id},#{trend.product_id}," +
            "#{trend.price})")
    public void addTrend(@Param("trend") Trend trend);

    @Select("select * from trend where id = #{dateId} and product_id = #{productId}")
    Trend findById(@Param("dateId") Date dateId, @Param("productId") String productId);

    @Update("update trend set price = #{netWorth} where id = #{dateId} and product_id = #{productId}")
    int updateTrend(@Param("dateId") Date dateId, @Param("productId") String productId,
                    @Param("netWorth") BigDecimal netWorth);
}
