package com.funtrans.userManage.server.mapper;

import com.fundtrans.userManage.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user limit #{index},#{limit}")
    public List<User> findAll(@Param("index") int index, @Param("limit") int limit);

    @Select("select * from user where id = #{id}")
    public User findById(@Param("id") String id);

    @Insert("insert into user values(#{id},#{name},#{type},#{ctype},#{cid},#{password},#{phone},#{age},#{sex},#{address},#{job},#{security},#{state})")
    public void addUserByField(@Param("id") String id, @Param("name") String name, @Param("type") String type,
                               @Param("ctype") String ctype, @Param("cid") String cid, @Param("password") String password,
                               @Param("phone") String phone, @Param("age") int age, @Param("sex") String sex,
                               @Param("address") String address, @Param("job") String job, @Param("security") String security, @Param("state") int state);

    @Insert("insert into user values(#{user.id},#{user.name},#{user.type}," +
            "#{user.ctype},#{user.cid},#{user.password},#{user.phone},#{user.age}," +
            "#{user.sex},#{user.address},#{user.job},#{user.security},#{user.state})")
    public void addUser(@Param("user") User user);

    @Update("update user set name = #{user.name}, type = #{user.type}, ctype = #{user.ctype}, cid = #{user.cid}, " +
            "password = #{user.password}, phone = #{user.phone}, age = #{user.age}, sex = #{user.sex}, " +
            "address = #{user.address}, job = #{user.job}, security = #{user.security}, state = #{user.state} where id = #{user.id}")
    public void updateUser(@Param("user") User user);

    @Delete("delete from user where id = #{id}")
    public void deleteUser(@Param("id") String id);
}
