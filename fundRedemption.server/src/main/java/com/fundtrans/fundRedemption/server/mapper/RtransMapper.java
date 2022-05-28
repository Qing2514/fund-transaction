package com.fundtrans.fundRedemption.server.mapper;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.pojo.Rtrans;
import com.fundtrans.vo.TransSelectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface RtransMapper {
    @Insert("insert into rtrans values(#{rtrans.id},#{rtrans.user_id},#{rtrans.product_id}," +
            "#{rtrans.card_id},#{rtrans.time},#{rtrans.method},#{rtrans.count},#{rtrans.state})")
    void addRtrans(@Param("rtrans") Rtrans rtrans);

    @Select("select * from rtrans where id = #{id}")
    Rtrans findByRtransId(@Param("id") Integer id);

    @Update("update rtrans set state = #{state} where id = #{id}")
    void updateState(@Param("state") int i, @Param("id") Integer id);

    @Select("select * from rtrans where time >= #{beforeDate} and #{date} > time and state = 0")
    List<Rtrans> findTodayrtrans(@Param("date") Date date,@Param("beforeDate") Date beforeDate);


    @Select("select count(*) from rtrans")
    int getSum();

    @Select("select * from rtrans where user_id = #{user_id}")
    List<Rtrans> findByUserId(@Param("user_id") String user_id);

    List<Rtrans> findByAll(TransSelectVo transSelectVo);

}
