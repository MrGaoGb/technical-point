package com.technical.facade.enums;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author gaogba
 * @apiNote 代理商平台：web端基本校验
 */
public enum LoginStatusEnum {

    /*web端 代理商登录状态->基本校验:*/
    LOGIN_ERROR(1,"用户登录失败，验证码错误"),
    NO_ACCESS(2,"用户没有权限"),
    PASSWORD_ERROR(3,"用户名密码错误"),
    PASSWORD_ERROR_TOO_MUCH(4,"用户登录状态已锁定!"),
    INVALID_OR_CANCELLATION(5,"该商户是无效或注销状态"),

    ;

    private int code;
    private String msg;


    private LoginStatusEnum() {

    }

    private LoginStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
