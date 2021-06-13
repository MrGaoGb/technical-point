package com.pc.framework.spring.framework;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 自定义上下文访问类
 * @author: Mr.Gao
 * @create: 2021-05-11 22:14
 **/
public class MrGaoApplicationContext {

    /**
     * 单例池
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    /**
     * 装载bean定义信息
     */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private Class configClazz;

    public MrGaoApplicationContext(Class configClazz) {
        this.configClazz = configClazz;

        //解析配置类 ComponentScan注解 -> 扫描路径 -> BeanDefinition 对象 -> 存入BeanDefinitionMap中

        //scan  拿到当前的主配置类之后需要进行扫描 => 把Bean对象存储到BeanDefinitionMap中
        scanMainConfigClazz(configClazz);

        //为单例对象池创建单例对象
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals(BeanDefinition.SCOPE_SINGLETON)) {
                Object bean = createBean(beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    /**
     * 扫描主配置类信息
     *
     * @param configClazz
     */
    private void scanMainConfigClazz(Class configClazz) {
        if (configClazz.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClazz.getDeclaredAnnotation(ComponentScan.class);
            //获取到包扫描路径
            String scanPath = componentScanAnnotation.value();
            scanPath = scanPath.replace(".", "/");
            //通过应用主类加载器 加载资源路径
            ClassLoader classLoader = MrGaoApplicationContext.class.getClassLoader();
            URL loaderResource = classLoader.getResource(scanPath);
            System.out.println(loaderResource);
            File file = new File(loaderResource.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    //获取Class文件的绝对路径 f.getPath()或getAbsolutePath()都可以
                    String absoluteFilePath = f.getAbsolutePath();
                    if (absoluteFilePath.endsWith(".class")) {
                        absoluteFilePath = absoluteFilePath.substring(absoluteFilePath.indexOf("com"), absoluteFilePath.indexOf(".class"));
                        String clazzNamePath = absoluteFilePath.replace("\\", ".");
                        try {
                            Class<?> aClass = classLoader.loadClass(clazzNamePath);
                            if (aClass.isAnnotationPresent(Component.class)) {
                                Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
                                String beanName = componentAnnotation.value();
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setClazz(aClass);
                                if (aClass.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = aClass.getDeclaredAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());
                                } else {
                                    beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
                                }
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            //指定包扫描路径不能为空,
            throw new NullPointerException();
        }
    }

    /**
     * 创建对象
     *
     * @param beanDefinition
     * @return
     */
    public Object createBean(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object newInstance = clazz.getDeclaredConstructor().newInstance();
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(f.getName());
                    f.setAccessible(true);
                    f.set(newInstance, bean);
                }
            }

            return newInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (BeanDefinition.SCOPE_SINGLETON.equals(beanDefinition.getScope())) {
                Object instance = singletonObjects.get(beanName);
                return instance;
            } else {
                //创建一个多例Bean
                Object bean = createBean(beanDefinition);
                return bean;
            }
        } else {
            //表示当前beanName不存在!
            throw new NullPointerException();
        }
    }
}
