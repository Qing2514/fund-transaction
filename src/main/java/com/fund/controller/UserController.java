package com.fund.controller;

import com.fund.entity.User;
import com.fund.service.UserService;
import com.fund.util.AjaxResult;
import com.fund.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "UserController", tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/findAll")
    public AjaxResult findAll() {
        return userService.findAll();
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/findById/{id}")
    public AjaxResult findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @ApiOperation("根据id模糊查询用户")
    @GetMapping("/findByFuzzyId/{id}")
    public AjaxResult findByFuzzyId(@PathVariable String id) {
        return userService.findByFuzzyId(id);
    }

    @ApiOperation("根据姓名和证件号码查询用户")
    @GetMapping("/findByNameAndCid/{name}/{cid}")
    public AjaxResult findByNameAndCid(@PathVariable String name, @PathVariable String cid) {
        return userService.findByNameAndCid(name, cid);
    }

    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public AjaxResult addUser(@Valid @RequestBody UserVo userVo) {
        return userService.addUser(userVo);
    }

    @ApiOperation("更新用户")
    @PutMapping("/updateUser")
    public AjaxResult updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @ApiOperation("销户")
    @DeleteMapping("/deleteUser/{id}")
    public AjaxResult deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @ApiOperation("用户风险评估")
    @PutMapping("/riskAssess/{answer}")
    public AjaxResult riskAssess(@RequestBody User user, @PathVariable String answer) {
        return userService.riskAssess(user, answer);
    }

    @ApiOperation("获取用户总数")
    @GetMapping("/getSum")
    public AjaxResult getSum() {
        return userService.getSum();
    }

}
