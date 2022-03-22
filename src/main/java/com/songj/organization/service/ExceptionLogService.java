package com.songj.organization.service;

import cn.hutool.json.JSONUtil;
import com.songj.organization.model.dto.ExceptionLog;
import com.songj.organization.model.dto.OperationLog;
import org.springframework.stereotype.Service;

/**
 * @ClassNamee: ExceptionLogService
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 17:38
 * @Version: 1.0
 **/
@Service
public class ExceptionLogService {

    public void insert(ExceptionLog excepLog){
        System.out.println(">>> 开始插入日志...");
        System.out.println(JSONUtil.toJsonStr(excepLog));
        System.out.println("<<< 插入日志结束。");
    }
}
