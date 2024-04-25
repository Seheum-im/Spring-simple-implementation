## 对bean对象的属性和依赖类进行注入

- 03中仅仅实现了含参构造函数的实例化，但是bean对象中的属性值并没有进行注入，04将进行<u>对bean对象中的属性进行注入</u>


1.目标: 04主要实现Bean对象中的属性填充，在Bean对象创建时填充相对应的属性信息

2.设计：`BeanDefinition` 中新增`PropertyValues`字段，`PropertyValues`字段内保存`PropertyValue`数组，`PropertyValue`内保存属性名和属性值；
实例化Bean对象时，先创建出Bean对象，然后在 `AbstractAutowireCapableBeanFactory` 为Bean对象添加属性填充方法 `applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition)` , 
<u>如果Bean对象中的属性是引用类型，也会为该Bean对象中的引用类型中的属性进行填充</u>

3.项目新增：

1）`BeanReference`
- 封装Bean对象里的引用类型，在后续的实例化过程中进行递归创建和填充


2）`PropertyValues`
- 保存类型为`PropertyValue`的数组


3）`PropertyValue`
- 保存Bean对象中字段名以及字段值


4）`AbstractAutowireCapableBeanFactory`  method : ` applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition)`
- 进行属性填充，同时对Bean对象中引用类型字段进行递归创建和填充


4.测试流程: 

1） 初始化`BeanFactory `

2） 创建`PropertyValues`，添加该Bean对象自己的属性名称和属性值进去

3） 创建`BeanDefinition`，传入Bean对象的XXX.Class，以及`PropertyValues`

4） 通过`BeanFactory`注册Bean对象

5） 根据制定好的name去获取Bean对象实例，执行Bean对象中的函数