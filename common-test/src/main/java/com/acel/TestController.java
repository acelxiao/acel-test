package com.acel;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class TestController {

    @Autowired
    private HelloService helloService;


    private static final GroovyClassLoader groovy;

    static {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        groovy = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return helloService.test();
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2() {
        return ((Hello)SpringContextUtil.getBean("testBean")).test();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(String code) throws Exception{
        Class clz = groovy.parseClass(new String(Base64.getDecoder().decode(code), "UTF-8"));
        generateSpringBean("testBean", clz);
        return "okkk";
    }

    private void generateSpringBean(String beanName, Class<?> javaClass){
        // 构建 Bean 定义对象
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(javaClass);
        AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        // 将 bean 移交给 Spring 去管理
        ConfigurableApplicationContext appCtx =
                (ConfigurableApplicationContext)SpringContextUtil.getApplicationContext();
        ((BeanDefinitionRegistry)appCtx.getBeanFactory()).registerBeanDefinition(beanName, rawBeanDefinition);
    }

}
