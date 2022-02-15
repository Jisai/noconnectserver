package com.songj.organization.business.impl.fruits;

import com.songj.organization.annotation.FruitAnnotation;
import com.songj.organization.business.impl.FruitBusiness;
import com.songj.organization.model.enums.FruitEnum;
import org.springframework.stereotype.Component;


@Component
@FruitAnnotation(value = FruitEnum.PEAR)
public class PearBusinessImpl implements FruitBusiness {

    @Override
    public String say() {
        String result = "梨";
        System.out.println(result);
        return result;
    }
}
