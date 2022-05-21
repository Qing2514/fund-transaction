package com.funtrans.userManage.server.mapper;

import com.fundtrans.userManage.pojo.Card;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CardMapper {
    @Insert("insert into card values(#{id},#{card_id},#{user_id},#{account})")
    public void addCard(@Param("id") int id, @Param("card_id") String card_id, @Param("user_id") String user_id, @Param("account") BigDecimal account);

    @Delete("delete from card where card_id = #{card_id}")
    public void deleteCard(@Param("card_id") String card_id);

    @Select("select * from card where card_id = #{card_id}")
    public Card selectByCardId(@Param("card_id") String card_id);

    @Update("update card set account = account + #{amount} where card_id = #{card_id}")
    public void addCardAccount(@Param("amount") BigDecimal amount, @Param("card_id") String card_id);

    @Select("select * from card where user_id = #{user_id}")
    public List<Card> findAllCard(@Param("user_id") String user_id);
}
