#### Spring源码分析

##### 常见问题
* 1.SpringIOC之Bean的生命周期
> class(UserService.class) -->推断构造方法(先根据byType -> 再根据byName)  --> 实例化 --> 对象 --> 属性填充 --> 初始化(Spring提供的扩展点:InitializingBean/BeanNameAware/
> BeanPostProcessor等接口) -->  Bean对象

> DefaultListableBeanFactory --> AbstractAutowireCapableBeanFactory --> createBean() --> doCreateBean() 
> --> populateBean()属性填充
> --> initializeBean()初始化Bean