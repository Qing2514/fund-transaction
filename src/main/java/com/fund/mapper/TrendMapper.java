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

    @Select("select * from trend_${productId}")
    List<Trend> findByProductId(@Param("productId") String productId);

    @Select("select * from trend_${productId} where date = #{date}")
    Trend findByProductIdAndDate(@Param("productId") String productId, @Param("date") Date date);

    @Insert("insert into trend_${productId} values(#{trend.date}, #{trend.netWorth}, #{trend.growth})")
    boolean addTrend(@Param("productId") String productId, @Param("trend") Trend trend);

    @Select("select * from trend_${productId} where date = " +
            "(select max(date) from trend_${productId}")
    Trend getLateTrend(@Param("productId") String productId);

}
