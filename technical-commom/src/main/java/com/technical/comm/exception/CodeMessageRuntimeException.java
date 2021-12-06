package com.technical.comm.exception;

/**
 * Created by 文国印 on 2020/7/14.
 */
public class CodeMessageRuntimeException extends RuntimeException {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMessageRuntimeException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}

