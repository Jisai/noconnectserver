package com.songj.organization.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Classname AnimalEnum
 * @Description TODO
 * @Date 2021/11/11 上午9:58
 * @Created by admin
 */
@Getter
@AllArgsConstructor
public enum AnimalEnum {

    CAT("animal_cat", "猫"),
    DOG("animal_dog", "狗");

    private String code;
    private String name;

    public static boolean isBelong (String code){
        if(StringUtils.isBlank(code)){
            return false;
        }
        for (AnimalEnum one : AnimalEnum.values()){
            if(code.equals(one.getCode())){
                return true;
            }
        }
        return false;
    }

    public static AnimalEnum getEnum(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for (AnimalEnum one : AnimalEnum.values()){
            if(code.equals(one.getCode())){
                return one;
            }
        }
        return null;
    }

    public static String getName(String code){
        AnimalEnum one =  getEnum(code);
        if(one == null){
            return null;
        }
        return one.getName();
    }

}
