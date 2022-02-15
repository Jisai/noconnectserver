package com.songj.organization.annotation;

import com.songj.organization.model.enums.FruitEnum;

import java.lang.annotation.*;

/**
 * @Classname FruitAnnotation
 * @Description TODO
 * @Date 2021/11/11 上午11:09
 * @Created by admin
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitAnnotation {

    FruitEnum value();
}
