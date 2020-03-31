package com.kakuiwong.lockthanos.core.lock;

import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.core.util.LockThanosLogUtil;
import org.redisson.api.RLock;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public interface ThanosLockI {

    boolean lock(LockParam param);

    boolean unLock(LockParam param);

    default boolean defaultUnLock(RLock lock) {
        try {
            if (!lock.isHeldByCurrentThread()) {
                return false;
            }
            return lock.forceUnlockAsync().get();
        } catch (Exception e) {
            LockThanosLogUtil.warning("lock error", e);
            return false;
        }
    }

    default boolean defaultLock(RLock lock, LockParam param) {
        try {
            return lock.tryLock(param.getTryLockTime(), param.getAutoUnlockTime(), param.getTimeUnit());
        } catch (Exception e) {
            LockThanosLogUtil.warning("unLock error", e);
            return false;
        }
    }
}

