package com.github.zhzhair.propreader.service;

import com.github.zhzhair.propreader.util.CensorWordReaderUtil;

import java.util.Set;

public class SensitiveWordBean {
    private Set<String> set;

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public boolean hasCensorWord(String text){
        return CensorWordReaderUtil.hasCensorWord(text,set);
    }

    public Set<String> getCensorWord(String text){
        return CensorWordReaderUtil.getCensorWord(text,set);
    }

    public String getCensorWordText(String text){
        return CensorWordReaderUtil.getCensorWordText(text,set);
    }

}
