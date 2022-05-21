package com.fundtrans.fundRedemption.server.mapper;

import com.fundtrans.fundRedemption.pojo.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CardMapper {
    @Select("select * from card where card_id = #{card_id}")
    Card findByCardId(@Param("card_id") String card_id);

    @Update("update card set account = account + #{card.account} where id = #{card.id}")
    void updateCard(@Param("card") Card card);
}
