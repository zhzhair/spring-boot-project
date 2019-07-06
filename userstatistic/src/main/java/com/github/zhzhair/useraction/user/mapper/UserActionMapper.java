package com.github.zhzhair.useraction.user.mapper;

import com.github.zhzhair.useraction.user.dto.request.UserRecord;
import org.apache.ibatis.annotations.Param;

public interface UserActionMapper {

    void truncateTable(@Param("tableName") String tableName);

    void addLoginHistory(UserRecord userRecord);

    void addRegisterHistory(UserRecord userRecord);
}