package com.songj.organization.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname ReflectDataDTO
 * @Description 反射数据测试
 * @Date 2021/11/11 下午3:34
 * @Created by admin
 */
@Data
public class ReflectDataDTO implements Serializable {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 类名
     */
    private String beanName;
    /**
     * 实体json内容
     */
    private String beanJsonContent;

}
