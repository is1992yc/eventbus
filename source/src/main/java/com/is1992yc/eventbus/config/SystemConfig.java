package com.is1992yc.eventbus.config;


import com.is1992yc.eventbus.service.MyEventBus;
import com.is1992yc.eventbus.service.impl.MyEventBusImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 系统配置
 * bean的初始化
 * @author is1992yc
 */
@Configuration
public class SystemConfig {


    @Bean
    public MyEventBus createBus() {
        return new MyEventBusImpl(20, 20, 1024);
    }

}
