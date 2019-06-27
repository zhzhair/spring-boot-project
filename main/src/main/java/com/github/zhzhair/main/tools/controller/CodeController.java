package com.github.zhzhair.main.tools.controller;

import com.github.zhzhair.main.common.annotations.AccessLimit;
import com.github.zhzhair.main.common.annotations.NotAutoLog;
import com.github.zhzhair.main.common.util.WebUtil;
import com.github.zhzhair.main.tools.service.ImgCheckCodeService;
import com.github.zhzhair.main.tools.service.QRCodeWithPictureService;
import com.github.zhzhair.main.tools.service.QrCodeService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "常用公共接口 -- 图片验证码和二维码")
@Controller
@RequestMapping("imgcode")
public class CodeController {
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String,String> redisTemplate;
    @Resource
    private ImgCheckCodeService imgCheckCodeService;
    @Resource
    private QrCodeService qrCodeService;
    @Resource
    private QRCodeWithPictureService qrCodeWithPictureService;

    @ApiOperation(value = "生成二维码", notes = "生成二维码")
    @RequestMapping(value = "/othersImg", method = {RequestMethod.GET})
    public void createQrCode(HttpServletResponse response, @RequestParam String content) {
        BufferedImage bufferedImage = qrCodeService.createQrCode(content);
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpeg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @NotAutoLog
    @ApiOperation(value = "生成带图片的二维码", notes = "生成生成带图片的二维码")
    @RequestMapping(value = "/imgWithPic", method = {RequestMethod.GET})
    public void showImgCodeWithPicture(HttpServletResponse resp) {
        try {
            //QRCodeWithPicture.encode("http://www.baidu.com", req.getServletContext().getRealPath("/uploadpic/hehe.jpg"), resp.getOutputStream(), true);
            qrCodeWithPictureService.encode("https://github.com/zhzhair/", "E:/imgs/filescan.png", resp.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotAutoLog
    @AccessLimit(maxCount = 5,seconds = 2)
    @ApiOperation(value = "图片验证码",notes = "返回图片验证码并记录到redis")
    @RequestMapping(value = "/checkCode", method = {RequestMethod.GET})
    public void checkCode(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "No-cache");
        resp.setDateHeader("Expires", 0);
        String checkcode = null;
        try {
            checkcode = imgCheckCodeService.codeCreate(resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(checkcode != null){
            String ip = WebUtil.getRemoteAddr(req);
            redisTemplate.opsForValue().set("imgCode:" + ip,checkcode,5L,TimeUnit.MINUTES);
        }
    }
}
