package com.songj.organization.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassNamee: UserServiceImplTest
 * @Description:
 * @Author: Scott S
 * @Date: 2020-01-13 16:00
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Before
    public void before(){
        System.out.println(" = = = = = = = 开始 = = = = = = =");
    }

    @Test
    public void sayHelloWorld(){
        System.out.println(">>> hello world ! ");
    }

    @Test
    public void selectByPrimaryKey (){

    }


    @Test
    public void selectRedisByPrimaryKey (){


    }


    @After
    public void after(){
        System.out.println(" = = = = = = = 结束 = = = = = = =");
    }

}
