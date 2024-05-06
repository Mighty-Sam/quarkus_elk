package com.elk.resource;

import com.elk.dto.UserDto;
import com.elk.entity.User;
import com.elk.service.UserService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * [創建] 創建一筆 user info
     *
     * @param userInfo user info
     * @return Uni<RestResponse<UserDto>>
     */
    @POST
    public Uni<RestResponse<UserDto>> createUserInfo(User userInfo){
        return userService.create(userInfo)
                .onItem()
                .transform(RestResponse::ok)
                .invoke(userDto -> log.info("Create user info: {}", userInfo.getName()));
    }

    /**
     * [查詢] 查詢全部 user info
     * @return Uni<RestResponse<List<UserDto>>>
     */
    @GET
    public Uni<RestResponse<List<UserDto>>> findAllUserInfos(){
        return userService.findAll()
                .onItem()
                .transform(RestResponse::ok)
                .invoke(userDtos -> log.info("Find all user info: {}", userDtos));
    }

    /**
     * [刪除] 刪除全部 user info
     * @return Uni<RestResponse<String>>
     */
    @DELETE
    public Uni<RestResponse<String>> deleteAllUserInfos(){
        return userService.deleteAll()
                .onItem()
                .transform(RestResponse::ok)
                .invoke(msg -> log.info("Delete all user info: {}", msg));
    }
}
