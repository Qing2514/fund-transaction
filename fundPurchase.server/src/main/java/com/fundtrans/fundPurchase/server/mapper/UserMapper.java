package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.userManage.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where name like #{name}")
    List<User> selectUserByName(String name);

    @Select("select * from user where id = #{id}")
    public User findById(@Param("id") String id);
}
