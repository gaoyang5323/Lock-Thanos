package com.kakuiwong.lockthanos.exception;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public class LockThanosException extends RuntimeException {

    public LockThanosException() {
        super();
    }

    public LockThanosException(String message) {
        super(message);
    }

    public LockThanosException(String message, Throwable t) {
        super(message, t);
    }
}
