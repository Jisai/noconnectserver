package com.songj.organization.business.meiTuan.demo1;

/**
 * @ClassNamee: Food
 * @Description:单例美食策略
 **/
public class Food extends AbstractStrategy implements Strategy {
    private static final Food instance = new Food();
    private FoodService foodService;
    private Food() {
        register();
    }
    public static Food getInstance() {
        return instance;
    }
    @Override
    public void issue(Object... params) {
        FoodRequest request = new FoodRequest(params);
        foodService.payCoupon(request);
    }
}