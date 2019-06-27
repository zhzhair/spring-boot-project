package com.github.zhzhair.main.tools.service;

import java.awt.image.BufferedImage;

public interface QrCodeService {

    BufferedImage createQrCode(String content);
}
