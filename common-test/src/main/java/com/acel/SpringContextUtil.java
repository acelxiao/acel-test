package com.acel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 下面的这个方法上加了@Override注解，原因是继承ApplicationContextAware接口是必须实现的方法
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name)
            throws BeansException {
        return applicationContext.getBean(name);
    }

    public static Object getBean(String name, Class<?> requiredType)
            throws BeansException {

        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> clazz)
            throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    public static void replaceBean(String beanName, Object targetObj) throws NoSuchFieldException, IllegalAccessException {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext)getApplicationContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        //反射获取Factory中的singletonObjects 将该名称下的bean进行替换
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.put(beanName, targetObj);
    }
}
