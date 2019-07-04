package com.github.zhzhair.main.rabbitproducer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.zhzhair.main.rabbitproducer.dto.request.UserMapperRequest;
import com.github.zhzhair.main.rabbitproducer.mapper.UserMapper;
import com.github.zhzhair.main.rabbitproducer.dto.response.UserLoginInfo;
import com.github.zhzhair.main.rabbitproducer.service.UserMQService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class UserMQServiceImpl implements UserMQService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public UserLoginInfo login(String userName, String password, String ip) {
        Integer userId = userMapper.login(userName, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (userId != null) {
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            userLoginInfo.setUserId(userId);
            userLoginInfo.setIp(ip);
            userLoginInfo.setLoginTime(timestamp);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_LOGIN", userLoginInfo, correlationData);
            return userLoginInfo;
        }
        return null;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public UserMapperRequest register(UserMapperRequest userMapperRequest) {
        userMapper.register(userMapperRequest);
        Integer userId = userMapper.findByMobile(userMapperRequest.getMobile());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        jsonObject.put("createTime",timestamp);
        jsonObject.put("ip",userMapperRequest.getIp());
        jsonObject.put("mobile",userMapperRequest.getMobile());
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_REGISTER", jsonObject, correlationData);
        return userMapperRequest;
    }

    @Override
    public int registerChannel(UserMapperRequest userMapperRequest) {
        Integer userId = userMapper.findByMobile(userMapperRequest.getMobile());
        Integer userId1 = userMapper.findByUserName(userMapperRequest.getUserName());
        if(userId != null){
            return 1;
        }
        if(userId1 != null){
            return 2;
        }
        return 0;
    }
}
