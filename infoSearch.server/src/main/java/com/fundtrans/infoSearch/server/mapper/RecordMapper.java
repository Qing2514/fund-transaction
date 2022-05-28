package com.fundtrans.infoSearch.server.mapper;

import com.fundtrans.pojo.Record;
import com.fundtrans.vo.TransSelectVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface RecordMapper {

    @Insert("insert into record values(#{record.id},#{record.user_id},#{record.product_id}," +
            "#{record.card_id},#{record.num},#{record.time})")
    void addRecord(@Param("record") Record record);

    @Delete("delete from record where id = #{id}")
    void deleteRecord(@Param("id") String id);

    @Select("select count(*) from record")
    int getSum();

    @Select("select * from record where user_id = #{user_id}")
    List<Record> findRecordByUserId(@Param("user_id") String user_id);

    @Select("select * from record where time >= #{date1} and time <= #{date2} and user_id = #{user_id}")
    public List<Record> findRecordByDateAndUserId(@Param("date1") Date date1, @Param("date2") Date date2,@Param("user_id") String user_id);

    @Select("select * from record where time >= #{date1} and time <= #{date2} ")
    List<Record> findRecordByDate(@Param("date1") Date date1, @Param("date2") Date date2);

    List<Record> findByAll(TransSelectVo transSelectVo);
}
