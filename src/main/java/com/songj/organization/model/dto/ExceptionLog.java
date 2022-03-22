package com.songj.organization.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassNamee: ExceptionLog
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 18:34
 * @Version: 1.0
 **/
@Data
public class ExceptionLog {

    private String excId;
    // 请求参数
    private String excRequParam;
    // 请求方法名
    private String operMethod;
    // 异常名称
    private String excName;
    // 异常信息
    private String excMessage;
    // 操作员ID
    private String operUserId;
    // 操作员名称
    private String operUserName;
    // 操作URI
    private String operUri;
    // 操作员IP
    private String operIp;
    // 操作版本号
    private String operVer;
    // 发生异常时间
    private Date operCreateTime;
}
