package com.songj.organization.business.impl;

import cn.hutool.core.map.MapUtil;
import com.songj.organization.annotation.FruitAnnotation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class FruitBusinessFactory {

    /**
     * 默认是<"appleBusinessImpl", "Bean">...
     */
    @Autowired
    Map<String, FruitBusiness> fruitBusinessMap = new ConcurrentHashMap<>();


    /**
     * ① 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
     * PostConstruct在构造函数之后执行，init（）方法之前执行。PreDestroy（）方法在destroy（）方法知性之后执行。
     *
     * ② 如果一个类A中有个成员变量p被@Autowried注解，那么@Autowired注入是发生在A的构造方法执行完之后的。
     *
     * ③ 如果想在生成对象时完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么就无法在构造函数中实现。
     * 为此，可以使用@PostConstruct注解一个方法来完成初始化，@PostConstruct注解的方法将会在依赖注入完成后被自动调用。
     *
     */
    @PostConstruct
    public void autowiredAnnotationStrategy(){
        Map<String, FruitBusiness> fruitCodeBusinessMap = new ConcurrentHashMap<>();
        fruitBusinessMap.forEach((key, value) ->{
            /**
             * 开启aop之后，此处不能用value.getClass().getAnnotation(value.getClass(),DataSynchStrategy.class)获取不到。
             * 使用 org.springframework.core.annotation.AnnotationUtils.findAnnotation获取。
             */
            FruitAnnotation fruitAnnotation = AnnotationUtils.findAnnotation(value.getClass(), FruitAnnotation.class);
            fruitCodeBusinessMap.put(fruitAnnotation.value().getCode(), value);
        });
        fruitBusinessMap = fruitCodeBusinessMap;
    }


    public FruitBusiness getStrategy(String code){
        if(StringUtils.isBlank(code)){
            throw new RuntimeException("水果策略 code 不能为空！");
        }
        if(MapUtil.isEmpty(fruitBusinessMap)){
            throw new RuntimeException("水果策略 未注入bean！");
        }
        FruitBusiness result = fruitBusinessMap.get(code);
        if(result == null){
            throw new RuntimeException("水果策略 code = " + code + " 未查询到注入的bean！");
        }
        return result;
    }




}
