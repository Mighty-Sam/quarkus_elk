package com.order.restClient.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RegisterForReflection
@JsonbPropertyOrder({"name"})
public class UserDto {

    private String name;

}