package com.chmpay.idauth.console.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangshuxin
 * @date 2019-06-12
 */
@ApiModel("菜单")
public class Menu implements Serializable {

    private String menuCode;
    @TableId
    @ApiModelProperty(value = "菜单id",hidden = true)
    private String id;
    @ApiModelProperty(value = "菜单名称",required = true)
    private String menuName;
    @ApiModelProperty(value = "菜单链接",required = false)
    private String menuUrl;
    @ApiModelProperty(value = "菜单图标",required = false)
    private String menuIcon;
    @ApiModelProperty(value = "排序",hidden = true)
    private Integer order;
    @ApiModelProperty(value = "父菜单id",required = true)
    private String parentId;
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date modifyTime;
    @ApiModelProperty(value = "状态",hidden = true)
    private String status;
    @ApiModelProperty(value = "备注")
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", order=" + order +
                ", parentId='" + parentId + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
