package com.is1992yc.eventbus.events;

import com.is1992yc.eventbus.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author is1992yc
 */
public class EatEvent extends BaseEvent {



    private User user;

    private String food;


    public EatEvent(User user, String food) {
        this.user = user;
        this.food = food;
        super.setLeadTime(1000L);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public String getDescription() {
        return String.format("%s : %s,with event code %s", this.getClass().getSimpleName(), user.getName(), getEventCode());
    }
}
