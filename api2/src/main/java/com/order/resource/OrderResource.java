package com.order.resource;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import com.order.service.OrderService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.RestResponse;
import java.util.List;

@Path("/elk/order/api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@RegisterForReflection
public class OrderResource {


    @Inject
    OrderService orderService;

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<String>> getOrderItem(@PathParam("userId") ObjectId userId){
        log.info("in order service ");
        return orderService.getOrderItem(userId);
    }

    @POST
    public Uni<RestResponse<OrderDto>> createOrder(Order order){
        return orderService.createOrder(order)
                .onItem()
                .transform(RestResponse::ok);
    }

//========================================================================
    @GET
    @Path("/hello")
    public String hello(){
        return "Hello";
    }
}



