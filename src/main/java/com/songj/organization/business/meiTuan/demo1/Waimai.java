package com.songj.organization.business.meiTuan.demo1;

/**
 * @ClassNamee: Waimai
 * @Description: 单例外卖策略
 **/
public class Waimai extends AbstractStrategy implements Strategy {
    private static final Waimai instance = new Waimai();
    private WaimaiService waimaiService;
    private Waimai() {
        register();
    }
    public static Waimai getInstance() {
        return instance;
    }
    @Override
    public void issue(Object... params) {
        WaimaiRequest request = new WaimaiRequest();
        // 构建入参
        request.setWaimaiReq(params);
        waimaiService.issueWaimai(request);
    }
}

