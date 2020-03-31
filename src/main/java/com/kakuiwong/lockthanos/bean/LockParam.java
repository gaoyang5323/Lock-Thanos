package com.kakuiwong.lockthanos.bean;

import com.kakuiwong.lockthanos.exception.LockExceptionHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class LockParam {

    private String lockName;
    private long tryLockTime;
    private long autoUnlockTime;
    private TimeUnit timeUnit;
    private LockTypeEnum type;
    private LockExceptionHandler lockExceptionHandler;

    public LockParam(String lockName, long tryLockTime, long autoUnlockTime, TimeUnit timeUnit, LockTypeEnum type, LockExceptionHandler lockExceptionHandler) {
        this.lockName = lockName;
        this.tryLockTime = tryLockTime;
        this.autoUnlockTime = autoUnlockTime;
        this.timeUnit = timeUnit;
        this.type = type;
        this.lockExceptionHandler = lockExceptionHandler;
    }

    public LockExceptionHandler getLockExceptionHandler() {
        return lockExceptionHandler;
    }

    public void setLockExceptionHandler(LockExceptionHandler lockExceptionHandler) {
        this.lockExceptionHandler = lockExceptionHandler;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public long getTryLockTime() {
        return tryLockTime;
    }

    public void setTryLockTime(long tryLockTime) {
        this.tryLockTime = tryLockTime;
    }

    public long getAutoUnlockTime() {
        return autoUnlockTime;
    }

    public void setAutoUnlockTime(long autoUnlockTime) {
        this.autoUnlockTime = autoUnlockTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public LockTypeEnum getType() {
        return type;
    }

    public void setType(LockTypeEnum type) {
        this.type = type;
    }
}
