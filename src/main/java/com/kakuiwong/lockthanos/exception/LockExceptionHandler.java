package com.kakuiwong.lockthanos.exception;


import com.kakuiwong.lockthanos.bean.ExceptionHandlerParam;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public interface LockExceptionHandler {

    Object lockFailHandle(ExceptionHandlerParam param) throws Throwable;

    void unLockFailHandle(ExceptionHandlerParam param);
}
