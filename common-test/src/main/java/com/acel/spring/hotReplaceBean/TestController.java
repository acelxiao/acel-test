package com.acel.spring.hotReplaceBean;

import com.acel.SpringContextUtil;
import com.acel.notspring.javacode.DynamicClassLoader;
import com.acel.notspring.javacode.ReflectUtil;
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
    private My helloService;




    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return helloService.test();
    }



    @RequestMapping("/add")
    @ResponseBody
    public String add(String code) throws Exception{
        String base64JavaCode = "cGFja2FnZSBjb20uYWNlbC5zcHJpbmcuaG90UmVwbGFjZUJlYW47CgppbXBvcnQgb3JnLnNwcmluZ2ZyYW1ld29yay5iZWFucy5mYWN0b3J5LmFubm90YXRpb24uQXV0b3dpcmVkOwppbXBvcnQgb3JnLnNwcmluZ2ZyYW1ld29yay5zdGVyZW90eXBlLlNlcnZpY2U7CgpAU2VydmljZQpwdWJsaWMgY2xhc3MgSGVsbG9TZXJ2aWNlIGltcGxlbWVudHMgTXkgewoKICAgIEBBdXRvd2lyZWQKICAgIHByaXZhdGUgSW5uZXJTZXJ2aWNlIGlubmVyU2VydmljZTsKCiAgICBAT3ZlcnJpZGUKICAgIHB1YmxpYyBTdHJpbmcgdGVzdCgpewogICAgICAgIFN5c3RlbS5vdXQucHJpbnRsbihpbm5lclNlcnZpY2UudGVzdCgpKTsKICAgICAgICByZXR1cm4gIm5ldyI7CiAgICB9Cn0K";
        String javaCode = new String(Base64.getDecoder().decode(base64JavaCode), "UTF-8");
        Class<?> clz = DynamicClassLoader.load(javaCode);
        generateSpringBean("helloService", clz);
        return "okkk";
    }

    private void generateSpringBean(String beanName, Class<?> javaClass){
        System.out.println(SpringContextUtil.getBean("helloService").getClass().getClassLoader());
        System.out.println(javaClass.getClassLoader());

        // 构建 Bean 定义对象
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(javaClass);
        AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        // 将 bean 移交给 Spring 去管理
        ConfigurableApplicationContext appCtx =
                (ConfigurableApplicationContext)SpringContextUtil.getApplicationContext();
        BeanDefinitionRegistry beanDefinitionRegistry = ((BeanDefinitionRegistry)appCtx.getBeanFactory());
        beanDefinitionRegistry.removeBeanDefinition("helloService");
        beanDefinitionRegistry.registerBeanDefinition("helloService", rawBeanDefinition);

        System.out.println(ReflectUtil.invoke("test", SpringContextUtil.getBean(SpringContextUtil.getType("helloService"))));
    }

}
