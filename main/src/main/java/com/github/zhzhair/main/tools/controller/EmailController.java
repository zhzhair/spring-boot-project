package com.github.zhzhair.main.tools.controller;

import com.github.zhzhair.main.common.annotations.AccessLimit;
import com.github.zhzhair.main.common.annotations.NotAutoLog;
import com.github.zhzhair.main.tools.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(description = "常用公共接口 -- 发邮件")
@RestController
@RequestMapping("email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @AccessLimit(maxCount = 5)
    @ApiOperation(value = "发送简单邮件", notes = "发送简单邮件")
    @RequestMapping(value = "/sendSimpleEmail", method = {RequestMethod.POST})
    public void sendSimpleEmail(@RequestParam String receiver) {
        String subject = "主题:藜麦图书借阅系统注册成功通知";
        String text = "注册成功后请务必修改密码，每次借阅限期为一周，请及时归还；" +
                "若到期还需要继续使用，请登录图书借阅系统续借，祝您生活愉快，工作顺利";
        emailService.sendSimpleMail(receiver,subject,text);
    }

    @AccessLimit(maxCount = 5)
    @ApiOperation(value = "发送html简单邮件", notes = "发送html邮件")
    @RequestMapping(value = "/sendHtmlEmail", method = {RequestMethod.POST})
    public void sendHtmlEmail(@RequestParam String receiver) {
        String subject = "主题:藜麦图书借阅系统注册成功通知";
        String htmlText = "<font color='red'>注册成功后请务必修改密码，每次借阅限期为一周，请及时归还；" +
                "若到期还需要继续使用，请登录图书借阅系统续借，祝您生活愉快，工作顺利</font>";
        emailService.sendHtmlMail(receiver,subject,htmlText);
    }

    @NotAutoLog
    @AccessLimit(maxCount = 5)
    @ApiOperation(value = "发送带附件的邮件", notes = "发送带附件的邮件")
    @RequestMapping(value = "/sendAttachmentEmail", method = {RequestMethod.POST})
    public void sendAttachmentEmail(@RequestParam("receiver") String receiver, @RequestParam("file") MultipartFile multipartFile) {
        String subject = "主题:藜麦图书借阅系统注册成功通知";
        String htmlText = "<font color='red'>注册成功后请务必修改密码，每次借阅限期为一周，请及时归还；" +
                "若到期还需要继续使用，请登录图书借阅系统续借，祝您生活愉快，工作顺利</font>";
        emailService.sendAttachMail(receiver,subject,htmlText,"E:\\img\\upload", new MultipartFile[]{multipartFile});
    }
}
