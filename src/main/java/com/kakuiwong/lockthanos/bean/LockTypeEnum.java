package com.kakuiwong.lockthanos.bean;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public enum LockTypeEnum {
    DEFAULT(0), FAIRLOCK(1), READLOCK(2), WRITELOCK(3);

    private int idx;

    LockTypeEnum(int idx) {
        this.idx = idx;
    }
    public int idx() {
        return idx;
    }
}
