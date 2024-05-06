package com.elk.service.impl;

import com.elk.dto.UserDto;
import com.elk.entity.User;
import com.elk.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Override
    public Uni<UserDto> create(User userInfo) {
        return User.find("name", userInfo.getName()).firstResult()
                .flatMap(existingUser -> {
                    if (existingUser == null) {
                        return userInfo.persist()
                                .flatMap(x -> User.find("_id", userInfo.getId())
                                        .project(UserDto.class)
                                        .firstResult());
                    } else {
                        // 返回一個錯誤訊息，表示該名稱已被使用
                        return Uni.createFrom().failure(new IllegalArgumentException("User name already exists"));
                    }
                });
    }

    @Override
    public Uni<List<UserDto>> findAll() {
        return User.findAll()
                .project(UserDto.class)
                .list();
    }

    @Override
    public Uni<String> deleteAll() {
        return User.deleteAll()
                .map(count -> count > 0 ? "已刪除全部" + count + "筆資料!" : "沒有資料可以刪除!");
    }
}
