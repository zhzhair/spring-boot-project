package com.github.zhzhair.main.tools.service.impl;

import com.github.zhzhair.main.tools.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendSimpleMail(String receiver,String subject,String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendHtmlMail(String receiver,String subject,String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /** 发送带有附件的邮件       
    * *@param mailTo  邮件接收地址       
    * *@param title  邮件标题       
    * *@param content  邮件内容       
    * *@param filePath  文件地址       
    * *@param fileName  文件名称       
    * *@param fileType  文件类型后缀       
    */
    @Override
    public void sendAttachMail(String receiver,String subject,String text,String filePath,MultipartFile[] multipartFiles){
        if(multipartFiles != null && multipartFiles.length > 0){
            try {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型
                messageHelper.setFrom(sender);
                messageHelper.setTo(receiver);
                messageHelper.setSubject(subject);
                messageHelper.setText(text,true);
                messageHelper.setSentDate(new Date());
                for (MultipartFile multipartFile : multipartFiles) {
                    String fileName = multipartFile.getOriginalFilename();
                    File file = new File(filePath + File.separator + fileName);
                    messageHelper.addAttachment(fileName != null?fileName:"default.txt", file);
                }
                //发送邮件           
                mailSender.send(messageHelper.getMimeMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
