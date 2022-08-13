package com.rbing.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 使用到其他模块的配置，需要扫描其他模板的配置类，由于其他模块配置类所在的包和当前模块的包不一致，所以需要单独引入指定扫描
 * @author rbing
 * @create 2022-08-12-15:36
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.rbing")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
