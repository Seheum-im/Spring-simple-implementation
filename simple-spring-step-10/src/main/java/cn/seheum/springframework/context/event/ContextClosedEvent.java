package cn.seheum.springframework.context.event;

import cn.seheum.springframework.context.ApplicationEvent;

/**
 * 上下文关闭事件，继承ApplicationContextEvent
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }


}
