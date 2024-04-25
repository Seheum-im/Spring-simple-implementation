package cn.seheum.springframework.context.event;

import cn.seheum.springframework.context.ApplicationEvent;
import cn.seheum.springframework.context.ApplicationListener;

/**
 * 注册Listener 和 移除Listener，以及广播事件
 */
public interface ApplicationEventMulticaster {
    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
