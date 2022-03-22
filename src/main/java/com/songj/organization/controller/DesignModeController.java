package com.songj.organization.controller;

import com.songj.organization.business.impl.AnimalBusinessFactory;
import com.songj.organization.business.impl.FruitBusinessFactory;
import com.songj.organization.service.designmode.FactoryForFruitStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNamee: DesignModeController
 * @Description: 设计模式
 * @Author: Scott S
 * @Date: 2022-02-15 09:34
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/design/mode")
public class DesignModeController {

    //策略+工厂
    @Autowired
    private  FactoryForFruitStrategy factoryForFruitStrategy;
    //策略+工厂
    @Autowired
    private AnimalBusinessFactory animalBusinessFactory;
    //策略+工厂
    @Autowired
    private FruitBusinessFactory fruitBusinessFactory;

    //策略
    @GetMapping("/strategy/fruit")
    public String fruit(@RequestParam("code") String code){
        return factoryForFruitStrategy.getFruitStrategy(code).getType();
    }
    //策略
    @GetMapping("/strategy/animal")
    public String testAnimal(@RequestParam("code") String code){
        return animalBusinessFactory.getStrategy(code).say();
    }
    //策略
    @GetMapping("/strategy/testFruit")
    public String testFruit(@RequestParam("code") String code){
        return fruitBusinessFactory.getStrategy(code).say();
    }


}
