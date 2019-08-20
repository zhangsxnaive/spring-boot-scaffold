package com.chmpay.idauth.console.vo;

/**
 * @author zhangsx
 * @date 2018/9/18
 */
public class LoginVo {

    /**
     * 用户应用ID
     */
    private String appid;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 认证类型 1 身份认证, 2 人脸识别
     */
    private String authType;

    /**
     * 剩余认证次数
     */
    private Integer num;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色类型
     */
    private String roleType;

    private String phone;
    private String email;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
