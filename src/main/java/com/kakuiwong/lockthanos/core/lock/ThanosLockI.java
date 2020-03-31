package com.kakuiwong.lockthanos.core.lock;

import com.kakuiwong.lockthanos.bean.LockParam;
import org.redisson.api.RLock;

import java.util.concurrent.ExecutionException;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public interface ThanosLockI {

    boolean lock(LockParam param);

    boolean unLock(LockParam param);

    default boolean defaultUnLock(RLock lock) {
        if (lock.isHeldByCurrentThread()) {
            try {
                return lock.forceUnlockAsync().get();
            } catch (InterruptedException e) {
                return false;
            } catch (ExecutionException e) {
                return false;
            }
        }
        return false;
    }

    default boolean defaultLock(RLock lock, LockParam param) throws InterruptedException {
        return lock.tryLock(param.getTryLockTime(), param.getAutoUnlockTime(), param.getTimeUnit());
    }
}

