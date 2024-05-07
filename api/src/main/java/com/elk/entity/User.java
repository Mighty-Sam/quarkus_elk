package com.elk.entity;

import com.elk.entity.embedded.Hobby;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

@EqualsAndHashCode(callSuper = true)
@MongoEntity(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
@Accessors(chain = true)
public class User extends ReactivePanacheMongoEntityBase {

    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Hobby hobby;
}
