package com.kakuiwong.lockthanos.core.lock.impl;

import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.core.lock.ThanosLockI;
import org.redisson.api.RedissonClient;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class NonFairLock implements ThanosLockI {

    private RedissonClient redissonClient;

    public NonFairLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean lock(LockParam param) {
        return defaultLock(redissonClient.getLock(param.getLockName()), param);
    }

    @Override
    public boolean unLock(LockParam param) {
        return defaultUnLock(redissonClient.getLock(param.getLockName()));
    }
}
