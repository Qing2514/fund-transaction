package com.fundtrans.userManage.service;

import com.fundtrans.userManage.pojo.User;
import com.fundtrans.userManage.vo.UserSearch;
import com.fundtrans.userManage.vo.UserVo;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.List;

@CloudService
public interface UserService {

    RespBean findAll(int index, int limit);

    RespBean addUser(UserVo userVo);

    RespBean updateUser(User user);

    RespBean deleteUser(User user);

    RespBean riskAssess(User user, String answer);

    RespBean findUser(UserSearch userSearch);
}
