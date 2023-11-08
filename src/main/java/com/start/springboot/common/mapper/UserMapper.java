package com.start.springboot.common.mapper;

import com.start.springboot.domain.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public UserDto getUserByUserId(String userId);

    public UserDto getUserByObject(UserDto userDto);
}
