package cn.seheum.springframework.test.event;

import cn.seheum.springframework.context.ApplicationListener;

public class ContextClosedEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());

    }
}
