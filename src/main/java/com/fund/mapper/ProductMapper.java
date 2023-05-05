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

    @Select("select * from product where id like CONCAT('%',#{id},'%') and name like CONCAT('%',#{name},'%') "
            + "and state = 0")
    List<Product> findProduct(@Param("id") String id, @Param("name") String name);

    @Insert("insert into product values(#{product.id}, #{product.name}, #{product.type}, #{product.security}, "
            + "#{product.netWorth}, #{product.growth}, #{product.manager}, #{product.state})")
    boolean addProduct(@Param("product") Product product);

    @Update("update product set type = #{product.type}, security = #{product.security}, manager = #{product.manager}, "
            + " where id = #{product.id}")
    boolean updateProduct(@Param("product") Product product);

    @Update("update product set state = 1 where id = #{id}")
    boolean deleteProduct(@Param("id") String id);

    @Select("select id from product where state = 0")
    List<String> getIds();

}
