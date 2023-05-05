package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.User;
import com.fund.vo.LoginVo;
import com.fund.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {

    User login(LoginVo loginVo);

    List<User> findAll();

    User findByCid(String cid);

    List<User> findUser(String cid, String name, String phone);

    boolean addUser(UserVo userVo);

    boolean updateUser(User user);

    boolean deleteUser(String cid);

    boolean riskAssess(String cid, String answer);

}
