package com.songj.organization.model.common;

import org.apache.commons.lang3.StringUtils;

public enum DeletedEnum {

    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    private Integer code;

    private String mane;

    DeletedEnum(Integer code, String mane) {
        this.code = code;
        this.mane = mane;
    }

    public Integer getCode() {
        return code;
    }

    public String getMane() {
        return mane;
    }

    public static boolean isBelong (String code){
        if(StringUtils.isBlank(code)){
            return false;
        }
        for (DeletedEnum one : DeletedEnum.values()){
            if(code.equals(one.getCode())){
                return true;
            }
        }
        return false;
    }

    public static String getName(String code){
        if(StringUtils.isBlank(code)){
            return "";
        }
        for (DeletedEnum one : DeletedEnum.values()){
            if(code.equals(one.getCode())){
                return one.name();
            }
        }
        return "-";
    }

}
