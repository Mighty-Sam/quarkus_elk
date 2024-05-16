package com.user.service;

import com.user.dto.UserDto;
import com.user.entity.User;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    Uni<UserDto> create(User userInfo);

    Uni<List<UserDto>> findAll();

    Uni<UserDto> findById(ObjectId id);

    Uni<String> deleteAll();
}
