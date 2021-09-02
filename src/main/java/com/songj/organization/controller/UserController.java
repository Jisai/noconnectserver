package com.songj.organization.controller;

import com.songj.organization.model.dto.UserDTO;
import com.songj.organization.model.entity.UserPO;
import com.songj.organization.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNamee: UserController
 * @Description:
 * @Author: Scott S
 * @Date: 2019-10-18 14:14
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/user")
@Api(value = "UserController", description = "用户基本信息相关Api")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 获取用户信息
     * @param userCode
     * @return
     */
    @GetMapping(value = "selectByCode/{userCode}")
    @ApiOperation(value = "获取用户数据", notes = "获取用户数据", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户识别码", dataType = "String", paramType = "path")
    })
    public UserPO selectByCode(@PathVariable String userCode){
        return new UserPO();
    }


    @PostMapping(value = "/selectBySelective")
    @ApiOperation(value = "获取用户列表数据", notes = "获取用户数据", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户识别码", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String")
    })
    public List<UserPO> selectBySelective(@RequestBody UserPO record){
        return new ArrayList<UserPO>() ;
    }


    /**
     * 新增用户数据
     * @param param
     * @return
     */
    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增用户数据", notes = "新增用户数据", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "用户数据", dataType = "UserDTO", paramType = "body")
    })
    public Integer insert(@RequestBody UserDTO param){
        UserPO user = new UserPO();
        BeanUtils.copyProperties(param, user);
        return 0;
    }


    @DeleteMapping(value = "/delete/{userCode}")
    @ApiOperation(value = "删除用户数据", notes = "删除用户数据", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户识别码", dataType = "String", paramType = "path")
    })
    public Integer delete(@PathVariable String userCode){
        return 0;
    }




}
