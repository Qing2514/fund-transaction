package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.fundPurchase.pojo.Ptrans;
import com.fundtrans.fundPurchase.pojo.Purchase;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    @Insert("insert into purchase values(#{purchase.id},#{purchase.user_id},#{purchase.product_id}," +
            "#{purchase.card_id},#{purchase.time},#{purchase.amount},#{purchase.count})")
    void addPurchase(@Param("purchase") Purchase purchase);

    @Delete("delete from purchase where id = #{id}")
    void deletePurchase(@Param("id") Integer id);
}
