package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Card;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CardMapper extends BaseMapper<Card> {

    @Select("select * from card where user_id = #{userId} and state = 0")
    List<Card> findByUserId(@Param("userId") String userId);

    @Select("select * from card where card_id = #{cardId} and state = 0 limit 1")
    Card findByCardId(@Param("cardId") String cardId);

    @Insert("insert into card values(#{card.cardId},#{card.userId},0,#{card.password},0)")
    boolean addCard(@Param("card") Card card);

    @Update("update card set state = 1 where card_id = #{cardId}")
    boolean deleteCard(@Param("cardId") String cardId);

    @Update("update card set account = account + #{amount} where card_id = #{cardId}")
    boolean updateCard(@Param("cardId") String cardId, @Param("amount") BigDecimal amount);

}
