package com.kakuiwong.lockthanos.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
@ConfigurationProperties(prefix = LockThanosConfigProperties.PREFIX)
public class LockThanosConfigProperties {
    public final static String PREFIX = "lock.thanos";
    public final static int databaseIdx = 2;

    private String[] address;
    private String password;
    private int database = databaseIdx;
    private int order = 0;

    public static String getPREFIX() {
        return PREFIX;
    }

    public static int getDatabaseIdx() {
        return databaseIdx;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }
}
