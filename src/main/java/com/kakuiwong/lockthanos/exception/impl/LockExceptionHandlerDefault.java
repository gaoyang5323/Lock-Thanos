package com.kakuiwong.lockthanos.exception.impl;


import com.kakuiwong.lockthanos.bean.ExceptionHandlerParam;
import com.kakuiwong.lockthanos.exception.LockExceptionHandler;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class LockExceptionHandlerDefault implements LockExceptionHandler {

    @Override
    public Object lockFailHandle(ExceptionHandlerParam param) throws Throwable {
        return param.invoke();
    }

    @Override
    public void unLockFailHandle(ExceptionHandlerParam param) {
        //默认不处理
    }
}
