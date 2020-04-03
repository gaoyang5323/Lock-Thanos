package com.kakuiwong.lockthanos.bean;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.concurrent.TimeUnit;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class ExceptionHandlerParam {

    private ProceedingJoinPoint joinPoint;
    private LockParam lockParam;

    public ExceptionHandlerParam(ProceedingJoinPoint joinPoint, LockParam lockParam) {
        this.joinPoint = joinPoint;
        this.lockParam = lockParam;
    }

    public Object invoke() throws Throwable {
        return joinPoint.proceed();
    }

    public String getLockName() {
        return this.lockParam.getLockName();
    }

    public long getTryLockTime() {
        return this.lockParam.getTryLockTime();
    }

    public long getAutoUnlockTime() {
        return this.lockParam.getAutoUnlockTime();
    }

    public TimeUnit getTimeUnit() {
        return this.lockParam.getTimeUnit();
    }

    public LockTypeEnum getType() {
        return this.lockParam.getType();
    }

    public static ExceptionHandlerParam getHandlerParam(ProceedingJoinPoint joinPoint, LockParam lockParam) {
        return new ExceptionHandlerParam(joinPoint, lockParam);
    }
}
