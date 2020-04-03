package com.kakuiwong.lockthanos.core.aop;


import com.kakuiwong.lockthanos.annotation.LockThanos;
import com.kakuiwong.lockthanos.bean.ExceptionHandlerParam;
import com.kakuiwong.lockthanos.bean.LockParam;
import com.kakuiwong.lockthanos.bean.LockThanosConfigProperties;
import com.kakuiwong.lockthanos.core.lock.ThanosLockFactory;
import com.kakuiwong.lockthanos.core.lock.ThanosLockI;
import com.kakuiwong.lockthanos.core.util.LockThanosParamUtil;
import com.kakuiwong.lockthanos.exception.LockExceptionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
@Aspect
public class LockThanosMethodInterception implements Ordered {

    @Autowired
    private ThanosLockFactory thanosLockFactory;
    @Autowired
    private LockThanosParamUtil lockThanosParamUtil;
    @Autowired
    private LockThanosConfigProperties lockThanosConfigProperties;

    @Around(value = "@annotation(lockAnno)")
    public Object invoke(ProceedingJoinPoint joinPoint, LockThanos lockAnno) throws Throwable {
        LockParam lockParam = lockThanosParamUtil.convert(lockAnno, joinPoint);
        LockExceptionHandler lockExceptionHandler = lockParam.getLockExceptionHandler();
        ThanosLockI lock = thanosLockFactory.getLock(lockAnno);
        try {
            if (!lock.lock(lockParam)) {
                return lockExceptionHandler.lockFailHandle(ExceptionHandlerParam.getHandlerParam(joinPoint, lockParam));
            }
            return joinPoint.proceed();
        } finally {
            if (!lock.unLock(lockParam)) {
                lockExceptionHandler.unLockFailHandle(ExceptionHandlerParam.getHandlerParam(joinPoint, lockParam));
            }
        }
    }

    @Override
    public int getOrder() {
        return lockThanosConfigProperties.getOrder();
    }
}
