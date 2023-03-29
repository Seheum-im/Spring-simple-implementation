package cn.seheum.springframework.context;

import cn.seheum.springframework.beans.factory.HierarchicalBeanFactory;
import cn.seheum.springframework.beans.factory.ListableBeanFactory;
import cn.seheum.springframework.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
