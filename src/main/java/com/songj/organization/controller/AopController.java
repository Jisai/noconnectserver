package com.songj.organization.controller;

import com.songj.organization.annotation.OperLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNamee: AopController
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 18:44
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "/aop")
public class AopController {


    @GetMapping("/method1")
    public String method1(){
        return "method1 success!";
    }

    @GetMapping("/method2")
    @OperLog(operModul = "我是操作模块", operType = "我是操作类型", operDesc = "我是操作说明")
    public String method2(){
        return "method2 success!";
    }


}
