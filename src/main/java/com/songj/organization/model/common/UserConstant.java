package com.songj.organization.model.common;

import java.io.Serializable;

/**
 * @ClassNamee: UserConstant
 * @Description: User常量类
 * @Author: Scott S
 * @Date: 2020-01-13 10:45
 * @Version: 1.0
 **/
public class UserConstant implements Serializable {

    public static final String local_host = "127.0.0.1";

    public static final Integer redis_port = 6379;

    //redis缓存过期时间最小值（10min）1000*60*10
    public static final Long redis_timeout_min = 600000L;

    //redis缓存过期时间最大值（30min）
    public static final Long redis_timeout_max = 1800000L;


}
