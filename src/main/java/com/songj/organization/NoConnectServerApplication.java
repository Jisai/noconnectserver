package com.songj.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
public class NoConnectServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoConnectServerApplication.class, args);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~>>>>>>>>  NoConnectServer start success !!!  <<<<<<<<<<~~~~~~~~~~~~~~~~~~~~");
    }

}
