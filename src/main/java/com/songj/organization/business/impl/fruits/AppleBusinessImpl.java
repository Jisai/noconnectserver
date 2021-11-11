package com.songj.organization.business.impl.fruits;

import com.songj.organization.annotation.FruitAnnotation;
import com.songj.organization.business.impl.FruitBusiness;
import com.songj.organization.model.enums.FruitEnum;
import org.springframework.stereotype.Component;


@Component
@FruitAnnotation(value = FruitEnum.APPLE)
public class AppleBusinessImpl implements FruitBusiness {

    @Override
    public String say() {
        String result = "苹果";
        System.out.println(result);
        return result;
    }
}
