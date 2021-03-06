package com.funtrans.userManage.server.mapper;

import com.fundtrans.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where state = 0 limit #{index},#{limit}")
    public List<User> findAll(@Param("index") int index, @Param("limit") int limit);

    @Select("select * from user where id = #{id} and state = 0")
    public User findById(@Param("id") String id);

    @Select("select * from user where name = #{name} and type = #{type} and ctype= #{ctype} and cid = #{cid} and state = 0")
    public User findByFour(@Param("name") String name, @Param("type") String type, @Param("ctype") String ctype, @Param("cid") String cid);

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

    @Update("update user set state = 1 where id = #{user.id}")
    public void deleteUser(@Param("user") User user);

    @Select("select * from user where name like #{name} and state = 0")
    List<User> findByName(@Param("name") String name);

    @Select("select count(*) from user where state = 0")
    int getSum();

    @Select("select * from user where id like #{id} and state = 0")
    List<User> findByIdBubble(@Param("id") String id);
}
