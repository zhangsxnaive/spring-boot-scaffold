package com.chmpay.idauth.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhangshuxin
 * @date 2019-06-27
 */
@ApiModel("页面PageVo信息")
public class PageVo implements Serializable {
    @ApiModelProperty(value = "页码", required = false, example = "1")
    private int page;
    @ApiModelProperty(value = "列数", required = false, example = "10")
    private int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}
