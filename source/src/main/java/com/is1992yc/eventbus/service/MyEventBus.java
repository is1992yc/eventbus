package com.is1992yc.eventbus.service;


import com.is1992yc.eventbus.events.BaseEvent;

public interface MyEventBus {

    /**
     * post一个同步事件
     * @param event 事件
     */
    void postSyc(BaseEvent event);
    /**
     * post一个异步事件
     * @param event 事件
     */
    void postAnsy(BaseEvent event);

    /**
     * 向bus注册一个同步观察者
     * @param listener 观察者
     */
    void registerSyc(Object listener);

    /**
     * 向bus注册一个异步观察者
     * @param listener 观察者
     */
    void registerAnsy(Object listener);

}
