## Bean中含参构造函数实例化，采用Cglib实现

- 前面实现了bean对象的实例化，但是存在一个bug：含参构造函数无法实例化，本项目主要实现Bean对象含参构造函数的实例化


#### 1. 目标：实现Bean对象构造函数含参的实例化

#### 2. 设计：`AbstractAutowireCapableBeanFactory` 创建Bean对象的时候，通过反射获取目标Bean对象的构造参数，同时采用策略模式选择对应的动态代理去实例化


#### 3.项目新增：

1）`BeanFactory` method : `getBean(String name,Object... args)`
- 新增了`getBean(String name,Object... args)` 方法，重载`getBean()`,同时传入构造函数需要的参数值

2)`AbstractBeanFactory`
- 多个重载的`getBean()` 方法最终都会得到`doGetBean()`方法中执行创建Bean对象的一系列步骤

3)`AbstractAutowireCapableBeanFactory`
- `createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args)` 这里通过反射去捕获BeanClass中的构造方法，去判断构造方法是否含参，
然后根据策略选择进行实例化

4)`InstantiationStrategy`
- 定义实例化策略的接口 `instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args)`

5)`CglibSubclassingInstantiationStrategy`
- 实例化策略接口的实现类，使用CGLIB作为动态代理

6)`SimpleInstantiationStrategy`
- 实例化策略接口的实现类，使用JDK作为动态代理


#### 4. 测试流程
1）初始化 BeanFactory

2）注册Bean和它对应的BeanDefinition ，<u>注意此时并没有传入创建实例化好的对象，而是传入Class类</u> eg:`BeanDefinition beanDefinition = new BeanDefinition(UserService.class);`

3）调用getBean() 获取实例化后的Bean对象,注意此时传入了构造函数带参数的类型值
 
4）调用Bean对象中的方法（即UserService中的方法）