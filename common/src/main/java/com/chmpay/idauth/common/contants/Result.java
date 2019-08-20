package com.chmpay.idauth.common.contants;

/**
 * @author zhangshuxin
 * @date 2019-06-11
 */
public class Result {
    private String status;
    private String msg;
    private Object data;
    private Long total;
    private Long pageSize;


    public Result() {
    }

    public static Result ok() {
        return new Result(StatusCode.SUCCESS);
    }

    public static Result fail() {
        return new Result(StatusCode.FAIL);
    }

    public Result(StatusCode statusCode) {
        this.status = statusCode.getStatus();
        this.msg = statusCode.getMsg();
    }

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(Builder builder) {
        this.msg = builder.msg;
        this.status = builder.status;
        this.data = builder.data;
        this.total = builder.total;
        this.pageSize = builder.pageSize;
    }
    public static Builder create() {
        return new Builder();
    }
    public static Builder create(StatusCode statusCode) {
        return new Builder(statusCode);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public static class Builder{
        private String status;
        private String msg;
        private Object data;
        private Long total;
        private Long pageSize;

        public Builder() {
        }

        public Builder(StatusCode statusCode) {
            this.status = statusCode.getStatus();
            this.msg = statusCode.getMsg();
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder total(long total) {
            this.total = total;
            return this;
        }

        public Builder pageSize(long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Result build() {
            return new Result(this);
        }

    }




}
