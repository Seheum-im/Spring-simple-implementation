## 第二步：实现Bean对象的定义、注册和获取

#### 1. 目标：把Bean对象的创建交给容器来执行，而不是单独传输一个实例化后的Bean对象，通过获取Bean对象的Class，通过反射newInstant()创建

#### 2. 设计：`BeanDefinition`将字段`Object`类型换成`Class`，同时制定好获取Bean实例化后对象的模板，(`getBean()`)在获取Bean实例化后的对象时先去单例中获取`getSingleton()`，如果单例中没有实例化后的对象，则容器自行创建对象 



#### 3. 项目新增

1） `AbstractBeanFactory`
- 容器使`AbstractBeanFactory`类成为模板类类成为模板类，实现了`BeanFactory`中的接口方法，制定好了一系列的Bean对象实例化的流程，
让子类去实现`getBeanDefinition()` 和 `createBean()` 

2）`AbstractAutowireCapableBeanFactory`
- `AbstractAutowireCapableBeanFactory` 主要实现了<u>模板方法</u>中的`createBean()`,实例化Bean对象，实例化后的对象添加为单例Bean

3）`DefaultListableBeanFactory`
- `DefaultListableBeanFactory` 主要实现了<u>模板方法</u>中的`getBeanDefinition()`,通过BeanName去获取Bean对象对应的`BeanDefinition`,同时还有`registryBeanDefinition()`，在map中根据BeanName <u>注册了`BeanDefinition`</u>

4） `SingletonBeanRegistry` 
-  `SingletonBeanRegistry` 获得已经注册的单例Bean，定义了`getSingleton()`接口

5）`DefaultSingletonBeanRegistry`
- `DefaultSingletonBeanRegistry` 实现`SingletonBeanRegistry`,为子类暴露出注册单例Bean的函数`addSingleton()`

6）`BeanDefinitionRegistry`
- `BeanDefinitionRegistry` 定义了`registryBeanDefinition()`接口，根据BeanName注册BeanDefinition


#### 4. 测试执行流程
1）初始化 BeanFactory

2）注册Bean和它对应的BeanDefinition ，<u>注意此时并没有传入创建实例化好的对象，而是传入Class类</u> eg:`BeanDefinition beanDefinition = new BeanDefinition(UserService.class);`

3）调用getBean() 获取实例化后的Bean对象
 
4）调用Bean对象中的方法（即UserService中的方法）
