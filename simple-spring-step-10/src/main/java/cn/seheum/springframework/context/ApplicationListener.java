package cn.seheum.springframework.context;

import java.util.EventListener;

/**
 * 监听器接口，处理应用事件
 * Listener 中 监听的泛型事件都是继承于ApplicationEvent
 * @param <E>
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
