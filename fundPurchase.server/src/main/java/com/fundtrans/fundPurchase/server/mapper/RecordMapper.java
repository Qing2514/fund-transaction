package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.pojo.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecordMapper {
    @Insert("insert into record values(#{record.id},#{record.user_id},#{record.product_id}," +
            "#{record.card_id},#{record.num},#{record.time})")
    void addRecord(@Param("record") Record record);

    @Delete("delete from record where id = #{id}")
    void deleteRecord(@Param("id") String id);
}
