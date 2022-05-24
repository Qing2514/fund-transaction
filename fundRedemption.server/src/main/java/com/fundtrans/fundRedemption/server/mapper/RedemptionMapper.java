package com.fundtrans.fundRedemption.server.mapper;

import com.fundtrans.pojo.Redemption;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;

@Mapper
public interface RedemptionMapper {
    @Insert("insert into redemption values(#{redemption.id},#{redemption.user_id}," +
            "#{redemption.product_id},#{redemption.card_id},#{redemption.time},#{redemption.count},#{redemption.amount})")
    void addRedemption(@Param("redemption") Redemption redemption);

    @Delete("delete from redemption where id = #{redemption.id}")
    void deleteRedemption(@Param("redemption") Redemption redemption);

    @Update("update redemption set amount = count * #{netWorth} where product_id = #{productId} " +
            "and time < #{date} and time >= DATE_SUB(#{date}, INTERVAL 1 DAY)")
    int updateRedemptionByDate(@Param("date") Date date, @Param("productId") String productId,
                               @Param("netWorth") BigDecimal netWorth);
}
