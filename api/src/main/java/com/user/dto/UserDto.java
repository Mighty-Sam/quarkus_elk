package com.user.dto;

import com.user.entity.User;
import com.user.entity.embedded.Hobby;
import io.quarkus.mongodb.panache.common.ProjectionFor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@RegisterForReflection
@ProjectionFor(User.class)
@JsonbPropertyOrder({"name","email","password","role","hobby","orderItem"})
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private Hobby hobby;
    private List<String> orderItem;
}
