## 第一步：简单实现Bean容器

> 什么是Spring bean 容器
- Bean 对象是日常业务中的类，Spring相当于一个承载bean对象的容器，负责管理bean对象的生命周期和配置，
当一个 Bean 对象被定义存放以后，再由 Spring 统一进行装配，这个过程包括 Bean 的初始化、属性填充等，最终可以完整的使用一个 Bean 实例化后对象了。

1. 目标：简单实现Spring Bean 容器，存放bean对象

2. 设计：通过BeanFactory 进行bean对象注册和获取
