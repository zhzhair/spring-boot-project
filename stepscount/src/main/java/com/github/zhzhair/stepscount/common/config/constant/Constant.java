package com.github.zhzhair.stepscount.common.config.constant;

import com.github.zhzhair.stepscount.common.util.WebUtil;

public class Constant {
    private String mac = WebUtil.getMACAddress();

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
