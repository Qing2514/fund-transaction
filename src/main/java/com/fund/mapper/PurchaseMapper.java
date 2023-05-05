package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Purchase;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {

    @Select("select * from purchase where state = #{state}")
    List<Purchase> findAll(@Param("state") Integer state);

    @Select("select * from purchase where id = #{id} and state = #{state}")
    Purchase findById(@Param("id") String id, @Param("state") Integer state);

    @Select("select * from purchase where user_id = #{userId} and state = #{state}")
    List<Purchase> findByUserId(@Param("userId") String userId, @Param("state") Integer state);

    @Select("select * from purchase where card_id = #{cardId} and state = #{state}")
    List<Purchase> findByCardId(@Param("cardId") String cardId, @Param("state") Integer state);

    @Select("select * from purchase where datetime < #{datetime} and datetime >= DATE_SUB(#{datetime}, " +
            "INTERVAL 1 DAY) and state = #{state}")
    List<Purchase> findByDate(@Param("datetime") Date datetime, @Param("state") Integer state);

    @Select("select * from purchase where id like CONCAT('%',#{id},'%') " +
            "and user_name like CONCAT('%',#{userName},'%') and product_name like CONCAT('%',#{productName},'%') " +
            "and card_id like CONCAT('%',#{cardId},'%') and datetime like CONCAT('%',#{date},'%') " +
            "and state = #{state}")
    List<Purchase> findPurchase(@Param("state") Integer state, @Param("id") String id,
                                @Param("userName") String userName, @Param("productName") String productName,
                                @Param("cardId") String cardId, @Param("date") String date);

    @Insert("insert into purchase values(#{purchase.id},#{purchase.userId},#{purchase.userName}," +
            "#{purchase.productId},#{purchase.productName},#{purchase.cardId},#{purchase.datetime}," +
            "#{purchase.amount},0,0)")
    boolean addPurchase(@Param("purchase") Purchase purchase);

    @Update("update purchase set share = #{share}, state = 1 where state = 0 and id = #{id}")
    boolean finishPurchase(@Param("id") String id, @Param("share") BigDecimal share);

    @Update("update purchase set share = #{share}, state = 1 where state = 0 " +
            "and datetime < #{datetime} and datetime >= DATE_SUB(#{datetime}, INTERVAL 1 DAY) " +
            "and product_id = #{productId}")
    boolean finishPurchaseByDate(@Param("productId") String productId, @Param("share") BigDecimal share,
                           @Param("datetime") Date datetime);

    @Update("update purchase set state = 2 where id = #{id} and state = 0")
    boolean cancelPurchase(@Param("id") String id);

    @Update("update purchase set state = 2 where state = 0 and datetime < #{datetime} " +
            "and datetime >= DATE_SUB(#{datetime}, INTERVAL 1 DAY)")
    boolean cancelPurchaseByDate(@Param("datetime") Date datetime);

}
