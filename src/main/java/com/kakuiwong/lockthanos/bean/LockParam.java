package com.kakuiwong.lockthanos.bean;

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
