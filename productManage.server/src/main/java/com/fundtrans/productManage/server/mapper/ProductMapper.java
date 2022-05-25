package com.fundtrans.productManage.server.mapper;


import com.fundtrans.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product limit #{index},#{limit}")
    public List<Product> findAllProduct(@Param("index") int index, @Param("limit") int limit);

    @Insert("insert into product values(#{product.id},#{product.name}," +
            "#{product.detail},#{product.networth},#{product.type},#{product.security},0.0000)")
    public void addProduct(@Param("product") Product product);

    @Update("update product set name = #{product.name}, " +
            "detail = #{product.detail}, networth = #{product.networth}, " +
            "type = #{product.type}, security = #{product.security}, prange = #{product.prange} where id=#{product.id}")
    public void updateProduct(@Param("product") Product product);

    @Select("select * from product where id = #{id}")
    public Product findProductById(@Param("id") String id);

    @Select("select * from product where name like #{name}")
    public List<Product> findProductByName(@Param("name") String name);

    @Select("select * from product where type = #{type}")
    public List<Product> findProductByType(@Param("type") String type);

    @Select("select * from product where security = #{security}")
    public List<Product> findProductBySecurity(@Param("security") String security);

    @Delete("delete from product where id = #{id}")
    public void deleteProduct(@Param("id") String id);

    @Select("select * from product where id like #{id}")
    Product findBubbleById(String id);
}
