package com.garyhu.citypickerdemo.model.net;

/**
 * 作者： garyhu.
 * 时间： 2017/1/13.
 */

public class ServerResponse<T> {
    private int code;
    private String msg;
    T info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
