package com.order.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@MongoEntity(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
@Accessors(chain = true)
public class Order extends ReactivePanacheMongoEntityBase {

    private ObjectId id;

    @BsonProperty("user_id")
    private ObjectId userId;

    @BsonProperty("order_item")
    private List<String> orderItem;
}
