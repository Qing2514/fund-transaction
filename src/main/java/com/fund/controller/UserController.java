package com.fund.controller;


import com.fund.service.UserService;
import com.fund.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value = "UserController", tags = "")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/findAll")
    public AjaxResult findAll() {
        return AjaxResult.success(userService.getById(0));
    }

        // /**
        //  * 判断客户基本四要素信息是否完整以及是否已开户
        //  * @param userVo
        //  * @return
        //  */
        // @PostMapping("/addUser")
        // public AjaxResult addUser(@Valid @RequestBody UserVo userVo){
        //     return userService.save(userVo);
        // }

    // //    /**
    // //     * 用户风险评估
    // //     * @param user
    // //     * @param answer
    // //     * @return
    // //     */
    // //    @PostMapping("/riskAssess")
    // //    public RespBean riskAssess(User user, @RequestBody String answer){
    // //        return userService.riskAssess(user, answer);
    // //    }
    //
    //     /**
    //      * 用户更新
    //      * @param user
    //      * @return
    //      */
    //     @PutMapping("/updateUser")
    //     public RespBean updateUser(@Valid @RequestBody User user) {
    //         return userService.updateUser(user);
    //     }
    //
    //     /**
    //      * 销户
    //      * @param user
    //      * @return
    //      */
    //     @PutMapping("/deleteUser")
    //     public RespBean deleteUser(@RequestBody User user){
    //         return userService.deleteUser(user);
    //     }
    //
    //
    //     @PostMapping("/findUser")
    //     public RespBean findUser(@RequestBody UserSearch userSearch){
    //         return userService.findUser(userSearch);
    //     }
    //
    //
    //
    //     @GetMapping("/getSum")
    //     public RespBean getSum(){
    //         return userService.getSum();
    //     }
    //
    //     @PostMapping("/findById")
    //     public RespBean findById(@RequestBody User user){
    //         return userService.findById(user.getId());
    //     }
    //
    //     @PostMapping("/findByIdBubble")
    //     public RespBean findByIdBubble(@RequestBody User user){
    //         return userService.findByIdBubble(user.getId());
    //     }
}
