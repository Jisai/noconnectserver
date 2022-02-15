package com.songj.organization.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeletedEnum {

    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    private Integer code;

    private String name;

    public static boolean isBelong (Integer code){
        if(code == null){
            return false;
        }
        for (DeletedEnum one : DeletedEnum.values()){
            if(code.intValue() == one.getCode().intValue()){
                return true;
            }
        }
        return false;
    }

    public static String getName(Integer code){
        if(code == null){
            return "";
        }
        for (DeletedEnum one : DeletedEnum.values()){
            if(code.intValue() == one.getCode().intValue()){
                return one.name();
            }
        }
        return "";
    }

}
