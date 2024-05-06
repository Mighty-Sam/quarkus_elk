package com.elk.dto;

import com.elk.entity.User;
import com.elk.entity.embedded.Hobby;
import io.quarkus.mongodb.panache.common.ProjectionFor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RegisterForReflection
@ProjectionFor(User.class)
@JsonbPropertyOrder({"name","email","password","role"})
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private Hobby hobby;
}
