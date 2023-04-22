package com.fund.controller;

import com.fund.entity.User;
import com.fund.service.UserService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.vo.LoginVo;
import com.fund.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Api(value = "UserController", tags = "客户模块")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("客户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginVo loginVo) {
        return  AjaxResult.success(userService.login(loginVo));
    }

    @ApiOperation("查询所有客户")
    @GetMapping("/findAll")
    public AjaxResult findAll() {
        return AjaxResult.success(userService.findAll());
    }

    @ApiOperation("根据id查询客户")
    @GetMapping("/findById/{id}")
    public AjaxResult findById(@PathVariable String id) {
        return AjaxResult.success(userService.findById(id));
    }

    @ApiOperation("根据id模糊查询客户")
    @GetMapping("/findByFuzzyId/{id}")
    public AjaxResult findByFuzzyId(@PathVariable String id) {
        return AjaxResult.success(userService.findByFuzzyId(id));
    }

    @ApiOperation("根据姓名和证件号码查询客户")
    @GetMapping("/findByNameAndCid")
    public AjaxResult findByNameAndCid(@PathParam("name") String name, @PathParam("cid") String cid) {
        return AjaxResult.success(userService.findByNameAndCid(name, cid));
    }

    @ApiOperation("新增客户")
    @PostMapping("/addUser")
    public AjaxResult addUser(@Valid @RequestBody UserVo userVo) {
        return userService.addUser(userVo) ? AjaxResult.success() : AjaxResult.error(ResultEnum.USER_ALREADY_EXIST);
    }

    @ApiOperation("更新客户")
    @PutMapping("/updateUser")
    public AjaxResult updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user) ? AjaxResult.success() : AjaxResult.error(ResultEnum.USER_NOT_EXIST);
    }

    @ApiOperation("销户")
    @DeleteMapping("/deleteUser/{id}")
    public AjaxResult deleteUser(@PathVariable String id) {
        return userService.deleteUser(id) ? AjaxResult.success() : AjaxResult.error(ResultEnum.USER_NOT_EXIST);
    }

    @ApiOperation("客户风险评估")
    @PutMapping("/riskAssess/{answer}")
    public AjaxResult riskAssess(@RequestBody User user, @PathVariable String answer) {
        return userService.riskAssess(user, answer) ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("获取客户总数")
    @GetMapping("/getSum")
    public AjaxResult getSum() {
        return AjaxResult.success(userService.getSum());
    }

}
