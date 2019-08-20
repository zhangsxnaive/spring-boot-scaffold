package com.chmpay.idauth.console.vo;

import com.chmpay.idauth.console.model.Menu;

import java.util.List;

/**
 * @author zhangshuxin
 * @date 2019-06-27
 */
public class FirstClsssMenu extends Menu {

    private List<SecondClassMenu> children;

    public List<SecondClassMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SecondClassMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "FirstClsssMenu{" +
                "children=" + children +
                "} " + super.toString();
    }
}
