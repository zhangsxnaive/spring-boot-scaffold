package com.chmpay.idauth.common.exception;

import com.chmpay.idauth.common.contants.StatusCode;

/**
 * @author zhangsx
 * @date 2018/9/18
 */
public class MyException extends Exception {


    /**
     *
     */
    private static final long serialVersionUID = -9013736238972722983L;

    private String status;
    private String msg;

    private StatusCode statusCode;

    public MyException() {
        super();
    }


    public MyException(String status, String msg) {
        super(msg);
        this.msg = msg;
        this.status = status;
        this.statusCode = StatusCode.create().status(status).msg(msg).build();
    }

    public MyException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.msg = statusCode.getMsg();
        this.status = statusCode.getStatus();
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
