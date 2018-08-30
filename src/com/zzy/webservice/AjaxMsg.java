package com.zzy.webservice;

public class AjaxMsg {

    private Object code;// http状态码
    private Object model;// 其他信息
    private Object msg;//返回成功与否

    public AjaxMsg(){//构造器
        this.code = 200;
        this.model = null;// 其他信息
        this.msg = "success";// 其他信息
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }


    /**返回成功状态*/
    public static AjaxMsg returnAjaxMsg(){
        return new AjaxMsg();
    }
    /**返回数据*/
    public static AjaxMsg returnAjaxMsg(Object model){
        AjaxMsg ajaxMsg = new AjaxMsg();
        ajaxMsg.setModel(model);
        return ajaxMsg;
    }
    /**返回状态和信息*/
    public static AjaxMsg returnAjaxMsg(Object code,Object msg ){
        AjaxMsg ajaxMsg = new AjaxMsg();
        ajaxMsg.setCode(code);
        ajaxMsg.setMsg(msg);
        return ajaxMsg;
    }
    /**返回状态、数据和信息*/
    public static AjaxMsg returnAjaxMsg(Object code,Object model,Object msg ){
        AjaxMsg ajaxMsg = new AjaxMsg();
        ajaxMsg.setCode(code);
        ajaxMsg.setModel(model);
        ajaxMsg.setMsg(msg);
        return ajaxMsg;
    }
}
