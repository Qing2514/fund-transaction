package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.pojo.Purchase;
import com.fundtrans.vo.TransSelectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PurchaseMapper {
    @Insert("insert into purchase values(#{purchase.id},#{purchase.user_id},#{purchase.user_name},#{purchase.product_id}," +
            "#{purchase.product_name},#{purchase.card_id},#{purchase.time},#{purchase.amount},#{purchase.count})")
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

    List<Purchase> findByAll(TransSelectVo transSelectVo);

    @Select("select * from purchase where time < #{date} and time >= DATE_SUB(#{date}, INTERVAL 1 DAY)")
    List<Purchase> outFindPurchaseByDate(@Param("date") Date datetime);
}
