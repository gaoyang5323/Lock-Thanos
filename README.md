# Lock-Thanos
Distributed lock based on Redisson, declarative annotation method

###一.Features:
1. Support re-entrant fair lock, re-entrant non-fair lock, write lock, read lock
2. Support annotation setting parameters
3. Custom error handling

###二.Introduce the project:
1 Introducing the pom parameter:
```
        <dependency>
           <groupId>com.kakuiwong</groupId>
              <artifactId>lock-thanos</artifactId>
              <version>${lastversion}</version>
        </dependency>
```

2 Writing the application configuration file
```
lock.thanos.address=redis://127.0.0.1:6379,redis://127.0.0.2:6379 #Cluster or stand-alone
lock.thanos.password=123456 #password
lock.thanos.database=2 #redis database
lock.thanos.order=0 #aop order
```

3 Introducing annotations:
```
    @LockThanos(key = {"#id"},                                      #Support spel lock key
            type = LockTypeEnum.FAIRLOCK,                           #Lock category
            lockName = "default",                                   #Lock name
            tryLockTime = 30,                                       #Try to lock the expiration time
            autoUnlockTime = 30,                                    #Automatically destroy lock time
            timeUnit = TimeUnit.SECONDS,                            #time unit
            exceptionHandler = LockExceptionHandlerDemo.class)      #Custom exception handling
    @GetMapping("lock")
    public Object lock(String id){
        System.out.println(id);
        return id;
    }
```

4 Custom exception handling:
```
//Implementation LockExceptionHandler Interface
static class LockExceptionHandlerDemo implements LockExceptionHandler {
        
          @Override
          public Object lockFailHandle(ExceptionHandlerParam param) throws Throwable {
              return param.invoke();
          }
        
          @Override
          public void unLockFailHandle(ExceptionHandlerParam param) {
              //default is to do nothing
          }
    }
```