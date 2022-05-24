package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.pojo.Share;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ShareMapper {
    @Insert("insert into share values(#{share.user_id},#{share.product_id}," +
            "#{share.card_id},#{share.num},#{share.frozen_num})")
    void addShare(@Param("share") Share share);

    @Update("update share set num = #{share.num} where user_id = #{share.user_id} and " +
            "product_id = #{share.product_id} and card_id = #{share.card_id}")
    void updateShareAdd(@Param("share") Share share);

    @Select("select * from share where user_id = #{user_id} and product_id = #{product_id} and card_id = #{card_id}")
    Share findByThree(@Param("user_id") String userId, @Param("product_id") String productId, @Param("card_id") String cardId);
}
