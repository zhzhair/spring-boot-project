package com.github.zhzhair.flowstatistic.user.service.impl;

import com.github.zhzhair.flowstatistic.user.mapper.UserActionMapper;
import com.github.zhzhair.flowstatistic.user.service.UserActionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserActionServiceImpl implements UserActionService {
    @Resource
    private UserActionMapper userActionMapper;

    @Override
    public void truncateTable(){
        userActionMapper.truncateTable("user_register");
        userActionMapper.truncateTable("user_login");
    }
}
