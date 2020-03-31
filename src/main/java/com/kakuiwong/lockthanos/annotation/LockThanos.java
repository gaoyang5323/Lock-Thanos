package com.kakuiwong.lockthanos.annotation;

import com.kakuiwong.lockthanos.bean.LockTypeEnum;
import com.kakuiwong.lockthanos.exception.LockExceptionHandler;
import com.kakuiwong.lockthanos.exception.impl.LockExceptionHandlerDefault;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LockThanos {

    /**
     * @return 锁名称
     */
    String lockName() default "";

    /**
     * @return 加锁key
     */
    String[] key() default "";

    /**
     * @return 尝试加锁超时时间
     */
    int tryLockTime() default -1;

    /**
     * @return 自动解锁时间
     */
    int autoUnlockTime() default -1;

    /**
     * @return 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * @return 锁类别
     */
    LockTypeEnum type() default LockTypeEnum.DEFAULT;

    /**
     * @return 锁异常处理类
     */
    Class<? extends LockExceptionHandler> exceptionHandler() default LockExceptionHandlerDefault.class;
}
