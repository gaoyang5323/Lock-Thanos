package com.kakuiwong.lockthanos.bean;

import com.kakuiwong.lockthanos.exception.LockExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
@ConfigurationProperties(prefix = LockThanosConfigProperties.PREFIX)
public class LockThanosConfigProperties {
    public final static String PREFIX = "lock.thanos";
    private String[] address;
    private String password;
    private int database = 2;
    private long tryLockTime = 30;
    private long autoUnlockTime = 60;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private LockExceptionHandler lockExceptionHandler;

    public LockExceptionHandler getLockExceptionHandler() {
        return lockExceptionHandler;
    }

    public void setLockExceptionHandler(LockExceptionHandler lockExceptionHandler) {
        this.lockExceptionHandler = lockExceptionHandler;
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
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
}
