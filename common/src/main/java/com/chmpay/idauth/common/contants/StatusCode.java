package com.chmpay.idauth.common.contants;

/**
 * @author zhangsx
 * @date 2018/9/18
 */
public class StatusCode {


    public final static StatusCode SUCCESS = new StatusCode("00", "成功");
    public final static StatusCode FAIL = new StatusCode("10", "失败");
    public final static StatusCode PARAM_ERROR = new StatusCode("11", "参数错误");
    public final static StatusCode ILLEGAL_ACESS = new StatusCode("20", "非法访问");
    public final static StatusCode EXCEPTION = new StatusCode("98", "系统异常");
    public final static StatusCode MAX_SIZE = new StatusCode("80", "数量限制为10MB");
    public final static StatusCode NOT_LOGIN = new StatusCode("100", "未登陆");
    public final static StatusCode NOT_DOWN_DATA = new StatusCode("12", "没有可下载数据");
    public final static StatusCode PASSWORD_ERROR = new StatusCode("13", "密码错误");
    public final static StatusCode PASSWORD_EQUAL = new StatusCode("14", "新旧密码相同");
    public final static StatusCode PASSWORD_DIFF = new StatusCode("15", "2次密码不一致");
    public final static StatusCode PASSWORD_LENGTH_LIMIT = new StatusCode("16", "密码长度不能大于32位");
    public final static StatusCode NOT_ADMIN2 = new StatusCode("10", "无二级审核人员！");
    public final static StatusCode NOT_PRODUCT_ID = new StatusCode("10", "缺少产品与authType 配置");

    public final static StatusCode EMPTY_REQUEST = new StatusCode("404", "请求信息不存在");
    public final static StatusCode NO_ID = new StatusCode("101", "请求信息不存在");
    public final static StatusCode SYSTEM_CONTANS = new StatusCode("102", "不允许删除内置参数");
    public final static StatusCode USER_NAME_ALREADY_EXISTED = new StatusCode("103", "该用户名已经存在");
    public final static StatusCode USER_CUSTOM_CODE_ALREADY_EXISTED = new StatusCode("104", "该客户编号已经存在");
    public final static StatusCode ID_EXIST = new StatusCode("106", "ID重复");
    public final static StatusCode TEMPLATE_IS_FULL = new StatusCode("107", "消息模板保存不得超过5个");
    public final static StatusCode REASON_ISNOT_EMPTY = new StatusCode("108", "原因不能为空");


    public final static StatusCode FILE_EXIST = new StatusCode("00", "文件已存在,稍后再进行操作");
    public final static StatusCode FILE_EXEC = new StatusCode("00", "文件生成中，稍后请转移到数据导出列表中下载");

    private String status;
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Builder create() {
        Builder builder = new Builder();
        return builder;
    }

    private StatusCode(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private StatusCode(Builder builder) {
        this.status = builder.status;
        this.msg = builder.msg;
    }

    public static class Builder {

        private static String status;
        private static String msg;

        public Builder() {
            super();
            Builder.status = status;
            Builder.msg = msg;
        }

        public Builder(String status, String msg) {
            super();
            Builder.status = status;
            Builder.msg = msg;
        }

        public Builder status(String status) {
            Builder.status = status;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public StatusCode build() {
            return new StatusCode(this);
        }

    }
}
