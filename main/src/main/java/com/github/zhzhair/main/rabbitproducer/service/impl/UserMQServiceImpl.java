package com.github.zhzhair.main.rabbitproducer.service.impl;

import com.github.zhzhair.main.rabbitproducer.dto.request.UserMapperRequest;
import com.github.zhzhair.main.rabbitproducer.mapper.UserMapper;
import com.github.zhzhair.main.rabbitproducer.dto.response.UserLoginInfo;
import com.github.zhzhair.main.rabbitproducer.service.UserMQService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
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

    @Override
    public UserMapperRequest register(UserMapperRequest userMapperRequest) {
        Integer userId = userMapper.findByMobile(userMapperRequest.getMobile());
        if(userId != null){
            return null;
        }
        userMapper.register(userMapperRequest);
        userId = userMapper.findByMobile(userMapperRequest.getMobile());
        userMapperRequest.setUserId(userId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        userMapperRequest.setCreateTime(timestamp);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_REGISTER", userMapperRequest, correlationData);
        return userMapperRequest;
    }
}
