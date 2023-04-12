package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.User;
import com.fund.mapper.UserMapper;
import com.fund.service.UserService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.util.UUIDUtil;
import com.fund.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findByFuzzyId(String id) {
        return userMapper.findByFuzzyId(id);
    }

    @Override
    public List<User> findByNameAndCid(String name, String cid) {
        return userMapper.findByNameAndCid(name, cid);
    }

    @Override
    public boolean addUser(UserVo userVo) {
        // 判断用户是否已开户
        User temp = userMapper.findUser(userVo.getType(), userVo.getCtype(), userVo.getCid());
        if (temp != null) {
            return false;
        }
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setId(UUIDUtil.getUUID());
        return userMapper.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        User temp = userMapper.findById(user.getId());
        if (temp == null) {
            return false;
        }
        return userMapper.updateUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        User temp = userMapper.findById(id);
        if (temp == null) {
            return false;
        }
        return userMapper.deleteUser(id);
    }

    @Override
    public boolean riskAssess(User user, String answer) {
        int security;
        switch (answer) {
            case "A":
                security = 1;
                break;
            case "B":
                security = 2;
                break;
            case "C":
                security = 3;
                break;
            default:
                security = 4;
        }
        user.setSecurity(security);
        return userMapper.updateUser(user);
    }

    @Override
    public int getSum() {
        return userMapper.getSum();
    }

}
