package com.fundtrans.userManage.client.controller;


import com.fundtrans.infoSearch.service.CardService;
import com.fundtrans.pojo.User;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.userManage.vo.UserSearch;
import com.fundtrans.userManage.vo.UserVo;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @CloudReference
    private UserService userService;
    @CloudReference
    private CardService cardService;

    @GetMapping("/findAll")
    public RespBean findAll(int index, int limit) {
        return userService.findAll(index, limit);
    }

    /**
     * 判断客户基本四要素信息是否完整以及是否已开户
     * @param userVo
     * @return
     */
    @PostMapping("/addUser")
    public RespBean addUser(@Valid @RequestBody UserVo userVo){
        return userService.addUser(userVo);
    }

//    /**
//     * 用户风险评估
//     * @param user
//     * @param answer
//     * @return
//     */
//    @PostMapping("/riskAssess")
//    public RespBean riskAssess(User user, @RequestBody String answer){
//        return userService.riskAssess(user, answer);
//    }

    /**
     * 用户更新
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public RespBean updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 销户
     * @param user
     * @return
     */
    @PutMapping("/deleteUser")
    public RespBean deleteUser(User user){
        return userService.deleteUser(user);
    }


    @PostMapping("/findUser")
    public RespBean findUser(@RequestBody UserSearch userSearch){
        return userService.findUser(userSearch);
    }

}
