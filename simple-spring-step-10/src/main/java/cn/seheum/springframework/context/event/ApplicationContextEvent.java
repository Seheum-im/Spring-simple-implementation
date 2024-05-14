package cn.seheum.springframework.context.event;

import cn.seheum.springframework.context.ApplicationEvent;

/**
 * 应用上下文事件，继承ApplicationEvent
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContextEvent getApplicationContext() {
        return (ApplicationContextEvent) getSource();
    }
}
