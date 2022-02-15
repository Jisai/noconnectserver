package com.songj.organization.model.po;

import lombok.Data;

@Data
public class UserPO {

    private Long id;

    private String userCode;

    private String userName;

    private String password;

    private Integer deleted;

}