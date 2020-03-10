package com.is1992yc.eventbus.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.is1992yc.eventbus.events.BaseEvent;
import com.is1992yc.eventbus.service.MyEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class MyEventBusImpl implements MyEventBus {


    private ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("sub-event-pool-%d").build();
    /**
     * 用于postAnsy方法的线程池
     */
    private ExecutorService executorService = new ThreadPoolExecutor(8, 20, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), tf, new ThreadPoolExecutor.CallerRunsPolicy());

    private static Logger LOGGER = LoggerFactory.getLogger(MyEventBusImpl.class);

    /**
     * 同步的一个bus
     */
    private EventBus snyBus = new EventBus(MyEventBus.class.getSimpleName());

    /**
     * 异步执行的bus
     */
    private EventBus anysBus = null;


    public MyEventBusImpl() {
        ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("event-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(4, 16, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), tf, new ThreadPoolExecutor.CallerRunsPolicy());
        anysBus = new AsyncEventBus(pool);
    }


    public MyEventBusImpl(int coreSize, int maxSize, int queue) {
        ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("event-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(queue), tf, new ThreadPoolExecutor.CallerRunsPolicy());
        anysBus = new AsyncEventBus(pool);
    }

    public MyEventBusImpl(int coreSize, int maxSize, int queue, ExecutorService executorService) {
        ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("event-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(queue), tf, new ThreadPoolExecutor.CallerRunsPolicy());
        anysBus = new AsyncEventBus(pool);

    }



    @Override
    public void postSyc(BaseEvent event) {
        snyBus.post(event);
    }

    @Override
    public void postAnsy(BaseEvent event) {
        if (event.getLeadTime() > 0L) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(event.getLeadTime());
                    anysBus.post(event);
                } catch (InterruptedException e) {
                    LOGGER.error("Event delay " + e.getMessage());
                }
            });
        } else {
            anysBus.post(event);
        }
    }

    @Override
    public void registerSyc(Object listener) {
        snyBus.register(listener);
    }

    @Override
    public void registerAnsy(Object listener) {
        anysBus.register(listener);
    }
}
