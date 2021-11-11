package com.songj.organization.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Classname FruitEnum
 * @Description TODO
 * @Date 2021/11/11 上午9:58
 * @Created by admin
 */
@Getter
@AllArgsConstructor
public enum FruitEnum {

    APPLE("apple", "fruit_apple", "苹果"),
    PEAR("pear","fruit_pear", "梨"),
    PEACH("peach","fruit_peach", "桃子");

    /**
     * 系统外对接使用code
     */
    private String code;
    /**
     * 系统内部使用code
     */
    private String innerCode;
    /**
     * 名称
     */
    private String name;

    public static boolean isBelong (String code){
        if(StringUtils.isBlank(code)){
            return false;
        }
        for (FruitEnum one : FruitEnum.values()){
            if(code.equals(one.getCode())){
                return true;
            }
        }
        return false;
    }

    public static FruitEnum getEnum(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for (FruitEnum one : FruitEnum.values()){
            if(code.equals(one.getCode())){
                return one;
            }
        }
        return null;
    }

    public static FruitEnum getEnumByInnerCode(String innerCode){
        if(StringUtils.isBlank(innerCode)){
            return null;
        }
        for (FruitEnum one : FruitEnum.values()){
            if(innerCode.equals(one.getInnerCode())){
                return one;
            }
        }
        return null;
    }

    public static String getName(String code){
        FruitEnum one =  getEnum(code);
        if(one == null){
            return null;
        }
        return one.getName();
    }

}
