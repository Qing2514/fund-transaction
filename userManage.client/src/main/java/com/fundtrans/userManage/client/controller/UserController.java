package com.fundtrans.userManage.client.controller;


import com.fundtrans.userManage.pojo.User;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @CloudReference
    private UserService userService;

    @GetMapping("/findAll/{index}/{limit}")
    public RespBean findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return userService.findAll(index, limit);
    }

    @GetMapping("findById/{id}")
    public RespBean findById(@PathVariable("id") String userId) {
        return userService.findById(userId);
    }

    @PostMapping("/addUser")
    public RespBean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public RespBean deleteUser(@PathVariable("id") String userId){
        return userService.deleteUser(userId);
    }
}
