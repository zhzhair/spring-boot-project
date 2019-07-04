package com.github.zhzhair.main.rabbitproducer.mapper;

import com.github.zhzhair.main.rabbitproducer.dto.request.UserMapperRequest;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Integer login(@Param("userName") String userName, @Param("password") String password);

    void register(UserMapperRequest userMapperRequest);

    Integer findByMobile(String mobile);

    Integer findByUserName(String userName);
}