package com.fundtrans.infoSearch.server.mapper;

import com.fundtrans.pojo.Share;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShareMapper {
    @Select("select * from share where user_id = #{user_id}and product_id = #{product_id}")
    List<Share> judgeCard(@Param("user_id") String userId, @Param("product_id") String productId);

    @Select("select * from share where user_id = #{user_id} and product_id = #{product_id} and card_id = #{card_id}")
    Share findByThree(@Param("user_id") String userId, @Param("product_id") String productId, @Param("card_id") String cardId);

    @Update("update share set num = #{share.num}, frozen_num = #{share.frozen_num} where user_id = #{share.user_id} and " +
            "product_id = #{share.product_id} and  card_id = #{share.card_id}")
    void updateCount(@Param("share") Share share);

    @Delete("delete from share where user_id = #{share.user_id} and product_id = #{share.product_id} and card_id = #{share.card_id}")
    void deleteShare(@Param("share") Share share);

    @Insert("insert into share values(#{share.user_id},#{share.product_id}," +
            "#{share.card_id},#{share.num},#{share.frozen_num})")
    void addShare(@Param("share") Share share);

    @Update("update share set num = #{share.num} where user_id = #{share.user_id} and " +
            "product_id = #{share.product_id} and card_id = #{share.card_id}")
    void updateShareAdd(@Param("share") Share share);

}