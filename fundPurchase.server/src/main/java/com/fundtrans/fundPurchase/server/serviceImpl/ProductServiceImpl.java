package com.fundtrans.fundPurchase.server.serviceImpl;

import com.fundtrans.fundPurchase.pojo.Product;
import com.fundtrans.fundPurchase.server.mapper.ProductMapper;
import com.fundtrans.fundPurchase.server.mapper.UserMapper;
import com.fundtrans.fundPurchase.service.ProductService;
import com.fundtrans.userManage.pojo.User;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@CloudComponent
public class ProductServiceImpl implements ProductService {

    private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean selectByProductId(String id) {
        List<Product> products = new ArrayList<>();
        try {
            products = productMapper.selectByProductId(id);
        }catch (Exception e){
            logger.error("基金产品查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_FIND_FAIL);
        }
        return RespBean.success(products);
    }

    @Override
    public RespBean selectByProductName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            products = productMapper.selectByProductName(name);
        }catch (Exception e){
            logger.error("基金产品查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_FIND_FAIL);
        }
        return RespBean.success(products);
    }

    @Override
    public RespBean selectUserByName(String name) {
        List<User> users = new ArrayList<>();
        try {
            users = userMapper.selectUserByName(name);
        }catch (Exception e){
            logger.error("用户查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
        }
        return RespBean.success(users);
    }
}
