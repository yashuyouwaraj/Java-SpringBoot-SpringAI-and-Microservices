package com.yashu.demo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

    AlienRepository repo = new AlienRepository();

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Alien> getAlien() {
        System.out.println("...getAlien() called...");

        return repo.getAliens();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alien getAlien(@javax.ws.rs.PathParam("id") int id) {
        return repo.getAliens(id);
    }

    @POST
    @Path("alien")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Alien createAlien(Alien a1){
        System.out.println("...createAlien() called...");
        repo.createAlien(a1);
        return a1;
    }

    @PUT
    @Path("alien")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Alien updateAlien(Alien a1){
        System.out.println("...updateAlien() called...");
        if(repo.getAliens(a1.getId()).getId()==0){
            repo.createAlien(a1);
        }
        repo.updateAlien(a1);
        return a1;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alien killAlien(@PathParam("id") int id) {
        System.out.println("...killAlien() called...");

        Alien a1 = repo.getAliens(id);
        if(a1.getId()!=0){
            repo.deleteAlien(id);
        }

        return a1;
    }
}
