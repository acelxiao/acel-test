package com.acel.notspring.javacode;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class DynamicClassLoader extends URLClassLoader {

    Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

    public DynamicClassLoader(Map<String, byte[]> classBytes) {
        super(new URL[0], DynamicClassLoader.class.getClassLoader());
        this.classBytes.putAll(classBytes);
    }

    /**
     * 对外提供的工具方法，加载指定的java源码，得到Class对象
     * @param javaSrc java源码
     * @return
     */
    public static Class<?> load(String javaSrc) throws ClassNotFoundException {
        /**
         * 先试用动态编译工具，编译java源码，得到类的全限定名和class字节码的字节数组信息
         */
        Map<String, byte[]> bytecode = DynamicCompiler.compile(javaSrc);
        if(bytecode != null) {
            /**
             * 传入动态类加载器
             */
            DynamicClassLoader classLoader = new DynamicClassLoader(bytecode);
            /**
             * 加载得到Class对象
             */
            String clzName = bytecode.keySet().iterator().next();
            return classLoader.loadClass(clzName);
        } else {
            throw new ClassNotFoundException("can not found class");
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = classBytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        classBytes.remove(name);
        return defineClass(name, buf, 0, buf.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        byte[] newClassData = classBytes.get(name);
        if (newClassData == null){
            return super.loadClass(name);
        }
        return myloadClass(newClassData, name);

    }

    public Class<?> myloadClass(byte[] classData, String name) {
        Class<?> clazz = defineClass(name, classData, 0, classData.length);
        if (clazz != null) {
            if (clazz.getPackage() == null) {
                definePackage(name.replaceAll("\\.\\w+$", ""), null, null, null, null, null, null, null);
            }
            resolveClass(clazz);
        }
        return clazz;
    }

}
