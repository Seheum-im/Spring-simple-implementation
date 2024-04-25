### 实现Aware感知容器

- 07节中实现了在Bean对象实例化后实现初始化和销毁方法，通过实现`InitializingBean` / `DisposableBean` 接口（这两种方式不推荐，与Spring中的代码耦合）或者在
xml文件中进行配置，通过ApplicationContext进行读取解析。在08节中，新增了Bean对象对Spring容器的感知

1. 目标：获取Spring框架中的 BeanFactory、ApplicationContext、BeanClassLoader等做一些框架扩展使用，通过实现Aware接口达到感知Spring容器

2. 设计：制定Aware接口，让对应的子类去继承接口获取Spring框架中的相关对象

3. 项目新增：

1）Aware
- 标记类接口，实现该接口可以感知Spring 容器中的相关对象

2）BeanFactoryAware
- 实现此接口，即能感知到所属的 BeanFactory

3）BeanNameAware
- 实现此接口，即能感知到所属的 BeanName

4）BeanClassLoaderAware
- 实现此接口，即能感知到所属的 ClassLoader

5）ApplicationContextAware
- 实现此接口，即能感知到所属的 ApplicationContext

6）ApplicationContextAwareProcessor
- 由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到，
所以需要在 refresh()时，把 ApplicationContext 写入到一个包装的 BeanPostProcessor 中去，再由 AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization 方法调用。