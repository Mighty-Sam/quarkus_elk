package com.user.service.impl;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.service.UserService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import java.util.List;

@Slf4j
@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Override
    public Uni<UserDto> create(User userInfo) {
        return User.find("name", userInfo.getName()).firstResult()
                .flatMap(existingUser -> {
                    if (existingUser == null) {
                        userInfo.setOrderItem(null);
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
    public Uni<UserDto> findById(ObjectId id) {
        return User.find("_id", id)
                .project(UserDto.class)
                .firstResult()
                .onFailure().recoverWithItem(Unchecked.supplier(() -> {
                    throw new NotFoundException("User not found");
                }));
    }

    @Override
    public Uni<String> deleteAll() {
        return User.deleteAll()
                .map(count -> count > 0 ? "已刪除全部" + count + "筆資料!" : "沒有資料可以刪除!");
    }
}
