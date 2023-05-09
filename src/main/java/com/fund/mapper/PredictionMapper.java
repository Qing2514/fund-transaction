package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Prediction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PredictionMapper extends BaseMapper<Prediction> {

    @Select("select * from prediction where product_id = #{productId}")
    List<Prediction> findByProductId(@Param("productId") String productId);

    @Insert("insert into prediction values(#{prediction.date}, #{prediction.productId}, #{prediction.netWorth}, " +
            "#{prediction.growth})")
    boolean addPrediction(@Param("prediction") Prediction prediction);

    @Delete("delete from prediction where product_id = #{productId}")
    boolean deletePrediction(@Param("productId") String productId);

}
