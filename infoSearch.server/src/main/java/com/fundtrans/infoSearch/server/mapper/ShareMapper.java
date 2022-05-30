package com.fundtrans.infoSearch.server.mapper;

import com.fundtrans.pojo.Share;
import com.fundtrans.vo.TransSelectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShareMapper {
    @Select("select * from share where user_id = #{user_id}and product_id = #{product_id}")
    List<Share> judgeCard(@Param("user_id") String userId, @Param("product_id") String productId);

    @Select("select * from share where user_id = #{user_id} and product_id = #{product_id} and card_id = #{card_id}")
    Share findByThree(@Param("user_id") String userId, @Param("product_id") String productId, @Param("card_id") String cardId);

    @Update("update share set value = #{share.value}, frozen_num = #{share.frozen_num} where user_id = #{share.user_id} and " +
            "product_id = #{share.product_id} and  card_id = #{share.card_id}")
    void updateCount(@Param("share") Share share);

    @Delete("delete from share where user_id = #{share.user_id} and product_id = #{share.product_id} and card_id = #{share.card_id}")
    void deleteShare(@Param("share") Share share);

    @Insert("insert into share values(#{share.user_id},#{share.product_id}," +
            "#{share.name},#{share.card_id},#{share.value},#{share.frozen_num})")
    void addShare(@Param("share") Share share);

    @Update("update share set value = #{share.value} where user_id = #{share.user_id} and " +
            "product_id = #{share.product_id} and card_id = #{share.card_id}")
    void updateShareAdd(@Param("share") Share share);



    @Select("select count(*) from share")
    int getSum();

    List<Share> findByAll(TransSelectVo transSelectVo);

    @Select("select distinct product_id from share where user_id = #{user_id}")
    List<String> findByUserId(@Param("user_id") String user_id);
}