# Lock-Thanos
基于Redisson的分布式锁,声明式注解方式

###一.功能:
1. 支持可重入公平锁,可重入非公平锁,写锁,读锁
2. 支持注解声明式设置参数
3. 自定义错误处理

###二.引入项目:
1 引入pom参数:
```
        <dependency>
           <groupId>com.kakuiwong</groupId>
              <artifactId>lock-thanos</artifactId>
              <version>${lastversion}</version>
        </dependency>
```

2 编写application配置文件
```
lock.thanos.address=redis://127.0.0.1:6379,redis://127.0.0.2:6379 #集群或单机
lock.thanos.password=123456 #密码
lock.thanos.database=2 #redis数据库
lock.thanos.order=0 #aop顺序
```

3 引入注解:
```
    @LockThanos(key = {"#id"},                                      #支持spel的锁key
            type = LockTypeEnum.FAIRLOCK,                           #锁类别
            lockName = "default",                                   #锁名称
            tryLockTime = 30,                                       #尝试加锁过期时间
            autoUnlockTime = 30,                                    #自动销毁锁时间
            timeUnit = TimeUnit.SECONDS,                            #时间单位
            exceptionHandler = LockExceptionHandlerDemo.class)      #自定义异常处理方式
    @GetMapping("lock")
    public Object lock(String id){
        System.out.println(id);
        return id;
    }
```

4 自定义异常处理:
```
//实现LockExceptionHandler接口,默认为不做任何处理
static class LockExceptionHandlerDemo implements LockExceptionHandler {

        @Override
        public Object lockFailHandle(ProceedingJoinPoint joinPoint, ThanosLockI lock, LockParam lockParam) throws Throwable {
            System.out.println("lockFailHandle");
            return joinPoint.proceed();
        }

        @Override
        public void unLockFailHandle(ProceedingJoinPoint joinPoint, ThanosLockI lock, LockParam lockParam) {
            System.out.println("unLockFailHandle");
        }
    }
```