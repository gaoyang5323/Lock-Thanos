package com.kakuiwong.lockthanos.bean;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
public enum LockTypeEnum {
    FAIRLOCK(0), NONFAIRLOCK(1), READLOCK(2), WRITELOCK(3);

    private int idx;

    LockTypeEnum(int idx) {
        this.idx = idx;
    }

    public int idx() {
        return idx;
    }
}
