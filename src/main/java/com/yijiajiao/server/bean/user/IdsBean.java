package com.yijiajiao.server.bean.user;

/**
 * Created by ruyage on 2015/12/5.
 */
public class IdsBean {

    private String ids;

    public IdsBean() {
    }

    public IdsBean(String ids) {
        this.ids = ids;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString(){
        return "ids : ["+ids+"]";
    }
}
