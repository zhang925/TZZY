package com.zzy.model;

import java.util.List;

public class MenuVo {

    private String id;
    private String name;
    private String url;
    private List<MenuVo> childNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuVo> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<MenuVo> childNodes) {
        this.childNodes = childNodes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
