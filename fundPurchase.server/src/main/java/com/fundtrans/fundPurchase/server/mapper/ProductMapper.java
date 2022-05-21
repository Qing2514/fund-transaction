package com.fundtrans.fundPurchase.server.mapper;

import com.fundtrans.fundPurchase.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product limit #{index},#{limit}")
    public List<Product> findAll(@Param("index") int index, @Param("limit") int limit);

    @Select("select * from product where id = #{id}")
    public Product findById(@Param("id") String id);

    @Insert("insert into product values(#{id},#{name},#{detail},#{networth},#{type},#{security})")
    public void addProductByField(@Param("id") String id, @Param("name") String name, @Param("detail") String detail,
                               @Param("networth") String networth, @Param("type") String type, @Param("security") String security);

    @Insert("insert into product values(#{product.id},#{product.name},#{product.detail}," +
            "#{product.networth},#{product.type},#{product.security})")
    public void addProduct(@Param("product") Product product);

    @Update("update product set name = #{product.name}, detail = #{product.detail}, networth = #{product.networth}, type = #{product.type}, " +
            "security = #{product.security} where id = #{product.id}")
    public void updateProduct(@Param("product") Product product);

    @Delete("delete from product where id = #{id}")
    public void deleteProduct(@Param("id") String id);

    @Select("select * from product where id like #{id}")
    List<Product> selectByProductId(@Param("id") String id);

    @Select("select * from product where name like #{name}")
    List<Product> selectByProductName(@Param("name") String name);
}
