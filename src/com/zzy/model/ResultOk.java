package com.zzy.model;

import java.util.Map;

/**
 * 自定义 SpringMVC JSON 返回的实体
 */

public class ResultOk {
    private Map map;
    private String msg;
    private Object obj;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
