package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.User;
import com.fund.mapper.UserMapper;
import com.fund.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Qing2514
 * @data 23-2-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {}
