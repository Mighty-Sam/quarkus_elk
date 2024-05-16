package com.order.restClient;


import com.order.restClient.dto.UserDto;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/elk/user/api/")
@RegisterRestClient
public interface UserService {

    @GET
    @Path("/{userId}")
    Uni<UserDto> getUser(@PathParam("userId") ObjectId userId);

}
