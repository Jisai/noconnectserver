package com.songj.organization.service.designmode;

import org.springframework.stereotype.Component;

/**
 * @ClassNamee: AppleStrategy
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 09:23
 * @Version: 1.0
 **/
@Component("apple")
public class AppleStrategy implements FruitStrategy {
    @Override
    public String getType() {
        System.out.println("我是苹果。");
        return "我是苹果。";
    }
}
