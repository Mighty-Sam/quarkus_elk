package com.order.dto;

import com.order.entity.Order;
import io.quarkus.mongodb.panache.common.ProjectionFor;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import java.util.List;

@Data
@Accessors(chain = true)
@RegisterForReflection
@ProjectionFor(Order.class)
@JsonbPropertyOrder({"userId","orderItem"})
public class OrderDto {

    private ObjectId userId;
    private List<String> orderItem;
}
