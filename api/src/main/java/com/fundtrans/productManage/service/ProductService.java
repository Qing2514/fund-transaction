package com.fundtrans.productManage.service;


import com.fundtrans.pojo.Product;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface ProductService {
    public RespBean findAllProduct(int index,int limit);//查找所有的产品信息
    public RespBean addProduct(Product product);//添加产品信息
    public RespBean updateProduct(Product product);//修改产品信息
    public RespBean findProductById(String id);//通过id查找产品信息
    public RespBean findProductByName(String name);//通过名称查找产品信息
    public RespBean findProductByType(String type);//通过产品类型查找产品信息
    public RespBean findProductBySecurity(String security);//通过风险等级查找产品信息
    public RespBean deleteProduct(String id);//删除产品
    public RespBean findBubbleId(String id);
    public Product outFindProductById(String id);
}
