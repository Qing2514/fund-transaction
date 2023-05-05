package com.fund.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
        return AjaxResult.success(userService.login(loginVo));
    }

    @ApiOperation("查询所有客户")
    @GetMapping("/findAll")
    public AjaxResult findAll(@PathParam("currentPage") int currentPage, @PathParam("pageSize") int pageSize) {
        IPage<User> page = userService.findAll(currentPage, pageSize);
        return AjaxResult.success(page.getRecords());
    }

    @ApiOperation("根据证件号、名称和手机号查询客户")
    @GetMapping("/findUser")
    public AjaxResult findUser(@PathParam("cid") String cid, @PathParam("name") String name,
                               @PathParam("phone") String phone) {
        return AjaxResult.success(userService.findUser(cid, name, phone));
    }

    @ApiOperation("新增客户")
    @PostMapping("/addUser")
    public AjaxResult addUser(@Valid @RequestBody UserVo userVo) {
        return userService.addUser(userVo) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CID_OR_PHONE_ALREADY_USE);
    }

    @ApiOperation("更新客户")
    @PutMapping("/updateUser")
    public AjaxResult updateUser(@RequestBody User user) {
        return userService.updateUser(user) ? AjaxResult.success() : AjaxResult.error(ResultEnum.USER_NOT_EXIST);
    }

    @ApiOperation("销户")
    @DeleteMapping("/deleteUser/{cid}")
    public AjaxResult deleteUser(@PathVariable String cid) {
        return userService.deleteUser(cid) ? AjaxResult.success() : AjaxResult.error(ResultEnum.USER_NOT_EXIST);
    }

    @ApiOperation("客户风险评估")
    @PutMapping("/riskAssess/{cid}/{answer}")
    public AjaxResult riskAssess(@PathVariable String cid, @PathVariable String answer) {
        return userService.riskAssess(cid, answer) ? AjaxResult.success() : AjaxResult.error();
    }

}
