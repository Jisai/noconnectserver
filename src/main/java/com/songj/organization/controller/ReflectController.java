package com.songj.organization.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.songj.organization.model.dto.ReflectDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ReflectController
 * @Description  反射
 * @Date 2021/11/11 下午6:00
 * @Created by admin
 */
@RestController
@RequestMapping(value = "/reflect")
public class ReflectController {




    @PostMapping("/test")
    public List<Object> test(){
        String param = "[\n" +
                "    {\n" +
                "        \"tableName\": \"reflect_data_one\",\n" +
                "        \"beanJsonContent\": \"{'id':'12345', 'name':'小明'}\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"tableName\": \"reflect_data_two\",\n" +
                "        \"beanJsonContent\": \"{'id':'12345', 'name':'小明'}\"\n" +
                "    }\n" +
                "]";

        List<ReflectDataDTO> list = new ArrayList<>();
        list = JSONUtil.toList(param, ReflectDataDTO.class);
        Assert.isTrue(CollectionUtil.isNotEmpty(list));
        List<Object> result = new ArrayList<>();
        for (ReflectDataDTO one : list){
            String tableName = one.getTableName();
            String beanName = StrUtil.upperFirstAndAddPre(StrUtil.toCamelCase(tableName), "");
            System.out.println("tableName" + tableName + " =》=》" + "beanName: " + beanName);
            Object object = null;
            try {
                object = ReflectUtil.newInstance("com.songj.organization.model.po." + beanName);
            } catch (UtilException e) {
                System.out.println("反射转换异常！");
                e.printStackTrace();
            }
            Object reOne = JSONUtil.toBean(one.getBeanJsonContent(), object.getClass());
            result.add(reOne);
        }
        System.out.println("result: ");
        System.out.println(JSONUtil.toJsonStr(result));
        return result;
    }
}
