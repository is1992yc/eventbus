package com.is1992yc.eventbus;


import com.is1992yc.eventbus.domain.User;
import com.is1992yc.eventbus.events.EatEvent;
import com.is1992yc.eventbus.service.MyEventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EventbusApplication.class)
public class SubscribeTest {


    @Autowired
    private MyEventBus myEventBus;

    @Test
    public void test(){


        User user = new User();

        user.setName("张三");


        myEventBus.postAnsy(new EatEvent(user,"菠萝蜜"));
        myEventBus.postAnsy(new EatEvent(user,"粑粑柑"));
        myEventBus.postAnsy(new EatEvent(user,"哈密瓜"));
        myEventBus.postAnsy(new EatEvent(user,"苹果"));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
