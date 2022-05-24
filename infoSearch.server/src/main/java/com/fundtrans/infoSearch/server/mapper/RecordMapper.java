package com.fundtrans.infoSearch.server.mapper;

import com.fundtrans.pojo.Record;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RecordMapper {

    @Select("select * from record_product_view where user_id = #{userId} and product_id = #{productId}")
    Record findByUserIdAndProductId(@Param("userId") String userId, @Param("productId") String productId);

    @Insert("insert into record values(#{record.id},#{record.user_id},#{record.product_id}," +
            "#{record.card_id},#{record.num},#{record.time})")
    void addRecord(@Param("record") Record record);

    @Delete("delete from record where id = #{id}")
    void deleteRecord(@Param("id") String id);
}
