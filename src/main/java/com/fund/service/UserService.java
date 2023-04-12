package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.User;
import com.fund.util.AjaxResult;
import com.fund.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> findAll();

    User findById(String id);

    List<User> findByFuzzyId(String id);

    List<User> findByNameAndCid(String name, String cid);

    boolean addUser(UserVo userVo);

    boolean updateUser(User user);

    boolean deleteUser(String id);

    boolean riskAssess(User user, String answer);

    int getSum();

}
