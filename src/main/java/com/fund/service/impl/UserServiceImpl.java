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

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public AjaxResult findAll() {
        return AjaxResult.success(userMapper.findAll());
    }

    @Override
    public AjaxResult findById(String id) {
        return AjaxResult.success(userMapper.findById(id));
    }

    @Override
    public AjaxResult findByFuzzyId(String id) {
        return AjaxResult.success(userMapper.findByFuzzyId(id));
    }

    @Override
    public AjaxResult findByNameAndCid(String name, String cid) {
        return AjaxResult.success(userMapper.findByNameAndCid(name, cid));
    }

    @Override
    public AjaxResult addUser(UserVo userVo) {
        // 判断用户是否已开户
        User temp = userMapper.findUser(userVo.getType(), userVo.getCtype(), userVo.getCid());
        if (temp != null) {
            return AjaxResult.error(ResultEnum.USER_ALREADY_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setId(UUIDUtil.getUUID());
        userMapper.addUser(user);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult updateUser(User user) {
        User temp = userMapper.findById(user.getId());
        if (temp == null) {
            return AjaxResult.error(ResultEnum.USER_NOT_EXIST);
        }
        userMapper.updateUser(user);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult deleteUser(String id) {
        User temp = userMapper.findById(id);
        if (temp == null) {
            return AjaxResult.error(ResultEnum.USER_NOT_EXIST);
        }
        userMapper.deleteUser(id);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult riskAssess(User user, String answer) {
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
        userMapper.updateUser(user);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getSum() {
        return AjaxResult.success(userMapper.getSum());
    }

}
