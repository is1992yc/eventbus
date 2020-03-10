package com.is1992yc.eventbus.events;

import com.is1992yc.eventbus.service.MyEventBus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * event的基础类
 * @author is1992yc
 */
@Data
public abstract class BaseEvent implements Serializable {


    private static final long serialVersionUID = -8096836248021271508L;

    private String eventCode = UUID.randomUUID().toString();

    private Date createTime = new Date();

    private long leadTime = 0L;

    /**
     * 描述
     * @return String
     */
    public abstract String getDescription();


    public void postAsnyc(MyEventBus bus) {
        Optional.ofNullable(bus).ifPresent(b -> {
            b.postAnsy(this);
        });
    }

}
