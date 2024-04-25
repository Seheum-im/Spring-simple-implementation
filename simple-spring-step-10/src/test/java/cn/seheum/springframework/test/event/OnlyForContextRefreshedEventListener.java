package cn.seheum.springframework.test.event;

import cn.seheum.springframework.context.ApplicationListener;
import cn.seheum.springframework.context.event.ContextRefreshedEvent;

/**
 * @description:
 * @author：Seheum
 * @date: 2024/1/18
 */
public class OnlyForContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("上下文刷新的监听器执行了，我钟爱坂本龙一先生的歌曲");
    }
}
