package com.songj.organization.service;

import com.songj.organization.service.FruitStrategy;
import org.springframework.stereotype.Service;

/**
 * @ClassNamee:
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 09:23
 * @Version: 1.0
 **/
@Service("pear")
public class PearStrategy implements FruitStrategy {
    @Override
    public String getType() {
        return "我是梨。";
    }
}
