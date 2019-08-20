package com.chmpay.idauth.console.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangshuxin
 * @date 2019-06-12
 */
@ApiModel("资源")
public class Resource implements Serializable {

    @TableId
    @ApiModelProperty(value = "资源id",hidden = true)
    private String id;
    @ApiModelProperty(value = "资源代码",hidden = true)
    private String resourceCode;
    @ApiModelProperty(value = "资源名称",required = true)
    private String resourceName;
    @ApiModelProperty(value = "资源地址",required = true)
    private String resourceUrl;
    @ApiModelProperty(value = "资源图标",required = false)
    private String resourceIcon;
    @ApiModelProperty(value = "资源使用？",required = false)
    private String resourceUse;
    @ApiModelProperty(value = "菜单id",required = true)
    private String menuId;
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

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public String getResourceUse() {
        return resourceUse;
    }

    public void setResourceUse(String resourceUse) {
        this.resourceUse = resourceUse;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
        return "Resource{" +
                "id='" + id + '\'' +
                ", resourceCode='" + resourceCode + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", resourceIcon='" + resourceIcon + '\'' +
                ", resourceUse='" + resourceUse + '\'' +
                ", menuId='" + menuId + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
