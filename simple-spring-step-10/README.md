### 容器内新增事件Event和事件监听器 EventListener

- 09节中主要实现了bean对象为单例还是原型，以及通过FactoryBean来定制化Bean对象，在10节中，通过定义事件Event，并且对事件进行发布和监听，当事件中有信息时，需要对事件进行处理

1.目标：通过观察者模式，在Spring中实现Event定义、监听以及发布


2.设计：在AbstractApplicationContext # refresh() 中新增 初始化事件发布者、注册事件监听者到事件发布者中，和最后发布Spring容器完成刷新的通知事件。



3.项目新增：

1）ApplicationEvent
- 定义应用事件的抽象类，后续拓展的事件需要继承这个类

2）ApplicationContextEvent
- 应用上下文事件

3）ContextClosedEvent
- 应用上下文关闭事件，当ApplicationContext关闭时触发事件

4）ContextRefreshedEvent
- 应用上下文刷新事件，当ApplicationContext初始化或者刷新时触发事件

5）ApplicationEventPublisher
- 应用事件发布者接口，制定发布事件接口，通知所有的监听者listeners，由ApplicationContext接口继承，后续在AbstractApplicationContext中实现

6）ApplicationEventMulticaster
- 应用事件广播接口，制定添加监听者和移除监听者接口，以及广播应用事件给合适的监听者，AbstractApplicationContext依赖这个应用事件广播接口

7）AbstractApplicationEventMulticaster
- 抽象类，实现ApplicationEventMulticaster，负责获得适合该事件的监听者，以及判断该监听者是否符合处理该事件

8）SimpleApplicationEventMulticaster
- 继承AbstractApplicationEventMulticaster，实现广播应用事件给合适的监听者的接口

9）ApplicationListener
- 应用监听器，监听对应的事件类