package com.technical.point.list.test;

/**
 * @description: 测试Class和ClassLoader不同的类加载方式
 * @author: Mr.Gao
 * @create: 2021-04-21 22:08
 **/
public class TestClassAndClassLoader {
    public static void main(String[] args) throws Exception {
        //测试Class加载
//        testClass();
        /**
         * 测试结果：
         * 执行静态代码块....!
         * 静态方法被执行了.....
         * ================== 结束符(testClass) ========================end!!!
         */
        testClassLoader();
        /**
         * 测试结果:
         * ================= 结束符(testClassLoader) =====================end!!!
         */

        /**
         * 执行结果分析：Class.forName("")加载类时将类进行了初始化;而ClassLoader没有对类进行初始化，
         * 只是把类加载到虚拟机中
         */
    }

    /**
     * 测试Class加载
     *
     * @throws Exception
     */
    public static void testClass() throws Exception {
        Class.forName("com.technical.point.list.entity.ClassAndClassLoaderTest");
        System.out.println("================== 结束符(testClass) ========================end!!!");
    }

    /**
     * 测试ClassLoader加载方式
     *
     * @throws Exception
     */
    public static void testClassLoader() throws Exception {
        ClassLoader.getSystemClassLoader().loadClass("com.technical.point.list.entity.ClassAndClassLoaderTest");
        System.out.println("================= 结束符(testClassLoader) =====================end!!!");
    }
}
