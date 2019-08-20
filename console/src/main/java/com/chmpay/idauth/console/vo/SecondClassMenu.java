package com.chmpay.idauth.console.vo;

import com.chmpay.idauth.console.model.Menu;
import com.chmpay.idauth.console.model.Resource;

import java.util.List;

/**
 * @author zhangshuxin
 * @date 2019-06-27
 */
public class SecondClassMenu extends Menu {

    private List<Resource> children;

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SecondClassMenu{" +
                "children=" + children +
                "} " + super.toString();
    }
}
