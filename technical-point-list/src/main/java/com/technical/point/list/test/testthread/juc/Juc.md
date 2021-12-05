### Juc学习

### 传统Synchronized(锁)

### Lock(锁)

#### 实现类
- ReentrantLock (可重入锁)
- Condition 
- ReadWriteLock(读写锁)

### 传统Synchronized和Lock二者区别?
- synchronized 是java关键字，而lock是一个java类
- synchronized 无法获取锁的状态，而lock可以判断是否获取到锁。
- synchronized 会自动释放锁，而lock必须手动释放锁，若不释放，会造成死锁。
- synchronized 线程1(获取锁)、线程2(会进入阻塞，傻傻的等) ，而 lock锁就不一定等下去(存在公平锁和非公平锁)。
- synchronized 可重入锁，不可以中断，而 lock 可重入锁，可以判断锁，非公平锁可以设置(默认非公平锁)。
- synchronized 适合锁少量的代码同步问题，lock 适合锁大量的代码同步问题。
