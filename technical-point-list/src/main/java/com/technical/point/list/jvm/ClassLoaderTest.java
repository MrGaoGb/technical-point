package com.technical.point.list.jvm;

/**
 * @author: Mr.Gao
 * @date: 2021/12/28 10:26
 * @description:
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoaderTest classLoaderTest1 = new ClassLoaderTest();
        ClassLoaderTest classLoaderTest2 = new ClassLoaderTest();
        ClassLoaderTest classLoaderTest3 = new ClassLoaderTest();
        /**
         * ----通过new关键字创建了三个不同的对象
         * com.technical.point.list.jvm.ClassLoaderTest@1ef7fe8e
         * com.technical.point.list.jvm.ClassLoaderTest@6f79caec
         * com.technical.point.list.jvm.ClassLoaderTest@67117f44
         */
        System.out.println(classLoaderTest1);
        System.out.println(classLoaderTest2);
        System.out.println(classLoaderTest3);

        //Class 类模板是唯一的？
        /**
         * class类模板是唯一的
         * class com.technical.point.list.jvm.ClassLoaderTest
         * class com.technical.point.list.jvm.ClassLoaderTest
         * class com.technical.point.list.jvm.ClassLoaderTest
         */
        Class<? extends ClassLoaderTest> aClass1 = classLoaderTest1.getClass();
        Class<? extends ClassLoaderTest> aClass2 = classLoaderTest2.getClass();
        Class<? extends ClassLoaderTest> aClass3 = classLoaderTest3.getClass();
        System.out.println(aClass1);
        System.out.println(aClass2);
        System.out.println(aClass3);

        //类加载器 ClassLoader是否唯一?
        /**
         * ClassLoader唯一
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         */
        ClassLoader classLoader1 = aClass1.getClassLoader();
        ClassLoader classLoader2 = aClass1.getClassLoader();
        ClassLoader classLoader3 = aClass1.getClassLoader();
        System.out.println(classLoader1);
        System.out.println(classLoader2);
        System.out.println("(应用类加载器)Launcher$AppClassLoader: " + classLoader3);
        System.out.println("(扩展类加载器)Launcher$ExtClassLoader parent: " + classLoader3.getParent());
        System.out.println("(boot或根加载器)grandParent:" + classLoader3.getParent().getParent());
    }
}
