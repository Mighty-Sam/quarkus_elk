package com.elk.service;

import com.elk.dto.UserDto;
import com.elk.entity.User;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface UserService {

    Uni<UserDto> create(User userInfo);

    Uni<List<UserDto>> findAll();

    Uni<String> deleteAll();
}
