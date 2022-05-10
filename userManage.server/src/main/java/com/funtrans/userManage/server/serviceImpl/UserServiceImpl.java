package com.funtrans.userManage.server.serviceImpl;

import com.fundtrans.exception.GlobalException;
import com.fundtrans.userManage.pojo.User;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.funtrans.userManage.server.mapper.UserMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@CloudComponent
public class UserServiceImpl implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean findById(String userId) {
        logger.info("查询用户"+userId);
        User user = null;
        try {
            user = userMapper.findById(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FINDBYID_ERROR);
        }
        if (user == null){
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        logger.info("查询结束");
        return RespBean.success(user);
    }

    @Override
    public RespBean findAll(int index, int limit) {
        logger.info("查询全部用户");
        List<User> users = new ArrayList<>();
        try {
            users = userMapper.findAll(index,limit);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FINDALL_ERROR);
        }
        if (users == null){
            return RespBean.error(RespBeanEnum.USER_LIST_NULL);
        }
        logger.info("查询结束");
        return RespBean.success(users);
    }
//添加用户
    @Override
    public RespBean addUser(User user) {
        logger.info("添加用户:"+user.toString());
        User temp = userMapper.findById(user.getId());
        if (temp != null){
            return RespBean.error(RespBeanEnum.USER_ALREADY_EXIST);
        }
        try {
            userMapper.addUser(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.USER_INSERT_ERROR);
        }
        logger.info("添加结束");
        return RespBean.success();
    }

    @Override
    public RespBean updateUser(User user) {
        logger.info("修改用户:"+user.getId());
        User temp = userMapper.findById(user.getId());
        if (temp == null){
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        try {
            userMapper.updateUser(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.USER_UPDATE_ERROR);
        }
        logger.info("修改结束");
        return RespBean.success();
    }

    @Override
    public RespBean deleteUser(String userId) {
        logger.info("删除用户:"+userId);
        User temp = userMapper.findById(userId);
        if (temp == null){
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        try {
            userMapper.deleteUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespBean.error(RespBeanEnum.USER_DELETE_ERROR);
        }
        logger.info("删除结束");
        return RespBean.success();
    }
}

/*
问题：
如果要throw出异常，被捕获的异常信息并非为自己定义的全局异常GlobalException而是BaseBizException，且不会被ExceptionHandler处理
 */
