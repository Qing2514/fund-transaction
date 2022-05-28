package com.fundtrans.productManage.server.serviceImpl;

import com.fundtrans.pojo.Product;
import com.fundtrans.productManage.server.mapper.ProductMapper;
import com.fundtrans.productManage.service.ProductService;
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
    //查找所有的产品信息
    @Override
    public RespBean findAllProduct(int index,int limit){
        logger.info("显示所有产品信息");
        List<Product> plist=new ArrayList<Product>();
        try {
            plist = productMapper.findAllProduct(index,limit);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_FINDALL_ERROR);
        }
        if (plist == null){
            return RespBean.error(RespBeanEnum.PRODUCT_LIST_NULL);
        }
        logger.info("查询结束");
        return RespBean.success(plist);
    }


    //添加产品信息
    @Override
    public RespBean addProduct(Product product){
        logger.info("增加产品:"+product.toString());
        Product temp = productMapper.findProductById(product.getId());
        if (temp != null){//产品代码已存在
            return RespBean.error(RespBeanEnum.PRODUCT_ALREADY_EXIST);
        }
        try {
            productMapper.addProduct(product);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_INSERT_ERROR);
        }
        logger.info("产品添加成功");
        return RespBean.success();
    }

    //修改产品信息
    @Override
    public RespBean updateProduct(Product product){
        logger.info("修改产品信息:"+product.getId());
        Product temp = productMapper.findProductById(product.getId());
        if (temp == null){
            return RespBean.error(RespBeanEnum.PRODUCT_NOT_EXIST);
        }
        try {
            productMapper.updateProduct(product);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_UPDATE_ERROR);
        }
        logger.info("产品更新成功");
        return RespBean.success();
    }

    //通过id查找产品信息
    @Override
    public RespBean findProductById(String id){
        logger.info("查询产品"+id);
        Product product = null;
        try {
            product = productMapper.findProductById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_NOTFIND_ERROR);
        }
        if (product == null){
            return RespBean.error(RespBeanEnum.PRODUCT_NOT_EXIST);
        }
        logger.info("查询结束");
        return RespBean.success(product);
    }

    //通过名称查找产品信息
    @Override
    public RespBean findProductByName(String name){
        logger.info("根据名称查询产品"+ name);
        List<Product> product = new ArrayList<Product>();
        try {
            product = productMapper.findProductByName(name);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_NOTFIND_ERROR);
        }
        if (product == null){
            return RespBean.error(RespBeanEnum.PRODUCT_NOT_EXIST);
        }
        logger.info("查询结束");
        return RespBean.success(product);
    }

    //通过产品类型查找产品信息
    @Override
    public RespBean findProductByType(String type){
        logger.info("根据类型查询产品"+ type);
        List<Product> plist=new ArrayList<Product>();
        try {
            plist = productMapper.findProductByType(type);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_FINDALL_ERROR);
        }
        if (plist.isEmpty()){
            return RespBean.error(RespBeanEnum.PRODUCT_LIST_NULL);
        }
        logger.info("查询结束");
        return RespBean.success(plist);
    }

    //通过风险等级查找产品信息
    @Override
    public RespBean findProductBySecurity(String security){
        logger.info("根据风险等级查询产品"+ security);
        List<Product> plist=new ArrayList<Product>();
        try {
            plist = productMapper.findProductBySecurity(security);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_FINDALL_ERROR);
        }
        if (plist.isEmpty()){
            return RespBean.error(RespBeanEnum.PRODUCT_LIST_NULL);
        }
        logger.info("查询结束");
        return RespBean.success(plist);
    }

    //删除产品
    @Override
    public RespBean deleteProduct(String id){
        logger.info("删除产品："+ id);
        Product temp = productMapper.findProductById(id);
        if (temp == null){
            return RespBean.error(RespBeanEnum.PRODUCT_NOT_EXIST);
        }
        try {
            productMapper.deleteProduct(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_DELETE_ERROR);
        }
        logger.info("删除成功");
        return RespBean.success();
    }

    @Override
    public RespBean findBubbleId(String id) {
        logger.info("查询产品"+id);
        List<Product> products = new ArrayList<>();
        try {
            products = productMapper.findBubbleById(id + "%");
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.PRODUCT_NOTFIND_ERROR);
        }
        if (products == null){
            return RespBean.error(RespBeanEnum.PRODUCT_NOT_EXIST);
        }
        logger.info("查询结束");
        return RespBean.success(products);
    }

    @Override
    public Product outFindProductById(String id){
        logger.info("查询产品"+id);
        Product product = null;
        try {
            product = productMapper.findProductById(id);
        }catch (Exception e){
            logger.error("无指定产品"+e.getMessage());
        }
        if (product == null){
            logger.error("产品列表为空");
        }
        logger.info("查询结束");
        return product;
    }

    @Override
    public RespBean getSum() {
        return RespBean.success(productMapper.getSum());
    }

    @Override
    public List<Product> outFindAllProduct() {
        logger.info("查询所有产品");
        List<Product> products = new ArrayList<Product>();
        try {
            products = productMapper.findAllProduct(0, 1000);
        }catch (Exception e){
            logger.error("无产品"+e.getMessage());
        }
        if (products == null){
            logger.error("产品列表为空");
        }
        logger.info("查询结束");
        return products;
    }

}
