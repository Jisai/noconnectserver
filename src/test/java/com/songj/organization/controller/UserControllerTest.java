package com.songj.organization.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
/**
 * @ClassNamee: UserControllerTest
 * @Description:
 * @Author: Scott S
 * @Date: 2020-01-15 11:02
 * @Version: 1.0
 **/
//SpringBoot1.4版本之前用的是SpringJUnit4ClassRunner.class
@RunWith(SpringRunner.class)
@SpringBootTest
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        System.out.println("》 》 》 Before 《 《 《");
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    public void selectByCode() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/user/selectByCode/ROOT-01") //请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

        System.out.println("selectByCode()#responseString = " + responseString);
    }




}
