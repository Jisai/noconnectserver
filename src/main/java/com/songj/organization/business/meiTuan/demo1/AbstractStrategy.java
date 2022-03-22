package com.songj.organization.business.meiTuan.demo1;

/**
 * @ClassNamee: AbstractStrategy
 * @Description: 抽象策略类
 **/
public abstract class AbstractStrategy implements Strategy {
    // 类注册方法
    public void register() {
        StrategyContext.registerStrategy(getClass().getSimpleName(), this);
    }
}
