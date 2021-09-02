package com.songj.organization.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassNamee: UserDTO
 * @Description:
 * @Author: Scott S
 * @Date: 2019-10-17 17:37
 * @Version: 1.0
 **/
@Data
@ApiModel(value = "用户")
public class UserDTO  implements Serializable {

    /**
     * @Description: 用户识别码
     * @auther: Scott S
     * @date: 2019/10/17 17:41
     */
    @ApiModelProperty(value="用户识别码")
    private String userCode;
    /**
     * @Description: 用户名
     * @auther: Scott S
     * @date: 2019/10/17 17:41
     */
    @ApiModelProperty(value="用户名")
    private String userName;
    /**
     * @Description: 密码
     * @auther: Scott S
     * @date: 2019/10/17 17:41
     */
    @ApiModelProperty(value="密码")
    private String password;



}
