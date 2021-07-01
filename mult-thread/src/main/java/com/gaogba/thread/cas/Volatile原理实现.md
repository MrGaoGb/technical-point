### Volatile原理实现

* 1、保证线程的可见性
![img_9.png](img_9.png)
  
* 2、禁止指令重排

> CPU 的乱序执行
![img_12.png](img_12.png)

![img_13.png](img_13.png)

![img_14.png](img_14.png)

![img_15.png](img_15.png)


#### 超线程
> 一个ALU对应多个PC
![img_10.png](img_10.png)

### Cache Line
![img_11.png](img_11.png)

### 常见问题

* 1、DCL(Double Check Lock) 到底需不需要Volatile关键字?
> 答案: 需要。
![img_18.png](img_18.png)
> 原因对象创建分成三步:1.申请内存 2.设置默认初始值 3、建立关联。
![img_16.png](img_16.png)
![img_17.png](img_17.png)

#### 内存屏障概念
![img_19.png](img_19.png)

![img_20.png](img_20.png)

![img_21.png](img_21.png)

![img_22.png](img_22.png)

#### 强引用

#### 软引用
![img_23.png](img_23.png)

#### 弱引用
![img_24.png](img_24.png)

#### 虚引用
* 作用:管理对外内存
![img_25.png](img_25.png)
  
##### P8