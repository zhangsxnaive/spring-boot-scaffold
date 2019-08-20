package com.chmpay.idauth.console.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangshuxin
 * @date 2019-06-27
 */
@Component
@ConfigurationProperties(prefix = "url")
public class IgnoreUrlsProp {

    private List<String> ignoreList;

    public List<String> getIgnoreList() {
        return ignoreList;
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }
}
