## 通过加载资源中的配置文件进行注册Bean对象

- 04的在运行ApiTest的时候，发现对象的定义、注册和属性值的填充都是对外开放手动调用的，05则将<u>Bean对象的定义、注册和属性值的填充进行封装</u>，通过Spring配置文件进行对象实例化

#### 1. 目标：将 <u>Bean对象的定义、注册和属性值的填充进行封装</u>，通过Spring配置文件进行对象实例化

#### 2. 设计：为资源从什么途径读取制定一个策略接口 `Resource` ,同时制定资源加载器`ResourceLoader`,子类实现各种资源加载的实现。通过在 `XmlBeanDefinitionReader` 里整合类加载过程加载解析对应的XML文件，定义对应的Bean对象，后续的Bean对象注册和属性值的填充的操作不变


#### 3. 项目新增/实现：
1）`Resource` 
- 资源类型的接口，提供获取InputStream资源获取接口

1.1） `ClassPathResource`
- 根据类路径读取资源xml文件

1.2）`FileSystemResource`
- 获取文件资源

1.3） `UrlResource`
- 根据URL读取资源xml文件

2）`ResourceLoader`
- 资源加载落地实现接口，把资源处理的方式进行包装的接口


3）`DefaultResourceLoader`
- 实现资源加载，把多个资源处理方式进行包装，根据条件判断去生成对应的资源类型


4）`BeanDefinitionReader`
- Bean对象资源读取、解析、注册接口

5）`AbstractBeanDefinitionReader`
- 抽象类，提供Bean对象的注册和资源加载解析实例的获取

6）`XmlBeanDefinitionReader`
- 读取并解析XML文件中的Bean对象，定义BeanDefinition，进行属性值填充以及注册


#### 4.测试流程
1） 初始化`BeanFactory`

2） 初始化 `XmlBeanDefinitionReader`，传入`BeanFactory`，然后调用`loadBeanDefinitions()`函数，根据传入函数的参数执行不同的资源加载，
解析资源后对Bean对象进行定义、属性值填充和注册

3）调用`BeanFactory` 的`getBean()` 函数，获取对应的Bean对象实例，执行后续的方法