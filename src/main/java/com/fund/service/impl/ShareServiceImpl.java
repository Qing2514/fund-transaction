package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Product;
import com.fund.entity.Share;
import com.fund.entity.User;
import com.fund.mapper.ShareMapper;
import com.fund.service.ProductService;
import com.fund.service.ShareService;
import com.fund.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Share> findAll() {
        return shareMapper.findAll();
    }

    @Override
    public List<Share> findByUserId(String userId) {
        return shareMapper.findByUserId(userId);
    }

    @Override
    public Share findByUserIdAndProductId(String userId, String productId) {
        return shareMapper.findByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<Share> findShare(String userName, String productName) {
        return shareMapper.findShare(userName, productName);
    }

    @Override
    public boolean addShare(Share share) {
        Share temp = shareMapper.findByUserIdAndProductId(share.getUserId(), share.getProductId());
        // 若不存在该记录，则新增份额记录
        if(temp == null) {
            User user = userService.findByCid(share.getUserId());
            Product product = productService.getById(share.getProductId());
            share.setUserName(user.getName());
            share.setProductName(product.getName());
            return shareMapper.addShare(share);
        }
        share.setShare(share.getShare().add(temp.getShare()));
        return shareMapper.updateShare(share);
    }

    @Override
    public boolean reduceShare(Share share) {
        Share temp = shareMapper.findByUserIdAndProductId(share.getUserId(), share.getProductId());
        // 若不存在该记录或份额不够减
        if(temp == null || share.getShare().compareTo(temp.getShare()) == 1) {
            return false;
        }
        share.setShare(temp.getShare().subtract(share.getShare()));
        // 若减去后份额为0，则删除该记录
        if(share.getShare().equals(BigDecimal.ZERO)) {
            shareMapper.deleteShare(share.getUserId(), share.getProductId());
            return true;
        }
        return shareMapper.updateShare(share);
    }

}
