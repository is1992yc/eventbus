package com.is1992yc.eventbus.listener;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.is1992yc.eventbus.events.EatEvent;
import org.springframework.stereotype.Service;

/**
 * @author is1992yc
 */
@Service
public class EatEventListener {


    @Subscribe
    @AllowConcurrentEvents
    private void function(EatEvent event){
        /**
         * 在这里可以处理一些对应的业务
         */
        System.out.println(String.format("%s eat %s",event.getUser().getName(),event.getFood()));
    }

}
