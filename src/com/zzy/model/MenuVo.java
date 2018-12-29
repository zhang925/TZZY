package com.zzy.model;

import java.util.List;

public class MenuVo {



    private String id;
    private String name;
    private String url;
    private List<MenuVo> children;

    /**扩展内容,用于zTree*/
    private boolean open;//是否展开
    private boolean nocheck;//是否选择
    private boolean checked;//节点的 checkBox / radio 的 勾选状态。
    private boolean chkDisabled;//1、设置节点的 checkbox / radio 是否禁用
    private boolean isParent;//记录 treeNode 节点是否为父节点。
    private String target;//设置点击节点后在何处打开 url。

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

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
