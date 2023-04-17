package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Share;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShareMapper extends BaseMapper<Share> {

    @Select("select * from share where user_id = #{userId}")
    List<Share> findByUserId(@Param("userId") String userId);

    @Select("select * from share where user_id = #{userId} and product_id = #{productId}")
    Share findByUserIdAndProductId(@Param("userId") String userId, @Param("productId") String productId);

    @Insert("insert into share values(#{share.userId},#{share.productId},#{share.share})")
    boolean addShare(@Param("share") Share share);

    @Delete("delete from share where user_id = #{share.userId} and product_id = #{share.productId}")
    boolean deleteShare(@Param("userId") String userId, @Param("productId") String productId);

    @Update("update share set share = #{share.share} where user_id = #{share.userId} and " +
            "product_id = #{share.productId}")
    boolean updateShare(@Param("share") Share share);

}