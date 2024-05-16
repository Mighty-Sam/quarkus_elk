package com.user.entity.embedded;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Hobby extends ReactivePanacheMongoEntityBase {

    private String name;
    private String description;
}
