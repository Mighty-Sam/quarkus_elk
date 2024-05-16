package com.user.restClient;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/elk/order/api/")
@RegisterRestClient
public interface OrderService {

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<String>> getOrderItem(@PathParam("userId") ObjectId userId);

}
