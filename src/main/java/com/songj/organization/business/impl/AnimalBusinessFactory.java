package com.songj.organization.business.impl;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class AnimalBusinessFactory {

    @Autowired
    Map<String, AnimalBusiness> animalBusinessMap = new ConcurrentHashMap<>();


    public AnimalBusiness getStrategy(String animalCode){
        if(StringUtils.isBlank(animalCode)){
            throw new RuntimeException("动物策略 code 不能为空！");
        }
        if(MapUtil.isEmpty(animalBusinessMap)){
            throw new RuntimeException("动物策略 未注入bean！");
        }
        AnimalBusiness result = animalBusinessMap.get(animalCode);
        if(result == null){
            throw new RuntimeException("动物策略 code = " + animalCode + " 未查询到注入的bean！");
        }
        return result;
    }




}
