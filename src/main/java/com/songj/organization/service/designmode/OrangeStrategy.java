package com.songj.organization.service.designmode;

import org.springframework.stereotype.Service;

/**
 * @ClassNamee:
 * @Description:
 * @Author: Scott S
 * @Date: 2022-02-15 09:23
 * @Version: 1.0
 **/
@Service("orange")
public class OrangeStrategy implements FruitStrategy {
    @Override
    public String getType() {
        System.out.println("我是橘子。");
        return "我是橘子。";
    }
}
