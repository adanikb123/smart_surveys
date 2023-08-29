package epolsoft.practice.smart_surveys.mapper;

import epolsoft.practice.smart_surveys.dto.UserRequestDto;
import epolsoft.practice.smart_surveys.dto.UserResponseDto;
import epolsoft.practice.smart_surveys.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GeneralMapper<UserResponseDto, User, UserRequestDto> {
}
