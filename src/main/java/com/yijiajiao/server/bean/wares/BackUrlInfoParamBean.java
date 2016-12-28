package com.yijiajiao.server.bean.wares;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-15-11:40
 */
public class BackUrlInfoParamBean {
    private String mtgKey;
    private String userName;
    private String configFile;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getMtgKey() {
        return mtgKey;
    }

    public void setMtgKey(String mtgKey) {
        this.mtgKey = mtgKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public String toString() {
        return "BackUrlInfoParamBean{" +
                "mtgKey='" + mtgKey + '\'' +
                ", userName='" + userName + '\'' +
                ", configFile='" + configFile + '\'' +
                '}';
    }
}
