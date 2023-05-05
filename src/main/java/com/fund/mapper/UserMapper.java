package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where state = 0")
    List<User> findAll();

    @Select("select * from user where cid = #{cid} and state = 0")
    User findByCid(@Param("cid") String cid);

    @Select("select * from user where phone = #{phone} and state = 0")
    User findByPhone(@Param("phone") String phone);

    @Select("select * from user where phone = #{phone} and password = #{password} and state = 0 limit 1")
    User findByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    @Select("select * from user where cid like CONCAT('%',#{cid},'%') and name like CONCAT('%',#{name},'%') " +
            "and phone like CONCAT('%',#{phone},'%') and state = 0")
    List<User> findUser(@Param("cid") String cid, @Param("name") String name, @Param("phone") String phone);

    @Insert("insert into user values(#{user.cid}, #{user.ctype}, #{user.name}, #{user.type}, " +
            "#{user.password}, #{user.phone}, #{user.security}, #{user.state})")
    boolean addUser(@Param("user") User user);

    @Update("update user set name = #{user.name}, type = #{user.type}, ctype = #{user.ctype}, " +
            "cid = #{user.cid}, phone = #{user.phone}, security = #{user.security} where cid = #{user.cid}")
    boolean updateUser(@Param("user") User user);

    @Update("update user set security = #{security} where cid = #{cid}")
    boolean updateSecurity(@Param("cid") String cid, @Param("security") Integer security);

    @Update("update user set state = 1 where cid = #{cid}")
    boolean deleteUser(@Param("cid") String cid);

}
