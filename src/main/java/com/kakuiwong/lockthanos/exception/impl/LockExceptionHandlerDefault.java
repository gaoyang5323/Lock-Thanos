package com.kakuiwong.lockthanos.exception.impl;

import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.core.lock.ThanosLockI;
import com.kakuiwong.lockthanos.exception.LockExceptionHandler;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class LockExceptionHandlerDefault implements LockExceptionHandler {

    @Override
    public Object lockFailHandle(ProceedingJoinPoint joinPoint, ThanosLockI lock, LockParam lockParam) throws Throwable {
        return joinPoint.proceed();
    }

    @Override
    public void unLockFailHandle(ProceedingJoinPoint joinPoint, ThanosLockI lock, LockParam lockParam) {

    }
}
