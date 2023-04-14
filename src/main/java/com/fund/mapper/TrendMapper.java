package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Trend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface TrendMapper extends BaseMapper<Trend> {

    @Select("select * from trend where product_id=#{productId}")
    List<Trend> findByProductId(@Param("productId") String productId);

    @Select("select * from trend where product_id = #{productId} and date = #{date}")
    Trend findByProductIdAndDate(@Param("productId") String productId, @Param("date") Date date);

    @Insert("insert into trend values(#{trend.date},#{trend.productId},#{trend.price})")
    boolean addTrend(@Param("trend") Trend trend);

    @Select("select * from trend where product_id=#{productId} and date=" +
            "(select max(date) from trend where product_id=#{productId}) limit 1")
    Trend getLateTrend(String productId);

}
