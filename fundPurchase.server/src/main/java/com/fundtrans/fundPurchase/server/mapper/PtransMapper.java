package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.vo.TransSelectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PtransMapper {

    @Select("select * from ptrans where (time >= #{beforeDate} and time <= #{date}) and state = 0 and user_id = #{user_id} and card_id = #{card_id}")
    public List<Ptrans> findTodayPtrans1(@Param("user_id") String user_id, @Param("card_id") String card_id, @Param("date") Date date, @Param("beforeDate") Date beforeDate);

    @Insert("insert into ptrans(user_id,user_name,product_id,product_name,card_id,time,method,amount,state) values(#{ptrans.user_id},#{ptrans.user_name}," +
            "#{ptrans.product_id},#{ptrans.product_name},#{ptrans.card_id},#{ptrans.time},#{ptrans.method},#{ptrans.amount},#{ptrans.state})")
    public void addPtrans(@Param("ptrans") Ptrans ptrans);

    @Select("select * from ptrans where id = #{id}")
    Ptrans findByPtransId(@Param("id") Integer id);

    @Update("update ptrans set state = #{state} where id = #{id}")
    void updateState(@Param("state") Integer state, @Param("id") Integer id);

    @Select("select * from ptrans where time >= #{beforeDate} and #{date} > time and state = 0")
    List<Ptrans> findTodayptrans(@Param("date") Date date,@Param("beforeDate") Date beforeDate);

    @Select("select * from ptrans where user_id = #{user_id}")
    List<Ptrans> findByUserId(@Param("user_id") String user_id);

    @Select("select count(*) from ptrans")
    int getSum();

    List<Ptrans> findByAll(TransSelectVo transSelectVo);
}
