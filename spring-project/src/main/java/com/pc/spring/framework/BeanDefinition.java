package com.pc.spring.framework;

/**
 * @description: Bean自定义对象
 * @author: Mr.Gao
 * @create: 2021-05-11 23:25
 **/
public class BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";

    /**
     * Bean对象
     */
    private Class clazz;

    /**
     * 作用域
     */
    private String scope;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
