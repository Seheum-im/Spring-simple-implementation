###Spring 中的应用上下文实现
- 05节实现了通过读取XML中的文件进行资源的解析，以及Bean对象的定义、属性值填充和注册，但是在test类中还是暴露了初始化`BeanFactory`，
应该将这个BeanFactory 实例化进行封装，不应该对外暴露，同时，已经注册后的Bean对象没有任何可以扩展的空间，从一开始就已经定好了Bean对象的一切


1. 目标：对Spring容器内的Bean对象，在实例化的过程中添加可扩展机制，例如修改Bean对象中的属性等等，
同时将`BeanFactory`实例化和XML文件中的资源解析、Bean对象定义、属性填充和注册封装成对外暴露的接口，不让直接实例化`BeanFactory` 后去调用读取配置文件


2. 设计： 通过`ClassPathXmlApplicationContext` 作为入口，调用`refresh() `，`refresh() `函数中主要对`BeanFactory`实例化和XML文件中的资源解析进行了封装，
在`AbstractApplicationContext #refresh() ` 函数里，在对Bean对象实例化前，添加了`invokeBeanFactoryPostProcessors(beanFactory)` 和 `registerBeanPostProcessors(beanFactory)`,
前者是执行了BeanFactoryPostProcessor 中 修改Bean对象定义信息进行修改，后者是注册了BeanPostProcessor，待Bean对象实例化后再去执行；
在后续的Bean对象实例化时，也就是在 `AbstractAutowireCapableBeanFactory # createBean()`中 ，等Bean对象的属性值完成填充后，再去调用`initializeBean()` 方法，
这个方法通过 BeanPostProcessor 完成了 修改/替换 Bean对象

3. 新增的内容

1） `BeanFactoryPostProcessor`（重要内容）
- 在Bean对象注册之后，但未实例化之前，对`BeanDefinition `中的信息进行修改，
也就是还未调用`createBean() `方法之前对`BeanDefinition `中的信息进行修改


2） `BeanPostProcessor` （重要内容）
- 在Bean对象实例化后修改Bean对象、或者替换Bean对象

3）`ApplicationContext`

3.1) `ConfigurableApplicationContext`
- 定义容器刷新接口 refresh(),继承`ApplicationContext`

4）`AbstractApplicationContext`
- 实现`ConfigurableApplicationContext`，实现refresh()函数，同时作为模板，制定 a. `BeanFactory`实例化和XML文件中的资源解析、Bean对象定义、属性填充和注册，
b.获取 `BeanFactory`，c.执行 BeanFactoryPostProcessor，d.注册BeanPostProcessors，e.提前实例化单例Bean对象这五个流程，
让<u>子类去实现 `BeanFactory`实例化和XML文件中的资源解析、Bean对象定义、属性填充和注册 和 获取 `BeanFactory`</u>

4.1）`AbstractRefreshableApplicationContext` 
- 继承了`AbstractApplicationContext`，实现BeanFactory 的创建，同时在refreshBeanFactory()中制定了模板，让子类去实现Bean对象的资源解析，定义、属性填充和注册Bean对象

4.2） `AbstractXmlApplicationContext`
- 继承了`AbstractRefreshableApplicationContext` ，创建`XmlBeanDefinitionReader`实例，实现Bean对象的资源解析，定义、属性填充和注册Bean对象

4.3） `ClassPathXmlApplicationContext`
- 继承了`AbstractXmlApplicationContext`，对外提供应用上下文的函数，即test类中通过创建`ClassPathXmlApplicationContext` 来进行`AbstractApplicationContext`中的一系列流程操作