package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select * from product where state = 0")
    List<Product> findAll();

    @Select("select * from product where id = #{id} and state = 0")
    Product findById(@Param("id") String id);

    @Select("select * from product where type = #{type} and name = #{name} and state = 0")
    Product findByTypeAndName(@Param("type") Integer type, @Param("name") String name);

    @Select("select * from product where id like CONCAT('%',#{id},'%') and name like CONCAT('%',#{name},'%') " +
            "and state = 0")
    List<Product> findProduct(@Param("id") String id , @Param("name") String name);

    @Insert("insert into product values(#{product.id},#{product.name}," +
            "#{product.type},#{product.security},#{product.date},#{product.detail},0)")
    boolean addProduct(@Param("product") Product product);

    @Update("update product set name = #{product.name}, detail = #{product.detail}, type = #{product.type}, " +
            "security = #{product.security} where id=#{product.id}")
    boolean updateProduct(@Param("product") Product product);

    @Update("update product set state = 1 where id = #{id}")
    boolean deleteProduct(@Param("id") String id);

    @Select("select id from product where state = 0")
    List<String> getIds();

    @Select("select count(*) from product where state = 0")
    int getSum();

}
