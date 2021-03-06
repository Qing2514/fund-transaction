package com.fundtrans.infoSearch.server.mapper;

import com.fundtrans.pojo.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CardMapper {

    @Select("select * from card where user_id = #{userId}")
    List<Card> findByUserId(@Param("userId") String userId);

    @Insert("insert into card values(#{id},#{card_id},#{user_id},#{account})")
    public void addCard(@Param("id")int id, @Param("card_id") String card_id, @Param("user_id") String user_id, @Param("account") BigDecimal account);

    @Delete("delete from card where card_id = #{card_id}")
    public void deleteCard(@Param("card_id") String card_id);

    @Select("select * from card where card_id = #{card_id}")
    public Card selectByCardId(@Param("card_id") String card_id);

    @Update("update card set account = account + #{amount} where card_id = #{card_id}")
    public void addCardAccount(@Param("amount") BigDecimal amount, @Param("card_id") String card_id);

    @Select("select * from card where user_id = #{user_id}")
    public List<Card> findAllCard(@Param("user_id") String user_id);

    @Update("update card set account = #{card.account} where id = #{card.id}")
    public void update(@Param("card") Card card);

    @Select("select count(*) from card where user_id = #{user_id}")
    List<Card> getSumByUserId(@Param("user_id") String user_id);
}
