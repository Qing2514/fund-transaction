package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.pojo.Purchase;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface PurchaseMapper {
    @Insert("insert into purchase values(#{purchase.id},#{purchase.user_id},#{purchase.product_id}," +
            "#{purchase.card_id},#{purchase.time},#{purchase.amount},#{purchase.count})")
    void addPurchase(@Param("purchase") Purchase purchase);

    @Delete("delete from purchase where id = #{id}")
    void deletePurchase(@Param("id") Integer id);

    @Update("update purchase set count = amount / #{netWorth} where product_id = #{productId} " +
            "and time < #{date} and time >= DATE_SUB(#{date}, INTERVAL 1 DAY)")
    int updatePurchaseByDate(@Param("date") Date date, @Param("productId") String productId,
                             @Param("netWorth") BigDecimal netWorth);


    @Select("select count(*) from purchase")
    int getSum();

    @Select("select * from purchase where id = #{id}")
    public Purchase findById(@Param("id") int id);
}
