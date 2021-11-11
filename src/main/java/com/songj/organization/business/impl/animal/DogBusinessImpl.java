package com.songj.organization.business.impl.animal;

import com.songj.organization.business.impl.AnimalBusiness;
import org.springframework.stereotype.Component;

/**
 * @Classname DogBusinessImpl
 * @Description TODO
 * @Date 2021/11/11 上午9:20
 * @Created by admin
 */
@Component("animal_dog")
public class DogBusinessImpl implements AnimalBusiness {

    @Override
    public String say() {
        String result = "我是小猫";
        System.out.println(result);
        return result;
    }
}
