package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.User;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.UserMapper;
import com.hundsun.service.IUserService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
