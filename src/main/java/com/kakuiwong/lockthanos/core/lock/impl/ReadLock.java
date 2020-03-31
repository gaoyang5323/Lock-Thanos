package com.kakuiwong.lockthanos.core.lock.impl;

import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.core.lock.ThanosLockI;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class ReadLock implements ThanosLockI {

    private RedissonClient redissonClient;

    public ReadLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean lock(LockParam param) {
        try {
            RReadWriteLock lock = redissonClient.getReadWriteLock(param.getLockName());
            return defaultLock(lock.readLock(), param);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean unLock(LockParam param) {
        RReadWriteLock lock = redissonClient.getReadWriteLock(param.getLockName());
        return defaultUnLock(lock.readLock());
    }
}
