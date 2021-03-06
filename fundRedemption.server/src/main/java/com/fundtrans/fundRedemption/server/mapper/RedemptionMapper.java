package com.fundtrans.fundRedemption.server.mapper;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.pojo.Purchase;
import com.fundtrans.pojo.Redemption;
import com.fundtrans.vo.TransSelectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Repository
@Mapper
public interface RedemptionMapper {
    @Insert("insert into redemption values(#{redemption.id},#{redemption.user_id},#{redemption.user_name}," +
            "#{redemption.product_id},#{redemption.product_name},#{redemption.card_id},#{redemption.time},#{redemption.count},#{redemption.amount})")
    void addRedemption(@Param("redemption") Redemption redemption);

    @Delete("delete from redemption where id = #{redemption.id}")
    void deleteRedemption(@Param("redemption") Redemption redemption);

    @Update("update redemption set amount = count * #{netWorth} where product_id = #{productId} " +
            "and time < #{date} and time >= DATE_SUB(#{date}, INTERVAL 1 DAY)")
    int updateRedemptionByDate(@Param("date") Date date, @Param("productId") String productId,
                               @Param("netWorth") BigDecimal netWorth);


    @Select("select count(*) from redemption")
    int getSum();

    @Select("select * from redemption where time >= #{date1} and time <= #{date2})")
    List<Redemption> findByDate(@Param("date1") Date date1, @Param("date2") Date date2);

    @Select("select * from redemption where user_id = #{user_id}")
    List<Redemption> findByUserId(@Param("user_id") String user_id);

    @Select("select * from redemption where time >= #{date1} and time <= #{date2} and user_id = #{user_id}")
    public List<Redemption> findByDateAndUserId(@Param("date1") Date date1, @Param("date2") Date date2, @Param("user_id") String user_id);

    @Select("select * from redemption where id = #{id}")
    public Redemption findById(@Param("id") int id);

    List<Redemption> findByAll(TransSelectVo transSelectVo);

    @Select("select * from redemption where time < #{date} and time >= DATE_SUB(#{date}, INTERVAL 1 DAY)")
    List<Redemption> outFindRedemptionByDate(@Param("date") Date datetime);
}
