package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Redemption;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface RedemptionMapper extends BaseMapper<Redemption> {

    @Select("select * from Redemption where id = #{id} and state = #{state}")
    Redemption findById(@Param("id") String id, @Param("state") Integer state);

    @Select("select * from Redemption where user_id = #{userId} and state = #{state}")
    List<Redemption> findByUserId(@Param("userId") String userId, @Param("state") Integer state);

    @Select("select * from Redemption where card_id = #{cardId} and state = #{state}")
    List<Redemption> findByCardId(@Param("cardId") String cardId, @Param("state") Integer state);

    @Select("select * from Redemption where datetime < #{datetime} and datetime >= DATE_SUB(#{datetime}, " +
            "INTERVAL 1 DAY) and state = #{state}")
    List<Redemption> findByDate(@Param("datetime") Date datetime, @Param("state") Integer state);

    @Insert("insert into Redemption values(#{Redemption.id},#{Redemption.userId},#{Redemption.userName}," +
            "#{Redemption.productId},#{Redemption.productName},#{Redemption.cardId},#{Redemption.datetime}," +
            "0,#{Redemption.share},0)")
    boolean addRedemption(@Param("Redemption") Redemption redemption);

    @Update("update Redemption set amount = share * #{netWorth}, state = 1 where state = 0 " +
            "and datetime < #{datetime} and datetime >= DATE_SUB(#{datetime}, INTERVAL 1 DAY) " +
            "and product_id = #{productId}")
    boolean finishRedemption(@Param("productId") String productId, @Param("netWorth") BigDecimal netWorth,
                           @Param("datetime") Date datetime);

    @Update("update Redemption set state = 2 where id = #{id} and state = 0")
    boolean cancelRedemption(@Param("id") String id);

}
