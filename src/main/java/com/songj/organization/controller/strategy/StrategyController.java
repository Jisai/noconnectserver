package com.songj.organization.controller.strategy;

import com.songj.organization.business.impl.AnimalBusinessFactory;
import com.songj.organization.business.impl.FruitBusinessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname StrategyController
 * @Description TODO
 * @Date 2021/11/11 上午10:08
 * @Created by admin
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private AnimalBusinessFactory animalBusinessFactory;

    @Autowired
    private FruitBusinessFactory fruitBusinessFactory;

    @GetMapping("/animal")
    public String testAnimal(@RequestParam("code") String code){
        return animalBusinessFactory.getStrategy(code).say();
    }

    @GetMapping("/fruit")
    public String testFruit(@RequestParam("code") String code){
        return fruitBusinessFactory.getStrategy(code).say();
    }

}
