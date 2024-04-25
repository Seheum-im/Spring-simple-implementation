### 执行定义好的接口或者反射调用xml配置中的方法，在Bean对象初始化时添加额外的初始化和销毁操作

- 06节里实现了对Bean对象实例化前后进行修改BeanDefinition的信息以及初始化后通过 `BeanPostProcessor` 修改Bean对象中的信息，在07节中则再给Bean对象在初始化过程中扩展一些额外的操作，
例如初始化和销毁方法

1.目标 ：本节在对Bean对象初始化过程中添加一些扩展的操作，如初始化和销毁方法，通过xml配置或者实现接口的方式

2.设计 ：Bean对象通过XML中配置初始化/销毁方法，通过反射来调用，或者实现`InitializingBean` / `DisposableBean` 中的函数来达到初始化/销毁的效果。具体在Bean对象实例化后，
也就是在 `createBean()`中的 `initializeBean()`里，执行`invokeInitMethods()` 来实现初始化，初始化的方式分为实现`InitializingBean`中的方法和反射解析XML中的初始化方法。
同时，也在 `createBean()` 方法中的新增了一个步骤：`registerDisposableBeanIfNecessary()`,注册 实现了DisposableBean 接口的 Bean 对象或者注册XML中的销毁方法。
最后的销毁方法在`DefaultSingletonBeanRegistry` 里执行`destroySingletons()`方法，进行遍历执行销毁，最终销毁的落地实现在`DisposableBeanAdapter` 的 destroy() 方法中，类似与初始化方法。


3.项目新增： 

1）`InitializingBean`
- 定义了初始化函数的接口 `afterPropertiesSet()`，子类实现该接口中的函数即可执行初始化过程

2） `DisposableBean`
- 定义了销毁函数的接口`destroy()`，子类实现该接口中的函数即可执行销毁过程

3）`DisposableBeanAdapter`
- 销毁方法的适配器，对XML中配置的销毁方法和实现`DisposableBean`的销毁方法对象进行适配，在`destroy()`执行最终的销毁

4）`ConfigurableApplicationContext # registerShutdownHook()`
- 定义钩子方法的注册接口

4.1）`ConfigurableApplicationContext # close()`
- 定义执行关闭销毁方法的接口

5）`AbstractAutowireCapableBeanFactory # invokeInitMethods()` 
- 在Bean对象实例化后执行初始化的扩展操作，对XML中配置的初始化方法和实现`InitializingBean`的销毁方法对象进行适配

6）`ConfigurableBeanFactory # destroySingletons()`
- 摧毁单例，也就是定义了执行销毁方法的接口，该函数由`AbstractApplicationContext`的父类`DefaultSingletonBeanRegistry`来实现，`destroySingletons()`函数中遍历
disposableBeans，然后执行`destroy()`方法