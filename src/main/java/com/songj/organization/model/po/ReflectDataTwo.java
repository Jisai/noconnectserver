package com.songj.organization.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname ReflectDataPO
 * @Description 反射数据测试
 * @Date 2021/11/11 下午3:45
 * @Created by admin
 */
@Data
public class ReflectDataTwo implements Serializable {

    private String id;

    private String code;

    private String name;

    private Boolean sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

}
