package com.user.resource;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.restClient.OrderService;
import com.user.service.UserService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;
import java.util.List;

@Path("/elk/user/api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@RegisterForReflection
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    @RestClient
    OrderService orderService;

    /**
     * [創建] 創建一筆 user info
     *
     * @param userInfo user info
     * @return Uni<RestResponse<UserDto>>
     */
    @POST
    public Uni<RestResponse<UserDto>> createUserInfo(User userInfo) {
        return userService.create(userInfo)
                .onItem()
                .transform(RestResponse::ok)
                .invoke(userDto -> log.info("Create user info: {}", userInfo.getName()));
    }

    /**
     * [查詢] 查詢全部 user info
     *
     * @return Uni<RestResponse<List<UserDto>>>
     */
    @GET
    public Uni<RestResponse<List<UserDto>>> findAllUserInfos() {
        return userService.findAll()
                .onItem()
                .transform(RestResponse::ok)
                .invoke(userDtos -> log.info("Find all user info: {}", userDtos));
    }

    /**
     * [查詢] 使用 userId 查詢一筆 user info，並呼叫 order 裡的 getOrderItem 這支 api 拿到 order item 欄位的資料
     * @param userId user id
     * @return Uni<RestResponse < List < UserDto>>>
     */
    @GET
    @Path("/{userId}")
    public Uni<RestResponse<UserDto>> findUserInfoById(@PathParam("userId") ObjectId userId) {
        log.info("userId: {}", userId);
        return orderService.getOrderItem(userId)
                .flatMap(x -> userService.findById(userId)
                        .flatMap(y ->{
                            y.setOrderItem(x);
                            log.info("user info: {}", y);
                            log.info("user info: {}", y.getOrderItem());
                            return Uni.createFrom().item(y)
                                    .onItem()
                                    .transform(RestResponse::ok);
                }));
    }

    /**
     * [刪除] 刪除全部 user info
     *
     * @return Uni<RestResponse<String>>
     */
    @DELETE
    public Uni<RestResponse<String>> deleteAllUserInfos() {
        return userService.deleteAll()
                .onItem()
                .transform(RestResponse::ok)
                .invoke(msg -> log.info("Delete all user info: {}", msg));
    }

//    ===================test==============================\\

//    @GET
//    @Path("/test")
//    public List<String> test() {
//        System.out.println("testing....");
//        return orderService.getOrderItem(new ObjectId("61f3b3b3b3b3b3b3b3b3b3b3"));
//    }
}
