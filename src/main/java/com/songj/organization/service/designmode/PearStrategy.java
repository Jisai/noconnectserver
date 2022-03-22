package com.songj.organization.service.designmode;

import org.springframework.stereotype.Component;

/**
 * @ClassNamee:
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 09:23
 * @Version: 1.0
 **/
@Component("pear")
public class PearStrategy implements FruitStrategy {
    @Override
    public String getType() {
        System.out.println("我是梨。");
        return "我是梨。";
    }
}
