package com.funtrans.userManage.server.serviceImpl;

import com.fundtrans.exception.GlobalException;
import com.fundtrans.userManage.pojo.User;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.userManage.vo.UserSearch;
import com.fundtrans.userManage.vo.UserVo;
import com.fundtrans.utils.UUIDUtil;
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
    public RespBean findAll(int index, int limit) {
        List<User> users = new ArrayList<>();
        try {
            logger.info("查询全部用户");
            users = userMapper.findAll(index,limit);
        }catch (Exception e){
            logger.error("全部用户查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FINDALL_ERROR);
        }
        if (users == null){
            logger.info("用户列表为空");
        }
        logger.info("查询结束");
        return RespBean.success(users);
    }


    @Override
    public RespBean addUser(UserVo userVo) {
        String name = userVo.getName();
        String type = userVo.getType();
        String ctype = userVo.getCtype();
        String cid = userVo.getCid();
        if (name == null){
            logger.error("用户姓名为空");
            return RespBean.error(RespBeanEnum.USER_NAME_EMPTY);
        }
        if (type == null){
            logger.error("用户类别为空");
            return RespBean.error(RespBeanEnum.USER_TYPE_EMPTY);
        }
        if (ctype == null){
            logger.error("用户证件类型为空");
            return RespBean.error(RespBeanEnum.USER_CTYPE_EMPTY);
        }
        if (cid == null){
            logger.error("用户证件号为空");
            return RespBean.error(RespBeanEnum.USER_CID_EMPTY);
        }
        User temp = null;
        try {
            logger.info("判断用户:"+userVo.toString()+"是否已开户");
            temp = userMapper.findByFour(userVo.getName(), userVo.getType(), userVo.getCtype(), userVo.getCid());
        }catch (Exception e) {
            logger.error("四要素查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
        }
        if (temp != null){
            return RespBean.error(RespBeanEnum.USER_ALREADY_EXIST);
        }
        logger.info("添加用户:"+userVo.toString());
        String id = UUIDUtil.uuid();
        User user = new User();
        user.setId(id);
        user.setName(userVo.getName());
        user.setType(userVo.getType());
        user.setCtype(userVo.getCtype());
        user.setCid(userVo.getCid());
        user.setPassword(userVo.getPassword());
        user.setSex(userVo.getSex());
        user.setAge(userVo.getAge());
        user.setAddress(userVo.getAddress());
        user.setJob(userVo.getJob());
        user.setPhone(userVo.getPhone());
        user.setState(0);     //1表示已销户
        try {
            logger.info("添加用户:"+userVo.toString());
            userMapper.addUser(user);
        }catch (Exception e){
            logger.error("用户添加失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_INSERT_ERROR);
        }
        logger.info("添加结束");
        return RespBean.success();
    }


    @Override
    public RespBean riskAssess(User user, String answer) {
        String security = null;
        if (user == null){
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        switch (answer){
            case "A":
                security = "低风险";
                break;
            case "B":
                security = "较低风险";
                break;
            case "C":
                security = "较高风险";
                break;
            default:
                security = "高风险";
        }
        user.setSecurity(security);
        logger.info("用户风险评估");
        try {
            userMapper.updateUser(user);
        }catch (Exception e){
            logger.error("用户评估失败，用户更新失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_ASSESS_FAIL);
        }
        return RespBean.success();
    }

    @Override
    public RespBean findUser(UserSearch userSearch) {
        if (userSearch.getSymbol() == 0) {
            logger.info("四要素查询用户：" + userSearch.toString());
            User user = null;
            try {
                user = userMapper.findByFour(userSearch.getName(), userSearch.getType(), userSearch.getCtype(), userSearch.getCid());
            } catch (Exception e) {
                logger.info("四要素查询失败：" + e.getMessage());
                return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
            }
            if (user == null) {
                logger.error("用户不存在");
                return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
            }
            return RespBean.success(user);
        }else {
            logger.info("名字模糊查询");
            List<User> userList = new ArrayList<>();
            try {
                userList = userMapper.findByName(userSearch.getName()+ "%");
            }catch (Exception e){
                logger.error("名字模糊查询失败："+e.getMessage());
                return RespBean.error(RespBeanEnum.ERROR);
            }
            if (userList == null){
                logger.error("该模糊查询不存在该用户");
                return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
            }
            return RespBean.success(userList);
        }
    }

    @Override
    public RespBean updateUser(User user) {
        logger.info("修改用户:"+user.getId());
        String name = user.getName();
        String type = user.getType();
        String ctype = user.getCtype();
        String cid = user.getCid();
        if (name == null){
            return RespBean.error(RespBeanEnum.USER_NAME_EMPTY);
        }
        if (type == null){
            return RespBean.error(RespBeanEnum.USER_TYPE_EMPTY);
        }
        if (ctype == null){
            return RespBean.error(RespBeanEnum.USER_CTYPE_EMPTY);
        }
        if (cid == null){
            return RespBean.error(RespBeanEnum.USER_CID_EMPTY);
        }
        User temp = userMapper.findById(user.getId());
        if (temp == null){
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        try {
            userMapper.updateUser(user);
        }catch (Exception e){
            logger.error("用户更新失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_UPDATE_ERROR);
        }
        logger.info("修改结束");
        return RespBean.success();
    }

    @Override
    public RespBean deleteUser(User user) {
        logger.info("销户操作:"+user.toString());
        User temp = userMapper.findById(user.getId());
        if (temp == null){
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        try {
            userMapper.deleteUser(user);
        }catch (Exception e){
            logger.error("销户失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_DELETE_ERROR);
        }
        logger.info("销户结束");
        return RespBean.success();
    }
}

/*
问题：
如果要throw出异常，被捕获的异常信息并非为自己定义的全局异常GlobalException而是BaseBizException，且不会被ExceptionHandler处理
 */
