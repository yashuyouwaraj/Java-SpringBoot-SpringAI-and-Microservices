package com.yashu.demo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

    AlienRepository repo = new AlienRepository();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAlien() {
        System.out.println("...getAlien() called...");

        return repo.getAliens();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Alien getAlien(@javax.ws.rs.PathParam("id") int id) {
        return repo.getAliens(id);
    }

}
