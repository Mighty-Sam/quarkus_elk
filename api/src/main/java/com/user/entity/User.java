package com.user.entity;

import com.user.entity.embedded.Hobby;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.List;

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
    @BsonProperty("order_item")
    private List<String> orderItem;
}
