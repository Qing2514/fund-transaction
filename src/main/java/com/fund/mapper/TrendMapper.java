package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Trend;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrendMapper extends BaseMapper<Trend> {

    @Select("select * from trend where product_id=#{productId}")
    List<Trend> findById(@Param("productId") String productId);

    @Insert("insert into trend values(#{trend.date},#{trend.productId},#{trend.price})")
    void addTrend(@Param("trend") Trend trend);

    @Select("select * from trend where product_id=#{productId} and date=" +
            "(select max(date) from trend where product_id=#{productId}) limit 1")
    Trend getLateTrend(String productId);

}
