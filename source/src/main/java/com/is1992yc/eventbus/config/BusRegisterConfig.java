package com.is1992yc.eventbus.config;

import com.is1992yc.eventbus.listener.EatEventListener;
import com.is1992yc.eventbus.service.MyEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BusRegisterConfig {

    @Autowired
    private MyEventBus myEventBus;
    @Autowired
    private EatEventListener eatEventListener;


    @PostConstruct
    public void init(){
        myEventBus.registerAnsy(eatEventListener);
    }

}
