package com.kakuiwong.lockthanos.core.lock;

import com.kakuiwong.lockthanos.annotation.LockThanos;
import com.kakuiwong.lockthanos.bean.LockTypeEnum;
import com.kakuiwong.lockthanos.core.lock.impl.FairLock;
import com.kakuiwong.lockthanos.core.lock.impl.NonFairLock;
import com.kakuiwong.lockthanos.core.lock.impl.ReadLock;
import com.kakuiwong.lockthanos.core.lock.impl.WriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class ThanosLockFactory {

    private final static ThanosLockI[] locks = new ThanosLockI[4];

    @Autowired
    private RedissonClient redissonClient;

    @PostConstruct
    public void init() {
        locks[LockTypeEnum.FAIRLOCK.idx()] = new FairLock(redissonClient);
        locks[LockTypeEnum.NONFAIRLOCK.idx()] = new NonFairLock(redissonClient);
        locks[LockTypeEnum.READLOCK.idx()] = new ReadLock(redissonClient);
        locks[LockTypeEnum.WRITELOCK.idx()] = new WriteLock(redissonClient);
    }

    public ThanosLockI getLock(LockThanos lockThanos) {
        return locks[lockThanos.type().idx()];
    }
}
