package com.chmpay.idauth.console.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangshuxin
 * @date 2019-07-01
 */
public class ResourceVo implements Serializable {
    private String id;
    private String resourcename;
    private String status;
    private String menuname;
    private String resourceurl;
    private String resourceuse;
    private String resourceicon;
    private Date createtime;
    private Date modifytime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getResourceurl() {
        return resourceurl;
    }

    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl;
    }

    public String getResourceuse() {
        return resourceuse;
    }

    public void setResourceuse(String resourceuse) {
        this.resourceuse = resourceuse;
    }

    public String getResourceicon() {
        return resourceicon;
    }

    public void setResourceicon(String resourceicon) {
        this.resourceicon = resourceicon;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    @Override
    public String toString() {
        return "ResourceVo{" +
                "id='" + id + '\'' +
                ", resourcename='" + resourcename + '\'' +
                ", status='" + status + '\'' +
                ", menuname='" + menuname + '\'' +
                ", resourceurl='" + resourceurl + '\'' +
                ", resourceuse='" + resourceuse + '\'' +
                ", resourceicon='" + resourceicon + '\'' +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                '}';
    }
}
