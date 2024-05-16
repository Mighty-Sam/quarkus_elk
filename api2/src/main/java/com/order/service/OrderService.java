package com.order.service;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.PathParam;
import org.bson.types.ObjectId;
import java.util.List;

public interface OrderService {

    Uni<List<String>> getOrderItem(@PathParam("userId") ObjectId userId);

    Uni<OrderDto> createOrder(Order order);

}
