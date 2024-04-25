### FactoryBean使用

- 08节中主要实现了Aware感知Spring容器中的对象，在09节中创建的Bean对象呈现出单例和原型两种形式，以及实现FactoryBean接口，实例化特定的bean对象，供第三方与Spring框架结合

1.目标：Bean对象中新增原型模式，以及实现FactoryBean接口，供第三方与Spring框架结合

2.设计：在XML文件读取解析时，默认Bean对象为单例；在创建Bean对象时判断是否为单例，以及在最后的销毁时判断是否为单例，若为单例则进行销毁，
此外单独创建一个`FactoryBeanRegistrySupport `处理FactoryBean类对象的注册操作。

3.项目新增：

1）FactoryBean
- 制定特定bean对象的接口

2）FactoryBeanRegistrySupport
- 获取特定的bean对象，以及将该bean对象注册到缓存中

3）BeanDefinition #SCOPE_SINGLETON 和 #SCOPE_PROTOTYPE 
- 新增bean对象的类型：单例/原型