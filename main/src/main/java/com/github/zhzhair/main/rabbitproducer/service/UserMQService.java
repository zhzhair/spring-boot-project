package com.github.zhzhair.main.rabbitproducer.service;

import com.github.zhzhair.main.rabbitproducer.dto.request.UserMapperRequest;
import com.github.zhzhair.main.rabbitproducer.dto.response.UserLoginInfo;

public interface UserMQService {

    UserLoginInfo login(String userName, String password, String ip);

    UserMapperRequest register(UserMapperRequest userMapperRequest);

    int registerChannel(UserMapperRequest userMapperRequest);
}
