package com.rbing.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mapper接口没有实现类，MapperScan动态创建接口实现类，否则报错
 * @author rbing
 * @create 2022-08-13-16:19
 **/
@Configuration
@MapperScan("com.rbing.hosp.mapper")
public class HospConfig {
}
