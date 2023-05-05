package com.fund.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.User;
import com.fund.mapper.UserMapper;
import com.fund.service.CardService;
import com.fund.service.PurchaseService;
import com.fund.service.RedemptionService;
import com.fund.service.UserService;
import com.fund.util.UUIDUtil;
import com.fund.vo.LoginVo;
import com.fund.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CardService cardService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private RedemptionService redemptionService;

    @Override
    public User login(LoginVo loginVo) {
        return userMapper.findByPhoneAndPassword(loginVo.getPhone(), loginVo.getPassword());
    }

    @Override
    public IPage<User> findAll(int currentPage, int pageSize) {
        IPage<User> page = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(page, null);
    }

    @Override
    public User findByCid(String cid) {
        return userMapper.findByCid(cid);
    }

    @Override
    public List<User> findUser(String cid, String name, String phone) {
        return userMapper.findUser(cid, name, phone);
    }

    @Override
    public boolean addUser(UserVo userVo) {
        // 判断证件号和手机号是否已存在
        User temp1 = userMapper.findByCid(userVo.getCid());
        User temp2 = userMapper.findByPhone(userVo.getPhone());
        if (temp1 != null || temp2 != null) {
            return false;
        }
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        return userMapper.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        User temp = userMapper.findByCid(user.getCid());
        if (temp == null) {
            return false;
        }
        return userMapper.updateUser(user);
    }

    @Override
    public boolean deleteUser(String cid) {
        User temp = userMapper.findByCid(cid);
        if (temp == null) {
            return false;
        }
        // 取消申购订单
        purchaseService.cancelPurchaseByUserId(cid);
        // 取消赎回订单
        redemptionService.cancelRedemptionByUserId(cid);
        // 注销银行卡
        cardService.deleteCardByUserId(cid);
        return userMapper.deleteUser(cid);
    }

    @Override
    public boolean riskAssess(String cid, String answer) {
        Integer security;
        switch (answer) {
            case "R1":
                security = 1;
                break;
            case "R2":
                security = 2;
                break;
            case "R3":
                security = 3;
                break;
            case "R4":
                security = 4;
                break;
            default:
                security = null;
        }
        if(security == null) {
            return false;
        }
        return userMapper.updateSecurity(cid, security);
    }

}
