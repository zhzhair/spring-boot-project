package com.github.zhzhair.flowstatistic.user.mapper;

import com.github.zhzhair.flowstatistic.user.dto.request.UserRecord;
import org.apache.ibatis.annotations.Param;

public interface UserActionMapper {

    void truncateTable(@Param("tableName") String tableName);

    void addLoginHistory(UserRecord userRecord);

    void addRegisterHistory(UserRecord userRecord);
}