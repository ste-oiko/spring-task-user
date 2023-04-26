package com.steliana.springtaskuser.mapper;

import com.steliana.springtaskuser.api.UserRequest;
import com.steliana.springtaskuser.api.UserResponse;
import com.steliana.springtaskuser.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);
    List<UserResponse> allUsersToUserResponse(List<User> users);
}
