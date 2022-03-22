package com.songj.organization.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassNamee: OperationLog
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 17:46
 * @Version: 1.0
 **/
@Data
public class OperationLog {

    private String operId;
    // 操作模块
    private String operModul;
    // 操作类型
    private String operType;
    // 操作说明
    private String operDesc;
    // 请求方法
    private String operMethod;
    // 请求参数
    private String operRequParam;
    // 返回结果
    private String operRespParam;
    // 请求用户ID
    private String operUserId;
    // 请求用户名称
    private String operUserName;
    // 请求IP
    private String operIp;
    // 请求URI
    private String operUri;
    // 创建时间
    private Date operCreateTime;
    // 操作版本
    private String operVer;

}
