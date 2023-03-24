package com.acel.notspring.javacode;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static java.lang.System.out;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String base64JavaCode = "cGFja2FnZSBjb20uYWNlbC5ub3RzcHJpbmcuamF2YWNvZGU7CgpwdWJsaWMgY2xhc3MgVGVzdFBPSk8gewogICAgcHVibGljIFN0cmluZyB0ZXN0KCl7CiAgICAgICAgcmV0dXJuICJiYmJiYiI7CiAgICB9Cn0K";
        String javaCode = new String(Base64.getDecoder().decode(base64JavaCode), "UTF-8");
        Class<?> clazz1 = TestPOJO.class;
        Class<?> clazz2 = DynamicClassLoader.load(javaCode);

        out.println("Seems to be the same class:");
        out.println(clazz1.getName());
        out.println(clazz2.getName());
        out.println();

        out.println("But why there are 2 different class loaders:");
        out.println(clazz1.getClassLoader());
        out.println(clazz2.getClassLoader());
        out.println();

        out.println(((TestPOJO)clazz1.newInstance()).test());
        out.println(ReflectUtil.invoke("test", clazz2.newInstance()));
        out.println();
    }
}
