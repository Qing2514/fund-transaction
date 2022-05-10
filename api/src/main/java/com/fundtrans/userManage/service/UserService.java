package com.fundtrans.userManage.service;

import com.fundtrans.userManage.pojo.User;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.List;

@CloudService
public interface UserService {
    public RespBean findById(String userId);

    public RespBean findAll(int index, int limit);

    public RespBean addUser(User user);

    public RespBean updateUser(User user);

    public RespBean deleteUser(String userId);
}
