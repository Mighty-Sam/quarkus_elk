package com.elk.resource;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;

@Path("/elk/example/api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@RegisterForReflection
public class ExampleResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> example(){
        log.info("testLog");
        return Uni.createFrom().item("exaxcvxcvxxvxvvcccccmple");
    }
}
