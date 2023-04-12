package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where state = 0")
    List<User> findAll();

    @Select("select * from user where id = #{id} and state = 0")
    User findById(@Param("id") String id);

    @Select("select * from user where id like CONCAT('%',#{id},'%') and state = 0")
    List<User> findByFuzzyId(@Param("id") String id);

    @Select("select * from user where name like CONCAT('%',#{name},'%') and cid like CONCAT('%',#{cid},'%') and " +
            "state = 0")
    List<User> findByNameAndCid(@Param("name") String name, @Param("cid") String cid);

    @Select("select * from user where type = #{type} and ctype= #{ctype} and cid = #{cid} and state = 0")
    User findUser(@Param("type") Integer type, @Param("ctype") Integer ctype, @Param("cid") String cid);

    @Insert("insert into user values(#{user.id},#{user.name},#{user.type}," +
            "#{user.ctype},#{user.cid},#{user.password},#{user.phone},#{user.age}," +
            "#{user.sex},#{user.address},#{user.job},#{user.security},#{user.state})")
    boolean addUser(@Param("user") User user);

    @Update("update user set name = #{user.name}, " +
            "password = #{user.password}, phone = #{user.phone}, age = #{user.age}, sex = #{user.sex}, " +
            "address = #{user.address}, job = #{user.job}, security = #{user.security} where id = #{user.id}")
    boolean updateUser(@Param("user") User user);

    @Update("update user set state = 1 where id = #{id}")
    boolean deleteUser(@Param("id") String id);

    @Select("select count(*) from user where state = 0")
    int getSum();

}
