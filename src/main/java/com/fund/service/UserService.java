package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.User;
import com.fund.util.AjaxResult;
import com.fund.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {

    AjaxResult findAll();

    AjaxResult findById(String id);

    AjaxResult findByFuzzyId(String id);

    AjaxResult findByNameAndCid(String name, String cid);

    AjaxResult addUser(UserVo userVo);

    AjaxResult updateUser(User user);

    AjaxResult deleteUser(String id);

    AjaxResult riskAssess(User user, String answer);

    AjaxResult getSum();

}
