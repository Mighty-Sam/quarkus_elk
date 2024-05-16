package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import com.order.restClient.UserService;
import com.order.service.OrderService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Inject
    @RestClient
    UserService userService;

    @Override
    public Uni<List<String>> getOrderItem(ObjectId userId) {
    return Order.find("userId", userId)
            .project(OrderDto.class)
            .firstResult()
            .map(OrderDto::getOrderItem)
            .invoke(orderItem -> log.info("Get order item: {}", orderItem));
}

    @Override
    public Uni<OrderDto> createOrder(Order order) {

        ObjectId userId = order.getUserId();
        log.info("userId: {}", userId);
        List<String> orderItem = order.getOrderItem();
        log.info("orderItem: {}", orderItem.getFirst());
        if (userId == null || orderItem.isEmpty()){
            return Uni.createFrom().failure(new IllegalArgumentException("userId or orderItem is null"));
        }

//        return userService.getUser(userId)
//                .flatMap(Unchecked.function(userDto -> {
//
//                    if (userDto == null) {
//                        throw new IllegalArgumentException("User not found");
//                    }
//
//
//
//                    return order.persist()
//                            .flatMap(ignore -> Order.find("userId", userId)
//                                    .project(OrderDto.class)
//                                    .firstResult()
//                                    .onItem()
//                                    .ifNull()
//                                    .failWith(() -> new IllegalArgumentException("Create order failed")));
//                }));
        log.info("creating order in order service impl!");
        return order.persist()
                .flatMap(ignore -> Order.find("_id", order.getId())
                        .project(OrderDto.class)
                        .firstResult()
                        .onItem()
                        .ifNull()
                        .failWith(() -> new IllegalArgumentException("Create order failed")));

//        研究 rest client error
    }
}
