package com.songj.organization.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassNamee: RequestVO
 * @Description: 权限控制的请求vo基类
 * @Author: Scott S
 * @Date: 2019-11-15 19:22
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
public class RequestVO implements Serializable {

    /**
     * 权限标识符
     */
    private String authrity;

    /**
     * 操作人员ID
     */
    private Long adminId;
}
