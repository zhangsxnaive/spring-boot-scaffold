package com.chmpay.idauth.console.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单vo
 *
 * @author zhangshuxin
 * @date 2019-06-29
 */
public class MenuVo implements Serializable {

    private String id;
    private String menuicon;
    private String menuname;
    private String menuurl;
    private String parentid;
    private Date modifytime;
    private Date createTime;
    private String _parentId;
    private String status;

    private List<MenuVo> chidren;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<MenuVo> getChidren() {
        return chidren;
    }

    public void setChidren(List<MenuVo> chidren) {
        this.chidren = chidren;
    }

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "id='" + id + '\'' +
                ", menuicon='" + menuicon + '\'' +
                ", menuname='" + menuname + '\'' +
                ", menuurl='" + menuurl + '\'' +
                ", parentid='" + parentid + '\'' +
                ", modifytime=" + modifytime +
                ", createTime=" + createTime +
                ", _parentId='" + _parentId + '\'' +
                ", chidren=" + chidren +
                '}';
    }
}
