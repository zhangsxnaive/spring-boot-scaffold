package com.chmpay.idauth.console.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chmpay.idauth.console.vo.PageVo;

/**
 * @author zhangshuxin
 * @date 2019-06-11
 */
public class PageParam extends Page {


    public PageParam(PageVo pageVo) {
        super(pageVo.getPage(), pageVo.getRows());
    }

}
