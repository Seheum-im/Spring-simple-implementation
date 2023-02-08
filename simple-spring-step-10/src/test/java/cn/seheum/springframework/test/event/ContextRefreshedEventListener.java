package cn.seheum.springframework.test.event;

import cn.seheum.springframework.context.ApplicationListener;

public class ContextRefreshedEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());

    }
}
