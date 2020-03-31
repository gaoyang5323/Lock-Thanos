package com.kakuiwong.lockthanos.exception;

import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.core.lock.ThanosLockI;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public interface LockExceptionHandler {

    /**
     * @param joinPoint 切面信息
     * @param lock      当前redis锁
     * @param lockParam 锁信息
     * @return 返回执行结果
     * @throws Throwable
     */
    Object lockFailHandle(ProceedingJoinPoint joinPoint, ThanosLockI lock, LockParam lockParam) throws Throwable;

    /**
     * @param joinPoint 切面信息
     * @param lock      当前redis锁
     * @param lockParam 锁信息
     */
    void unLockFailHandle(ProceedingJoinPoint joinPoint, ThanosLockI lock, LockParam lockParam);
}
