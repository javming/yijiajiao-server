package com.yijiajiao.server.bean;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-31-13:03
 */
public class RedisParam {
    private String key;
    private int dbNum;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDbNum() {
        return dbNum;
    }

    public void setDbNum(int dbNum) {
        this.dbNum = dbNum;
    }
}
