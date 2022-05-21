package com.fundtrans.fundRedemption.server.mapper;

import com.fundtrans.fundRedemption.pojo.Redemption;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RedemptionMapper {
    @Insert("insert into redemption values(#{redemption.id},#{redemption.user_id}," +
            "#{redemption.product_id},#{redemption.card_id},#{redemption.time},#{redemption.count},#{redemption.amount})")
    void addRedemption(@Param("redemption") Redemption redemption);

    @Delete("delete from redemption where id = #{redemption.id}")
    void deleteRedemption(@Param("redemption") Redemption redemption);
}
