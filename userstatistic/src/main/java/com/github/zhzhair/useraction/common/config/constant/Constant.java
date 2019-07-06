package com.github.zhzhair.useraction.common.config.constant;

import com.github.zhzhair.useraction.common.util.WebUtil;

public class Constant {
    private String mac = WebUtil.getMACAddress();

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
