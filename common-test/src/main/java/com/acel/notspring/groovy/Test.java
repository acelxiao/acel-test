package com.acel.notspring.groovy;

import com.acel.notspring.javacode.DynamicClassLoader;
import com.acel.spring.hotReplaceBean.HelloService;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Test {
    private static final GroovyClassLoader groovy;

    static {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        groovy = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String base64JavaCode = "cGFja2FnZSBjb20uYWNlbC5zcHJpbmcuaG90UmVwbGFjZUJlYW47CgppbXBvcnQgb3JnLnNwcmluZ2ZyYW1ld29yay5zdGVyZW90eXBlLlNlcnZpY2U7CgpAU2VydmljZQpwdWJsaWMgY2xhc3MgSGVsbG9TZXJ2aWNlIGltcGxlbWVudHMgTXkgewogICAgQE92ZXJyaWRlCiAgICBwdWJsaWMgU3RyaW5nIHRlc3QoKXsKICAgICAgICByZXR1cm4gIm5ldyI7CiAgICB9Cn0K";
        String javaCode = new String(Base64.getDecoder().decode(base64JavaCode), "UTF-8");
        System.out.println(HelloService.class.getClassLoader());
        Class<?> clz = groovy.parseClass(javaCode);
        System.out.println(clz.getClassLoader());
    }
}
