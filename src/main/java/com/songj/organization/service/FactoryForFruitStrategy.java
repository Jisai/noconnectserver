package com.songj.organization.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassNamee: FactoryForFruitStrategy
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 09:29
 * @Version: 1.0
 **/
@Service
public class FactoryForFruitStrategy {


    Map<String, FruitStrategy> fruitStrategyMap = new ConcurrentHashMap<>(3);

    public FruitStrategy getFruitStrategy(String compoent){
        if(CollectionUtils.isEmpty(fruitStrategyMap) || fruitStrategyMap.get(compoent) == null){
            throw new  RuntimeException("no strategy defined !!!");
        }
        return fruitStrategyMap.get(compoent);
    }

    public FruitStrategy getFruitStrategy2(String compoent){
        if(CollectionUtils.isEmpty(fruitStrategyMap) || fruitStrategyMap.get(compoent) == null){
            throw new  RuntimeException("no strategy defined !!!");
        }
        return fruitStrategyMap.get(compoent);
    }

}
