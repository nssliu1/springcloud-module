package com.nssliu.dataserver.entity;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2020/2/24 17:41
 * @describe:
 */
public class Msg {
    private Integer code;
    private Object detail;

    public Msg() {
    }

    public Msg(Integer code, Object detail) {
        this.code = code;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Msg msg = (Msg) o;

        if (code != null ? !code.equals(msg.code) : msg.code != null) return false;
        return detail != null ? detail.equals(msg.detail) : msg.detail == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        return result;
    }
}
