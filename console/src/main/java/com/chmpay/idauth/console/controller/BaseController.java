package com.chmpay.idauth.console.controller;

import com.chmpay.idauth.console.util.PageParam;
import com.chmpay.idauth.console.vo.PageVo;

/**
 * @author zhangshuxin
 * @date 2019-06-10
 */
public class BaseController {


    protected PageParam collectPage(PageVo pageVo) {
        if (pageVo.getRows() == 0) {
            return new PageParam(pageVo);
        } else {
            return new PageParam(pageVo);
        }
    }


}
