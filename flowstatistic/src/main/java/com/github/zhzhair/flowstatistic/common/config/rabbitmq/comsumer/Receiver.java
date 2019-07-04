package com.github.zhzhair.flowstatistic.common.config.rabbitmq.comsumer;

import com.alibaba.fastjson.JSONObject;
import com.github.zhzhair.flowstatistic.user.dto.request.UserRecord;
import com.github.zhzhair.flowstatistic.user.mapper.UserActionMapper;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * 异步方法调用：这里是登录异步入库登录历史记录，更加典型的场景是注册成功异步发送邮件,同时给用户送积分
 * Created by hxy on 2018/7/2 Used by zhzhair.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component
public class Receiver {

    @Resource
    private UserActionMapper userActionMapper;

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"DIRECT_LOGIN_QUEUE"})
    public void messageLogin(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        String userStr = new String(message.getBody());
        JSONObject jsonObject = JSONObject.parseObject(userStr);
        if(!jsonObject.isEmpty()){
            UserRecord userRecord = new UserRecord();
            userRecord.setUserId(jsonObject.getInteger("userId"));
            userRecord.setIp(jsonObject.getString("ip"));
            userRecord.setCreateTime(jsonObject.getTimestamp("loginTime"));
            userActionMapper.addLoginHistory(userRecord);
        }
    }

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"DIRECT_REGISTER_QUEUE"})
    public void messageRegister(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        String userStr = new String(message.getBody());
        JSONObject jsonObject = JSONObject.parseObject(userStr);
        if(!jsonObject.isEmpty()){
            UserRecord userRecord = new UserRecord();
            userRecord.setUserId(jsonObject.getInteger("userId"));
            userRecord.setMobile(jsonObject.getString("mobile"));
            userRecord.setIp(jsonObject.getString("ip"));
            userRecord.setCreateTime(jsonObject.getTimestamp("createTime"));
            userActionMapper.addRegisterHistory(userRecord);
        }
    }
//    /**
//     * FANOUT广播队列监听一.
//     *
//     * @param message the message
//     * @param channel the channel
//     * @throws IOException the io exception  这里异常需要处理
//     */
//    @RabbitListener(queues = {"FANOUT_QUEUE_A"})
//    public void on(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        log.debug("FANOUT_QUEUE_A " + new String(message.getBody()));
//    }

//    /**
//     * FANOUT广播队列监听二.
//     *
//     * @param message the message
//     * @param channel the channel
//     * @throws IOException the io exception   这里异常需要处理
//     */
//    @RabbitListener(queues = {"FANOUT_QUEUE_B"})
//    public void t(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//        log.debug("FANOUT_QUEUE_B " + new String(message.getBody()));
//    }

}
